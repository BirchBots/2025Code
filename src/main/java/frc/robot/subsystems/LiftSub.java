
package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

public class LiftSub {


    /*PLACE HOLDER NUMBER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * !!!!!!!!!!!!!!!*/
    static int placeNumber;
    
    
    
    //fix device ID
    public final static SparkMax leftLiftMotor = new SparkMax(placeNumber, MotorType.kBrushless);
    //fix device ID
    public final static SparkMax rightLiftMotor = new SparkMax(placeNumber, MotorType.kBrushless);

    RelativeEncoder leftLiftEncoder = leftLiftMotor.getEncoder();
    RelativeEncoder rightLiftEncoder = rightLiftMotor.getEncoder();
            
    //variable initiation
    int leftLiftMotorSpeed;
    int rightLiftMotorSpeed = leftLiftMotorSpeed * -1;

    int numbOfMotorRotations;

    int liftHight = numbOfMotorRotations/placeNumber;


    static void raiseLift() {
        
        while (liftHight < placeNumber){

            leftLiftMotor.set(-0.69);
            rightLiftMotor.set(0.69);

        }
    }

    static void lowerLift(){

        while (liftHight < placeNumber){

            leftLiftMotor.set(0.69);
            rightLiftMotor.set(-0.69);

        }
    }
}

