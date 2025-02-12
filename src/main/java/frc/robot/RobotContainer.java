package frc.robot;

import java.lang.ModuleLayer.Controller;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.Subsystems.AlgaeBalls;
import frc.robot.Subsystems.Coral;


public class RobotContainer {
  private XboxController driverController;
  private AlgaeBalls AlgaeBallsT = new AlgaeBalls();
  private Coral Coral = new Coral();


  public RobotContainer() {
    driverController = new XboxController(0);
    configureBindings();  
  }



  private void configureButtonBindings() {
   // Algae Ballz
    new JoystickButton(driverController, XboxController.Button.kLeftBumper.value)
        .onTrue(new InstantCommand(() -> AlgaeBallsT.setThrowSpeed(), AlgaeBallsT));

        new JoystickButton(driverController, XboxController.Button.kRightBumper.value)
        .onTrue(new InstantCommand(() -> AlgaeBallsT.setPickUpSpeed(), AlgaeBallsT));
       
       // Coral (no funny name for this yet)
        //Move the whole picker-uper
  new POVButton(driverController, 0).whenPressed(()-> Coral.setDownSpeed());


      }


  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return null;
  }
}
