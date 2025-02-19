package frc.robot;



import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Subsystems.ClimbSubSystem;

public class RobotContainer {
private final static XboxController Controller = new XboxController(0);
private final static ClimbSubSystem Climb = new ClimbSubSystem();
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    new JoystickButton(Controller, XboxController.Button.kA.value).onTrue(new InstantCommand(()-> Climb.toggle(),Climb));
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
