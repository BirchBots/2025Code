package frc.robot.Subsystems;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

// IDK where the canSparkMax import is LOL


public class AlgaeBalls extends SubsystemBase{

 private final SparkMax throwLeftMotor;
 private final SparkMax throwRightMotor; 

 public AlgaeBalls(int throwLeftMotorID, int throwRightMotorID) {
    throwLeftMotor = new SparkMax(throwLeftMotorID, MotorType.kBrusless);
    throwRightMotor = new SparkMax(throwRightMotorID, MotorType.kBrushless);
 }

 public void setThrowSpeed() { //some pre determined speed 
   
 }

 public void setPickUpSpeed() {//some pre determind speed)
    
 }

 public void stop() {
    // set the throw motors to 0!
 }
   




}
