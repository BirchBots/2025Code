package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TankSubsystem;

public class TankCommand extends Command {
    private final TankSubsystem tankSubsystem;
    private Supplier<Double> leftSpeedFunc, rightSpeedFunc;

    public TankCommand(TankSubsystem subsystem, Supplier<Double> leftSpeedFunc, Supplier<Double> rightSpeedFunc) {
        this.tankSubsystem = subsystem;
        this.leftSpeedFunc = leftSpeedFunc;
        this.rightSpeedFunc = rightSpeedFunc;
        
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        tankSubsystem.setLeft(leftSpeedFunc.get());
        tankSubsystem.setRight(rightSpeedFunc.get());
    }

    @Override
    public void end(boolean interrupted) {
        tankSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}