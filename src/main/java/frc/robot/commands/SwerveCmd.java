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
        this.turningLimiter = new SlewRateLimiter(SwerveConstants.kTeleSlew);
        addRequirements(swerveSubsystem);
    }

    @Override
    public void execute() {      
        double xSpeed = xSpdFunc.get();
        double ySpeed = ySpdFunc.get();
        double turningSpeed = -turnSpdFunc.get();

        xSpeed = Math.abs(xSpeed) > IOConstants.kDeadband ? xSpeed : 0.0;
        ySpeed = Math.abs(ySpeed) > IOConstants.kDeadband ? ySpeed : 0.0;
        turningSpeed = Math.abs(turningSpeed) > IOConstants.kDeadband ? turningSpeed : 0.0;

        xSpeed = xLimiter.calculate(xSpeed) * SwerveConstants.kMaxMetersPerSecond;
        ySpeed = yLimiter.calculate(ySpeed) * SwerveConstants.kMaxMetersPerSecond;
        turningSpeed = turningLimiter.calculate(turningSpeed) * SwerveConstants.kMaxMetersPerSecond;

        ChassisSpeeds chassisSpeeds = new ChassisSpeeds(xSpeed, ySpeed, turningSpeed);

        SwerveModuleState[] moduleStates = SwerveConstants.kDriveKinematics.toSwerveModuleStates(chassisSpeeds);
        swerveSubsystem.setModuleStates(moduleStates);
    }

    @Override
    public void end(boolean interrupted) {
        swerveSubsystem.stopModules();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}