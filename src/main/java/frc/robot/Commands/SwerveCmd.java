package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.IOConstants;
import frc.robot.Constants.SwerveConstants;
import frc.robot.Subsystems.SwerveSubsystem;

public class SwerveCmd extends Command {

    private final SwerveSubsystem swerveSubsystem;
    private final Supplier<Double> xSpdFunc, ySpdFunc, turnSpdFunc;
    private final SlewRateLimiter xLimiter, yLimiter, turningLimiter;

    public SwerveCmd(SwerveSubsystem swerveSubsystem, Supplier<Double> xSpdFunc, Supplier<Double> ySpdFunc, Supplier<Double> turnSpdFunc) {
        this.swerveSubsystem = swerveSubsystem;
        this.xSpdFunc = xSpdFunc;
        this.ySpdFunc = ySpdFunc;
        this.turnSpdFunc = turnSpdFunc;
        this.xLimiter = new SlewRateLimiter(SwerveConstants.kTeleSlew);
        this.yLimiter = new SlewRateLimiter(SwerveConstants.kTeleSlew);
        this.turningLimiter = new SlewRateLimiter(SwerveConstants.kTeleSlew);
        addRequirements(swerveSubsystem);
    }

    @Override
    public void execute() {      
        double xInput = xSpdFunc.get();
        double yInput = ySpdFunc.get();
        double turnInput = -turnSpdFunc.get();
        // Deadband
        if (Math.abs(xInput) < IOConstants.kDeadband) xInput = 0;
        if (Math.abs(yInput) < IOConstants.kDeadband) yInput = 0;
        if (Math.abs(turnInput) < IOConstants.kDeadband) turnInput = 0;

        // Slew rate limiters
        xInput = xLimiter.calculate(xInput);
        yInput = yLimiter.calculate(yInput);
        turnInput = turningLimiter.calculate(turnInput);

        // ----- FULL VECTOR MATH -----
        // Convert joystick into a proper vector
        double magnitude = Math.hypot(xInput, yInput);
        double angle = Math.atan2(yInput, xInput);   // radians

        // Convert back into chassis-relative velocity components
        double xSpeed = magnitude * Math.cos(angle) * SwerveConstants.kMaxMetersPerSecond;
        double ySpeed = magnitude * Math.sin(angle) * SwerveConstants.kMaxMetersPerSecond;
        double turningSpeed = turnInput * SwerveConstants.kMaxAngularSpeed;

         // Build chassis speeds (robot-relative)
        ChassisSpeeds chassisSpeeds = new ChassisSpeeds(xSpeed, ySpeed, turningSpeed);

        // Convert to module states
        SwerveModuleState[] moduleStates =
        SwerveConstants.kDriveKinematics.toSwerveModuleStates(chassisSpeeds);

        // Send to subsystem
        swerveSubsystem.setModuleStates(moduleStates);
    }
}