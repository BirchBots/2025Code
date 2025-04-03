package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.LimelightSubsystem;
import frc.robot.Subsystems.TankSubsystem;

public class Rotate extends Command {

    TankSubsystem drive = new TankSubsystem();
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
            drive.setLeft(-1);
            drive.setRight(1);
        } else if (limes.getAprilTagId() == 2 || limes.getAprilTagId() == 12) {
            drive.setLeft(1);
            drive.setRight(-1);
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