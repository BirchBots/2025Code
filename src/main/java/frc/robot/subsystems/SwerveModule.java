package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units;
import frc.robot.Constants.SwerveConstants;

public class SwerveModule {
    private final SparkMax driveMotor, turnMotor;
    private final RelativeEncoder driveEncoder, turnEncoder;
    private final CANcoder absoluteEncoder;
    private final double encoderOffset;
    private final PIDController pidController;

    public SwerveModule(int driveMotorId, int turnMotorId, int absoluteEncoderId, double encoderOffset) {
        driveMotor = new SparkMax(driveMotorId, MotorType.kBrushless);
        turnMotor = new SparkMax(turnMotorId, MotorType.kBrushless);

        driveEncoder = driveMotor.getEncoder();
        driveEncoder.setPosition(0);
        turnEncoder = turnMotor.getEncoder();
        turnEncoder.setPosition(getAbsoluteEncoderRad());

        absoluteEncoder = new CANcoder(absoluteEncoderId);
        this.encoderOffset = encoderOffset;

        pidController = new PIDController(SwerveConstants.kP * 0.4, 0, SwerveConstants.kD * 0.2);
        pidController.enableContinuousInput(-Math.PI, Math.PI); 
    }

    public void setDesiredState(SwerveModuleState state) {
        if (Math.abs(state.speedMetersPerSecond) < 0.001) {
            stop();
            return;
        }
        
        double currentAngleRad = getAbsoluteEncoderRad();
        
        state.optimize(new Rotation2d(currentAngleRad));
        //driveMotor.set(-state.speedMetersPerSecond * 0.2 / SwerveConstants.kMaxMetersPerSecond);
        
        double turningOutput = pidController.calculate(currentAngleRad, state.angle.getRadians());
        turnMotor.set(turningOutput);
    }

    public double getAbsoluteEncoderRad() {
        if (absoluteEncoder == null) {
            System.out.println("CANcoder not initialized properly.");
            return 0;
        }
        return Units.rotationsToRadians(absoluteEncoder.getAbsolutePosition().getValueAsDouble() - Units.radiansToRotations(encoderOffset));
    }

    public SwerveModuleState getState() {
        return new SwerveModuleState(driveEncoder.getVelocity(), new Rotation2d(getAbsoluteEncoderRad()));
    }

    public void stop() {
        driveMotor.set(0);
        turnMotor.set(0);
    }
}