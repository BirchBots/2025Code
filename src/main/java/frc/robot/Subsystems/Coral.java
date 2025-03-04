package frc.robot.Subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Coral extends SubsystemBase{

    private final SparkMax downMotor = new SparkMax(0, MotorType.kBrushless);
    
    private final SparkMax throwCoralMotor = new SparkMax(0, MotorType.kBrushless);
    
 private final double speed = 0.5;
 private final double rSpeed = 0.1;

 //Move coral, more effecient way to
 public void setDownSpeed() { 
   downMotor.set(-rSpeed);
 }

 public void setUpSpeed() {
    downMotor.set(rSpeed);
 }


// pick up or shoot coral
 public void setThrowCoral() {
    throwCoralMotor.set(speed);
 }
 public void setPickCoral() {
    throwCoralMotor.set(-speed);
 }






}
