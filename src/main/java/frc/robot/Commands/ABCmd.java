package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.AlgaeBalls;

public class ABCmd extends Command{
  
  private AlgaeBalls m_AlgaeBalls;

    public ABCmd(AlgaeBalls moveBalls) {
        
        m_AlgaeBalls = moveBalls;

        addRequirements(m_AlgaeBalls);
    }



}
