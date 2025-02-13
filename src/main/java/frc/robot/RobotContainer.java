package frc.robot;

import java.lang.ModuleLayer.Controller;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
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
   //Can delete this if the "new" code to throw/pick up coral doesn't work
    Trigger rightTrigger = new Trigger(() -> driverController.getRightTriggerAxis() > 0.5);
    Trigger leftTrigger = new Trigger(() -> driverController.getLeftTriggerAxis() > 0.5);
  

    // Algae Ballz
    new JoystickButton(driverController, XboxController.Button.kLeftBumper.value)
        .onTrue(new InstantCommand(() -> AlgaeBallsT.setThrowSpeed(), AlgaeBallsT));

    new JoystickButton(driverController, XboxController.Button.kRightBumper.value)
        .onTrue(new InstantCommand(() -> AlgaeBallsT.setPickUpSpeed(), AlgaeBallsT));
       

  // CORAL!!!!!!!! (no funny name for this yet)
  //Move the overall coral 
    new POVButton(driverController, 0).onTrue(new InstantCommand(() -> Coral.setUpSpeed(), Coral));
    new POVButton(driverController, 180).onTrue(new InstantCommand(() -> Coral.setDownSpeed(), Coral));
  
  // pick/shoot coral    
        rightTrigger.onTrue(new InstantCommand(() -> Coral.setThrowCoral(), Coral));
        leftTrigger.onTrue(new InstantCommand(() -> Coral.setPickCoral(), Coral));

  /* Different way to binding coral throw/pick 
        new JoystickButton(driverController, XboxController.Axis.kLeftTrigger.value)
        .onTrue(new InstantCommand(() -> Coral.setThrowCoral(), Coral));

        new JoystickButton(driverController, XboxController.Axis.kRightTrigger.value)
        .onTrue(new InstantCommand(() -> Coral.setPickCoral(), Coral));
*/


      }


  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return null;
  }
}
