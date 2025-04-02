package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.SwerveCmd;
import frc.robot.subsystems.SwerveSubsystem;

public class RobotContainer {

  private final XboxController controller = new XboxController(0);
  private final LiftSub liftsub = new LiftSub();

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    new POVButton(controller, 0).whileTrue(new InstantComand(()->liftsub.raiseLift(), liftsub));
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
