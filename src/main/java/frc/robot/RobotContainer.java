package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Subsystems.AlgaeBalls;


public class RobotContainer {
  private XboxController driverController;
  private AlgaeBalls AlgaeBallsT = new AlgaeBalls();

  public RobotContainer() {
    driverController = new XboxController(0);


    configureBindings();

   
  }



  private void configureButtonBindings() {
    new JoystickButton(driverController, XboxController.Button.kLeftBumper.value)
        .onTrue(new InstantCommand(() -> AlgaeBallsT.setThrowSpeed(), AlgaeBallsT));
}

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return null;
  }
}
