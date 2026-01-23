package frc.robot.Subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSubSystem extends SubsystemBase{

    private final SparkMax motor = new SparkMax(11, MotorType.kBrushless); 
    private final static double speed = 0.7;

    public void up() {
        motor.set(speed);
    }

    public void down() {
        motor.set(-speed);
    }

    public void stop() {
        motor.set(0);
    }
}