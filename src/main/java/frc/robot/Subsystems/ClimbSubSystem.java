package frc.robot.Subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSubSystem extends SubsystemBase{
    private final static SparkMax ClimbMotor = new SparkMax(0,MotorType.kBrushless);
    private final RelativeEncoder climbEncoder = ClimbMotor.getEncoder();
    private final double upRad = Math.PI/2; 
    private final double speed = 0.4;
    private final double downRad = Math.PI;
    public void toggle() {
        if (climbEncoder.getPosition() == upRad){ //if up is true write it here
            ClimbMotor.set(speed);

        } else if (climbEncoder.getPosition() == downRad) {
            ClimbMotor.set(-speed);
        

        }
    }
    
}
