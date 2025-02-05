package frc.robot.Subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

// IDK where the canSparkMax import is LOL


public class AlgaeBalls extends SubsystemBase{

 private final SparkMax throwLeftMotor = new SparkMax(0, MotorType.kBrushless);
 private final SparkMax throwRightMotor = new SparkMax(0, MotorType.kBrushless);
 private final double speed = 0.5;

 public void setThrowSpeed() { 
   throwLeftMotor.set(speed);
   throwRightMotor.set(-speed);
 }

 public void setPickUpSpeed() {
   throwLeftMotor.set(-speed);
   throwRightMotor.set(speed);
 }


}
