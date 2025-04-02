package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.subsystems.LiftSub;

public class RobotContainer {

  private final XboxController controller = new XboxController(0);
  private final LiftSub liftsub = new LiftSub();

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    new POVButton(controller, 0).whileTrue(new InstantCommand(() -> liftsub.raiseLift(), liftsub));
    new POVButton(controller, 180).whileTrue(new InstantCommand(() -> liftsub.lowerLift(), liftsub));
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
