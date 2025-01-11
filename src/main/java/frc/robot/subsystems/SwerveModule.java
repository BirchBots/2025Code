package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.robot.Constants.SwerveConstants;

public class SwerveModule {

    private final SparkMax driveMotor, turnMotor;
    private final RelativeEncoder driveEncoder, turnEncoder;
    private final CANcoder absoluteEncoder;
    private final double encoderOffset;
    private final PIDController pidController;

    // Swerve Module: Turning & driving motors, an absolute encoder, and a physical offset for the absolute encoder
    public SwerveModule(int driveMotorId, int turnMotorId, int absoluteEncoderId, double encoderOffset) {
        driveMotor = new SparkMax(driveMotorId, MotorType.kBrushless);
        turnMotor = new SparkMax(turnMotorId, MotorType.kBrushless);

        driveEncoder = driveMotor.getEncoder();
        driveEncoder.setPosition(0);
        turnEncoder = turnMotor.getEncoder();
        turnEncoder.setPosition(getAbsoluteEncoderRad());

        absoluteEncoder = new CANcoder(absoluteEncoderId);
        this.encoderOffset = encoderOffset;

        pidController = new PIDController(SwerveConstants.kP, SwerveConstants.kI, SwerveConstants.kD);
        pidController.enableContinuousInput(0, 2*Math.PI); // 0 radians = 2pi radians (0 degrees = 360 degrees)
    }

    // Set the DESIRED state, given by the command
    public void setDesiredState(SwerveModuleState state) {
        if (Math.abs(state.speedMetersPerSecond) < 0.001) {
            stop();
            return;
        }
        driveMotor.set(-state.speedMetersPerSecond * 0.2 / SwerveConstants.kMaxMetersPerSecond);
        double turningOutput = pidController.calculate(getAbsoluteEncoderRad(), state.angle.getRadians())/(2*Math.PI);
        turnMotor.set(turningOutput);
    }

    public double getAbsoluteEncoderRad() {
        double angle = 2 * Math.PI * absoluteEncoder.getAbsolutePosition().getValueAsDouble();
        return angle - encoderOffset;
    }

    // Returns the current PHYSICAL state, read directly from the encoders
    public SwerveModuleState getState() {
        return new SwerveModuleState(driveEncoder.getVelocity(), new Rotation2d(getAbsoluteEncoderRad()));
    }

    public void stop() {
        driveMotor.set(0);
        turnMotor.set(0);
    }
}