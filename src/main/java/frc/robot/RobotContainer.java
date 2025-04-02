package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.IOConstants;
import frc.robot.commands.TankCommand;
import frc.robot.subsystems.TankSubsystem;


public class RobotContainer {

    private final TankSubsystem tank = new TankSubsystem();

    private final XboxController controller = new XboxController(IOConstants.kDriverControllerPort);

    public RobotContainer() {
        configureBindings();
    }

    private void configureBindings() {
        tank.setDefaultCommand(new TankCommand(tank, () -> controller.getLeftY(), () -> controller.getRightY()));
    }

    public Command getAutonomousCommand() {
        return null;
    }
}