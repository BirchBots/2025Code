package frc.robot.Commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.SwerveConstants;
import frc.robot.Subsystems.LimelightSubsystem;
import frc.robot.Subsystems.SwerveSubsystem;

public class Rotate extends Command {

    SwerveSubsystem drive = new SwerveSubsystem();
    LimelightSubsystem limes = new LimelightSubsystem();

    public Rotate(){

        addRequirements(drive);
    }
    
    @Override
    public void initialize() {

    }
    
    @Override
    public void execute() {
        if (limes.getAprilTagId() == 1 || limes.getAprilTagId() == 13) {
            ChassisSpeeds chassisSpeeds = new ChassisSpeeds(0, 0, 1.0);

            SwerveModuleState[] moduleStates = SwerveConstants.kDriveKinematics.toSwerveModuleStates(chassisSpeeds);

            drive.setModuleStates(moduleStates);
        } else if (limes.getAprilTagId() == 2 || limes.getAprilTagId() == 12) {
            ChassisSpeeds chassisSpeeds = new ChassisSpeeds(0, 0, -1.0);

            SwerveModuleState[] moduleStates = SwerveConstants.kDriveKinematics.toSwerveModuleStates(chassisSpeeds);

            drive.setModuleStates(moduleStates);
        }

    }

    public boolean isFinished() {
        return (limes.getHorizontalOffset() == 0);
    }
 
    protected void end() {
    }
 
    protected void interrupted() {
    }
}