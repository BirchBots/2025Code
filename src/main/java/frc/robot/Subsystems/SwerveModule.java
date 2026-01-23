package frc.robot.Subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveModule extends SubsystemBase{

    //drive 
    public Rotation2d getAngle() {
        return Rotation2d.fromRotations(getModuleAngRotations());
    }

    SparkMax driveMotor;
    int driveMotorID;
    SparkAbsoluteEncoder driveMotorEncoder;
    SparkClosedLoopController  driveController;
    //steer
    SparkMax steerMotor;
    SparkAbsoluteEncoder steerMotorEncoder;
    PIDController steerController;
    //module encoder 
    CANcoder moduleEncoder;
    double encoderOffsetRotations;
    //conversion factors
    final double WHEEL_DIAMETER = Units.inchesToMeters(4);
    final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;
    final double GEAR_RATIO = 1.0 / 6.75;
    final double DRIVE_POSITION_CONVERSION = WHEEL_CIRCUMFERENCE * GEAR_RATIO;
    final double DRIVE_VELOCITY_CONVERSION = DRIVE_POSITION_CONVERSION / 60.0;
    final double STEER_POSITION_CONVERSION = 1;
    final double STEER_VELOCITY_CONVERSION = STEER_POSITION_CONVERSION / 60.0;

    //CONSTRUCTOR//
        public SwerveModule(int driveMotorID, int steerMotorID, int encoderID, Double encoderOffsetRotations){
            this.driveMotorID = driveMotorID;

            //drive motor 
            driveMotor = new SparkMax(driveMotorID, MotorType.kBrushless);
            SparkMaxConfig driveConfig = new SparkMaxConfig();
            driveConfig.smartCurrentLimit(40);
            driveConfig.idleMode(IdleMode.kBrake);
            driveMotor.configure(driveConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

            //drive encoder
            driveMotorEncoder = driveMotor.getAbsoluteEncoder();
            //steer motor
            steerMotor = new SparkMax(steerMotorID, MotorType.kBrushless);
            SparkMaxConfig steerConfig = new SparkMaxConfig();
            steerConfig.idleMode(IdleMode.kBrake);
            steerConfig.smartCurrentLimit(40);
            steerMotor.configure(steerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
            // module encoder
            moduleEncoder = new CANcoder(encoderID);
            this.encoderOffsetRotations = encoderOffsetRotations;

            //controllers
            //driveController = driveMotor.getPIDController();
            //driveController.setP(Constants.Modules.SpeedKP);
            //driveController.setI(Constants.Modules.SpeedKI);
            //driveController.setD(Constants.Modules.SpeedKD);

            steerController = new PIDController(1.5, 0.0, 0.0);
            steerController.enableContinuousInput(0, 1);

        }

        public void setTargetState(SwerveModuleState desiredState) {
    
            // Optimize wheel angle vs current module angle
            Rotation2d currentAngle = getAngle();
            SwerveModuleState optimized = SwerveModuleState.optimize(desiredState, currentAngle);
        
            // -------- STEERING MOTOR -------- //
            // Convert the optimized angle (Rotation2d) to rotations (0–1)
            double targetRotations = optimized.angle.getRotations();
            double currentRotations = getModuleAngRotations();
        
            double steerOutput = steerController.calculate(currentRotations, targetRotations);
            steerMotor.set(-steerOutput);   // your code uses inverted steering
        
            // -------- DRIVE MOTOR -------- //
            double speed = optimized.speedMetersPerSecond;
        
            // Normalize to motor command (your 4.52 value)
            driveMotor.set(speed / 4.52);
        }


        public double getModuleAngRotations(){
            return moduleEncoder.getAbsolutePosition().getValueAsDouble() - encoderOffsetRotations;
        }
        
        public SwerveModulePosition getModulePosition() {
            return new SwerveModulePosition(
                driveMotorEncoder.getPosition(), //FIXME i broke this sorry
                Rotation2d.fromRotations(getModuleAngRotations())
            );  
        }

        public SwerveModuleState getSwerveModuleState() {
            return new SwerveModuleState(
                driveMotorEncoder.getVelocity(), //FIXME i broke this sorry
                Rotation2d.fromRotations(getModuleAngRotations()));
        }
    ////
}
