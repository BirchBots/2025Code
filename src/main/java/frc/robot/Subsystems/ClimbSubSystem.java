package frc.robot.Subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSubSystem extends SubsystemBase{
    private final static SparkMax ClimbMotor = new SparkMax(0,MotorType.kBrushless);
    private final RelativeEncoder climbEncoder = ClimbMotor.getEncoder();
    private final float upRad = (float)Math.PI/2;
    private final float downrad = (float)Math.PI;
    public void toggle() {
        if ()
    }
    
}
