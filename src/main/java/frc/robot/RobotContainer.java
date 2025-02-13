package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class RobotContainer {
  SendableChooser<Command> m_chooser = new SendableChooser<Command>();
  public RobotContainer() {
    m_chooser.setDefaultOption("Middle", new InstantCommand());
    m_chooser.addOption("right", new InstantCommand());
    m_chooser.addOption("left", new InstantCommand());
    SmartDashboard.putData(m_chooser);
    configureBindings();
  }

  private void configureBindings() {}
    

  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }
}
