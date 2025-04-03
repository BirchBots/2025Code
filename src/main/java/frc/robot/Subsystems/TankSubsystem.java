package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

public class TankSubsystem extends SubsystemBase {
    private final SparkMax flMotor = new SparkMax(1, MotorType.kBrushless);
    private final SparkMax blMotor = new SparkMax(2, MotorType.kBrushless);
    private final SparkMax frMotor = new SparkMax(3, MotorType.kBrushless);
    private final SparkMax brMotor = new SparkMax(4, MotorType.kBrushless);

    public void setLeft(double speed) {
        flMotor.set(speed);
        blMotor.set(speed);
    }

    public void setRight(double speed) {
        frMotor.set(speed);
        brMotor.set(speed);
    }

    public void stop() {
        frMotor.set(0);
        flMotor.set(0);
        brMotor.set(0);
        blMotor.set(0);
    }
}