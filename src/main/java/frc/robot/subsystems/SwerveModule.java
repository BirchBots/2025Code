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

    public SwerveModule(int driveMotorId, int turnMotorId, int absoluteEncoderId, double encoderOffset) {
        driveMotor = new SparkMax(driveMotorId, MotorType.kBrushless);
        turnMotor = new SparkMax(turnMotorId, MotorType.kBrushless);

        driveEncoder = driveMotor.getEncoder();
        driveEncoder.setPosition(0);
        turnEncoder = turnMotor.getEncoder();
        turnEncoder.setPosition(getAbsoluteEncoderRad());

        absoluteEncoder = new CANcoder(absoluteEncoderId);
        this.encoderOffset = encoderOffset;

        // Reduce PID values significantly
        pidController = new PIDController(
            SwerveConstants.kP * 0.4,  // Reduce P gain
            0,  // Remove I gain for now
            SwerveConstants.kD * 0.2   // Reduce D gain
        );
        pidController.enableContinuousInput(0, 2 * Math.PI);  // Back to 0 to 2π range
    }

    public void setDesiredState(SwerveModuleState state) {
        if (Math.abs(state.speedMetersPerSecond) < 0.001) {
            stop();
            return;
        }
        
        // Get the current angle
        double currentAngleRad = getAbsoluteEncoderRad();
        
        // Optimize the state to avoid spinning 180 degrees
        state.optimize(new Rotation2d(currentAngleRad));
        // Set drive speed (keep your original 0.2 factor for now)
        //driveMotor.set(-state.speedMetersPerSecond * 0.2 / SwerveConstants.kMaxMetersPerSecond);
        
        // Set turn motor (keep your original PID calculation)
        double turningOutput = pidController.
        turnMotor.set(turningOutput);
    }

    public double getAbsoluteEncoderRad() {
        if (absoluteEncoder == null) {
            System.out.println("CANcoder not initialized properly.");
            return 0;
        }
        return absoluteEncoder.getAbsolutePosition().getValueAsDouble() * 2 * Math.PI - encoderOffset;
    }

    public SwerveModuleState getState() {
        return new SwerveModuleState(driveEncoder.getVelocity(), new Rotation2d(getAbsoluteEncoderRad()));
    }

    public void stop() {
        driveMotor.set(0);
        turnMotor.set(0);
    }
}