package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Subsystems.LimelightSubsystem;

public class AprilTagAlignAndApproachCommand extends SequentialCommandGroup {
    
    public AprilTagAlignAndApproachCommand(
            LimelightSubsystem limelightSubsystem,
            int targetAprilTagId,
            double targetDistance) {
        
        
        // First, rotate to face the AprilTag
        addCommands(new Forward(1.8288, 1.40335));
        addCommands(new Rotate());
        addCommands(new Forward(0.305, 0.22225));
        addCommands(new PlacingAuto());

    }
}