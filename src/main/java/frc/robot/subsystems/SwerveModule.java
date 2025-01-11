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

    SwerveModuleState desiredState;

    final SparkMax driveMotor, turningMotor;

    private final RelativeEncoder driveEncoder, turningEncoder;

    private final PIDController turningPidController;

    private final CANcoder absoluteEncoder;
    private final double absoluteEncoderOffsetRad;

    // This class is the one for each swerve module
    // Initialized with: two motors with relative encoders, an absolute encoder, and a pid controller
    public SwerveModule(int driveMotorId, int turningMotorId, int absoluteEncoderId, double absoluteEncoderOffset) {

        this.absoluteEncoderOffsetRad = absoluteEncoderOffset;
        absoluteEncoder = new CANcoder(absoluteEncoderId);

        driveMotor = new SparkMax(driveMotorId, MotorType.kBrushless);
        turningMotor = new SparkMax(turningMotorId, MotorType.kBrushless);

        driveEncoder = driveMotor.getEncoder();
        turningEncoder = turningMotor.getEncoder();

        turningPidController = new PIDController(SwerveConstants.kP, SwerveConstants.kI, SwerveConstants.kD);
        turningPidController.enableContinuousInput(0, 2*Math.PI);
        resetEncoders();
    }

    // Get value from encodere
    public double getDrivePosition() {
        return driveEncoder.getPosition();
    }

    public double getTurningPosition() {
        return turningEncoder.getPosition() * 2*Math.PI;
    }

    public double getDriveVelocity() {
        return driveEncoder.getVelocity();
    }

    public double getTurningVelocity() {
        return turningEncoder.getVelocity();
    }

    // This gets the rotation of the absolute encoder in radians
    public double getAbsoluteEncoderRad() {
        double angle = 2 * Math.PI * absoluteEncoder.getAbsolutePosition().getValueAsDouble();
        return angle - absoluteEncoderOffsetRad;
    }

    // Reset encoders back to 0
    public void resetEncoders() {
        driveEncoder.setPosition(0);
        turningEncoder.setPosition(getAbsoluteEncoderRad());
    }

    // Get PHYSICAL state with speed and rotation
    public SwerveModuleState getState() {
        return new SwerveModuleState(getDriveVelocity(), new Rotation2d(getAbsoluteEncoderRad()));
    }

    // Set DESIRED state with speed and rotation
    public void setDesiredState(SwerveModuleState state) {
        if (Math.abs(state.speedMetersPerSecond) < 0.001) {
            stop();
            return;
        }
        desiredState = state;
        driveMotor.set(-state.speedMetersPerSecond * 0.2 / SwerveConstants.kMaxMetersPerSecond);
        double turningOutput = turningPidController.calculate(getAbsoluteEncoderRad(), state.angle.getRadians())/(2*Math.PI);
        turningMotor.set(turningOutput);
    }

    public SwerveModuleState getDesiredState() {
        if (desiredState == null) return new SwerveModuleState(0, Rotation2d.fromDegrees(0));
        return desiredState;
    }

    // Stop.
    public void stop() {
        driveMotor.set(0);
        turningMotor.set(0);
    }
}