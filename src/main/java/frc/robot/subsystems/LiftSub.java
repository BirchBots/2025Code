
package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkMax;

public class LiftSub extends SubsystemBase {
    
    private final SparkMax leftMotor = new SparkMax(9, MotorType.kBrushless);
    private final SparkMax rightMotor = new SparkMax(10, MotorType.kBrushless);

    private final static double speed = 0.7;
 
    public void raiseLift() {
        leftMotor.set(-speed);
        rightMotor.set(speed);
    }

    public void lowerLift(){
        leftMotor.set(speed);
        rightMotor.set(-speed);
    }
}

