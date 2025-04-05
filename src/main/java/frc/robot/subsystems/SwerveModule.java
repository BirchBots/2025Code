package frc.robot.subsystems;

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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SwerveModule extends SubsystemBase{

    //drive 
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
            steerConfig.smartCurrentLimit(20);
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

    //DRIVE//
        public void setTargetState(SwerveModuleState targetState) {
            //PID experement
            //  steerMotor.set(-steerController.calculate(getModuleAngRotations(),targetState.angle.getRotations()));
            //  driveController.setReference(targetState.speedMetersPerSecond / DRIVE_VELOCITY_CONVERSION, ControlType.kVelocity);

            // FUNCTIONING
            double currentAngle = getModuleAngRotations();
            steerMotor.set(-steerController.calculate(currentAngle, targetState.angle.getRotations()));
            targetState.speedMetersPerSecond *= targetState.angle.minus(new Rotation2d(currentAngle*2*Math.PI)).getCos();
            driveMotor.set(targetState.speedMetersPerSecond/4.52); 
        }
    //FEEDBACK//
        public void periodic() {
            SmartDashboard.putNumber("S" + driveMotorID, getModuleAngRotations());
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