package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.IOConstants;
import frc.robot.Subsystems.AlgaeBalls;
import frc.robot.Subsystems.ClimbSubSystem;
import frc.robot.Subsystems.Coral;

public class RobotContainer {

    private final AlgaeBalls algae = new AlgaeBalls();
    private final Coral coral = new Coral();
    private final ClimbSubSystem climb = new ClimbSubSystem();

    private final XboxController controller = new XboxController(IOConstants.kDriverControllerPort);

    private final SendableChooser<Command> m_chooser = new SendableChooser<Command>();

    public RobotContainer() {

        m_chooser.setDefaultOption("Middle", new InstantCommand());
        m_chooser.addOption("right", new InstantCommand());
        m_chooser.addOption("left", new InstantCommand());
        SmartDashboard.putData(m_chooser);

        configureBindings();
    }

    private void configureBindings() {

        new JoystickButton(controller, XboxController.Button.kLeftBumper.value)
            .onTrue(new InstantCommand(() -> algae.setThrowSpeed(), algae));
        new JoystickButton(controller, XboxController.Button.kRightBumper.value)
            .onTrue(new InstantCommand(() -> algae.setPickUpSpeed(), algae));

        new POVButton(controller, 0).onTrue(new InstantCommand(() -> coral.setUpSpeed(), coral));
        new POVButton(controller, 180).onTrue(new InstantCommand(() -> coral.setDownSpeed(), coral));

        new Trigger(() -> controller.getRightTriggerAxis() > 0.5)
            .onTrue(new InstantCommand(() -> coral.setThrowCoral(), coral));
        new Trigger(() -> controller.getLeftTriggerAxis() > 0.5)
            .onTrue(new InstantCommand(() -> coral.setPickCoral(), coral));

        new JoystickButton(controller, XboxController.Button.kA.value)
            .onTrue(new InstantCommand(()-> climb.toggle(), climb));
    }

    public Command getAutonomousCommand() {
        return null;
    }
}