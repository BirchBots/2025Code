package frc.robot;



import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.ClimbSubSystem;

public class RobotContainer {
private final static XboxController Controller = new XboxController(0);
private final static ClimbSubSystem Climb = new ClimbSubSystem();
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {

  }

  public Command getAutonomousCommand() {
    return null;
  }
}
