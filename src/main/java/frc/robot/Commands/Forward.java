package frc.robot.Commands;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.SwerveSubsystem;

public class Forward extends Command {
    
    SwerveSubsystem drive = new SwerveSubsystem();
    double startTime;

    public Forward() {
        addRequirements(drive); // drivetrain is an instance of our Drivetrain subsystem
     }
 
     public void initialize() {
        startTime = Timer.getTimestamp();
    }
 
     /*
           * execute() - In our execute method we call a tankDrive method we have created in our subsystem. This method takes two speeds as a parameter which we get from methods in the OI class.
           * These methods abstract the joystick objects so that if we want to change how we get the speed later we can do so without modifying our commands
           * (for example, if we want the joysticks to be less sensitive, we can multiply them by .5 in the getLeftSpeed method and leave our command the same).
           */
          public void execute() {

            drive.setModuleStates(new SwerveModuleState[]{
            new SwerveModuleState(1.0, new Rotation2d(0)),
            new SwerveModuleState(1.0, new Rotation2d(0)),
            new SwerveModuleState(1.0, new Rotation2d(0)),
            new SwerveModuleState(1.0, new Rotation2d(0))
            
        });
          }
 
     /*
           * isFinished - Our isFinished method always returns false meaning this command never completes on it's own. The reason we do this is that this command will be set as the default command for the subsystem. This means that whenever the subsystem is not running another command, it will run this command. If any other command is scheduled it will interrupt this command, then return to this command when the other command completes.
           */
          public boolean isFinished() {
              return (Timer.getTimestamp() - startTime >= 5.0);
          }
 
     protected void end() {
     }
 
     protected void interrupted() {
     }

}
