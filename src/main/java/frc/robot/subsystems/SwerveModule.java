package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.robot.Constants.SwerveConstants;

public class SwerveModule {

    final CANSparkMax driveMotor, turningMotor;

    private final RelativeEncoder driveEncoder, turningEncoder;

    private final PIDController turningPidController;

    private final AnalogInput absoluteEncoder;
    private final double absoluteEncoderOffsetRad;

    // This class is the one for each swerve module
    // Initialized with: two motors with relative encoders, an absolute encoder, and a pid controller
    public SwerveModule(int driveMotorId, int turningMotorId, int absoluteEncoderId, double absoluteEncoderOffset) {

        this.absoluteEncoderOffsetRad = absoluteEncoderOffset;
        absoluteEncoder = new AnalogInput(absoluteEncoderId);

        driveMotor = new CANSparkMax(driveMotorId, MotorType.kBrushless);
        turningMotor = new CANSparkMax(turningMotorId, MotorType.kBrushless);

        driveEncoder = driveMotor.getEncoder();
        turningEncoder = turningMotor.getEncoder();

        driveEncoder.setPositionConversionFactor(SwerveConstants.kDriveGearRatio);
        driveEncoder.setVelocityConversionFactor(SwerveConstants.kDriveGearRatio);
        turningEncoder.setPositionConversionFactor(SwerveConstants.kTurnGearRatio);
        turningEncoder.setVelocityConversionFactor(SwerveConstants.kTurnGearRatio);

        turningPidController = new PIDController(SwerveConstants.kPTurning, 0, 0);
        turningPidController.enableContinuousInput(-Math.PI, Math.PI);

        resetEncoders();
    }

    // Get value from encodere
    public double getDrivePosition() {
        return driveEncoder.getPosition();
    }

    public double getTurningPosition() {
        return turningEncoder.getPosition();
    }

    public double getDriveVelocity() {
        return driveEncoder.getVelocity();
    }

    public double getTurningVelocity() {
        return turningEncoder.getVelocity();
    }

    // This gets the rotation of the absolute encoder in radians
    public double getAbsoluteEncoderRad() {
        double angle = absoluteEncoder.getVoltage() / RobotController.getVoltage5V();
        angle *= 2.0 * Math.PI;
        return angle - absoluteEncoderOffsetRad;
    }

    // Reset encoders back to 0
    public void resetEncoders() {
        driveEncoder.setPosition(0);
        turningEncoder.setPosition(getAbsoluteEncoderRad());
    }

    // Get PHYSICAL state with speed and rotation
    public SwerveModuleState getState() {
        return new SwerveModuleState(getDriveVelocity(), new Rotation2d(getTurningPosition()));
    }

    // Set DESIRED state with speed and rotation
    public void setDesiredState(SwerveModuleState state) {
        if (Math.abs(state.speedMetersPerSecond) < 0.001) {
            stop();
            return;
        }
        state = SwerveModuleState.optimize(state, getState().angle);
        driveMotor.set(state.speedMetersPerSecond / SwerveConstants.kMaxMetersPerSecond);
        turningMotor.set(turningPidController.calculate(getTurningPosition(), state.angle.getRadians()));
    }

    // Stop.
    public void stop() {
        driveMotor.set(0);
        turningMotor.set(0);
    }
}