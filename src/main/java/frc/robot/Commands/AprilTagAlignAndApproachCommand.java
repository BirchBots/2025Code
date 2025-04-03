package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Subsystems.LimelightSubsystem;
import frc.robot.Subsystems.SwerveSubsystem;

public class AprilTagAlignAndApproachCommand extends SequentialCommandGroup {
    
    public AprilTagAlignAndApproachCommand(
            SwerveSubsystem swerveSubsystem,
            LimelightSubsystem limelightSubsystem,
            int targetAprilTagId,
            double targetDistance) {
        
        
        // First, rotate to face the AprilTag
        addCommands(new Rotate());
        addCommands(new Forward(5.0));

    }
}