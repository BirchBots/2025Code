package frc.robot.commands;

import java.util.function.Supplier;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.IOConstants;
import frc.robot.Constants.SwerveConstants;
import frc.robot.subsystems.SwerveSubsystem;

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
        this.turningLimiter = new SlewRateLimiter(SwerveConstants.kTeleAngularSlew);
        addRequirements(swerveSubsystem);
    }

    @Override
    public void execute() {      
        // Get current joystick values  
        double xSpeed = xSpdFunc.get();
        double ySpeed = ySpdFunc.get();
        double turningSpeed = -turnSpdFunc.get();

<<<<<<< Updated upstream
    @Override
    public void execute() {      
        // Get current joystick values  
        double xSpeed = xSpdFunc.get();
        double ySpeed = ySpdFunc.get();
        double turningSpeed = -turnSpdFunc.get();

        // Apply a deadband (if the joystick moves super little, just don't do anything, look up ternary operator if confused)
        xSpeed = Math.abs(xSpeed) > IOConstants.kDeadband ? xSpeed : 0.0;
        ySpeed = Math.abs(ySpeed) > IOConstants.kDeadband ? ySpeed : 0.0;
        turningSpeed = Math.abs(turningSpeed) > IOConstants.kDeadband ? turningSpeed : 0.0;

        // Add a slew limiter (makes motion less jank)
        xSpeed = xLimiter.calculate(xSpeed) * SwerveConstants.kMaxMetersPerSecond;
        ySpeed = yLimiter.calculate(ySpeed) * SwerveConstants.kMaxMetersPerSecond;
        turningSpeed = turningLimiter.calculate(turningSpeed) * SwerveConstants.kMaxMetersPerSecond;

=======
        // Apply a deadband (if the joystick moves super little, just don't do anything, look up ternary operator if confused)
        xSpeed = Math.abs(xSpeed) > IOConstants.kDeadband ? xSpeed : 0.0;
        ySpeed = Math.abs(ySpeed) > IOConstants.kDeadband ? ySpeed : 0.0;
        turningSpeed = Math.abs(turningSpeed) > IOConstants.kDeadband ? turningSpeed : 0.0;

        // Add a slew limiter (makes motion less jank)
        xSpeed = xLimiter.calculate(xSpeed) * SwerveConstants.kMaxMetersPerSecond;
        ySpeed = yLimiter.calculate(ySpeed) * SwerveConstants.kMaxMetersPerSecond;
        turningSpeed = turningLimiter.calculate(turningSpeed) * SwerveConstants.kMaxMetersPerSecond;

>>>>>>> Stashed changes
        // ChassisSpeeds object, just give it x y and theta
        ChassisSpeeds chassisSpeeds;
        chassisSpeeds = new ChassisSpeeds(xSpeed, ySpeed, turningSpeed);

        // Create an array of 4 module states for each swerve module from kinematics (x and y location of modules) and chassis speeds
        SwerveModuleState[] moduleStates = SwerveConstants.kDriveKinematics.toSwerveModuleStates(chassisSpeeds);

        // Tell the subsystem to do something with said states
        swerveSubsystem.setModuleStates(moduleStates);
    }

    // When ur done ur done buddy
    @Override
    public void end(boolean interrupted) {
        swerveSubsystem.stopModules();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}