package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.SwerveCmd;
import frc.robot.subsystems.SwerveSubsystem;

public class RobotContainer {

  //Swerve subsystem: Controls physical components
  private final SwerveSubsystem m_robotDrive = new SwerveSubsystem();
  //XBoxController: just an xbox controller
  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);

  public RobotContainer() {
    // Into the swerve subsystem, pass the swerve command (new SwerveCmd) with three inputs: left stick y, left stick x, and right stick x
    m_robotDrive.setDefaultCommand(new SwerveCmd(m_robotDrive,
                () -> -m_driverController.getLeftY(),
                () -> -m_driverController.getLeftX(),
                () -> m_driverController.getRightX())
    );
    //Idk what this does just never delete it
    configureBindings();
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return null;
  }
}
