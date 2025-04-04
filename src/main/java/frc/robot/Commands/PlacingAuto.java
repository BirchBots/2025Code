package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Subsystems.LiftSub;
import frc.robot.Subsystems.Coral;

public class PlacingAuto extends Command {

    
    Timer timer = new Timer();
    double startTime;
    
    LiftSub lift = new LiftSub();
    Coral coral  = new Coral();

    public PlacingAuto() {
        addRequirements((Subsystem) lift);
        addRequirements((Subsystem) coral);
    }

    public void initialize() {
        startTime = timer.get();
        timer.start();
    }
 
    public void execute() {

        LiftSub.raiseLift();

    }
 
     /*
           * isFinished - Our isFinished method always returns false meaning this command never completes on it's own. The reason we do this is that this command will be set as the default command for the subsystem. This means that whenever the subsystem is not running another command, it will run this command. If any other command is scheduled it will interrupt this command, then return to this command when the other command completes.
           */
          public boolean isFinished() {
            return (Timer.getTimestamp()  - startTime >= 1.0);
        }
 
     protected void end() {
        coral.setDownSpeed();
    }
 
     protected void interrupted() {
     }

}
