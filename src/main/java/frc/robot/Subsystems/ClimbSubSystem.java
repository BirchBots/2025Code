import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSubSystem extends SubsystemBase{
    private final static SparkMax ClimbMotor = new SparkMax(0,MotorType.kBrushless);
        public static void up() {
            ClimbMotor.set(0.8);
    } public static void down() {
        ClimbMotor.set(-0.8);
    }
}
