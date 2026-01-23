package frc.robot.Subsystems;

import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SwerveConstants;

public class SwerveSubsystem extends SubsystemBase {
    private final SwerveModule frontLeft = new SwerveModule(
        SwerveConstants.kFlDriveCAN,
        SwerveConstants.kFlTurnCAN,
        SwerveConstants.kFlEncoderId,
        SwerveConstants.kFlOffset);

    private final SwerveModule frontRight = new SwerveModule(
        SwerveConstants.kFrDriveCAN,
        SwerveConstants.kFrTurnCAN,
        SwerveConstants.kFrEncoderId,
        SwerveConstants.kFrOffset);

    private final SwerveModule backLeft = new SwerveModule(
        SwerveConstants.kBlDriveCAN,
        SwerveConstants.kBlTurnCAN,
        SwerveConstants.kBlEncoderId,
        SwerveConstants.kBlOffset);

    private final SwerveModule backRight = new SwerveModule(
        SwerveConstants.kBrDriveCAN,
        SwerveConstants.kBrTurnCAN,
        SwerveConstants.kBrEncoderId,
        SwerveConstants.kBrOffset);

/* 
    public void stopModules() {
        frontLeft.stop();
        frontRight.stop();
        backLeft.stop();
        backRight.stop();
    }
*/

    public void setModuleStates(SwerveModuleState[] states) {
    
        SwerveDriveKinematics.desaturateWheelSpeeds(states, SwerveConstants.kMaxMetersPerSecond);
    
        // Apply WPILib optimization using the actual module angle
        states[0] = SwerveModuleState.optimize(states[0], frontLeft.getAngle());
        states[1] = SwerveModuleState.optimize(states[1], frontRight.getAngle());
        states[2] = SwerveModuleState.optimize(states[2], backLeft.getAngle());
        states[3] = SwerveModuleState.optimize(states[3], backRight.getAngle());
    
        // Send optimized states to modules
        frontLeft.setTargetState(states[0]);
        frontRight.setTargetState(states[1]);
        backLeft.setTargetState(states[2]);
        backRight.setTargetState(states[3]);
    }



/* 
        double[] desired_states = new double[] {
            desiredStates[0].angle.getRadians(),
            desiredStates[0].speedMetersPerSecond,
            desiredStates[1].angle.getRadians(),
            desiredStates[1].speedMetersPerSecond,
            desiredStates[2].angle.getRadians(),
            desiredStates[2].speedMetersPerSecond,
            desiredStates[3].angle.getRadians(),
            desiredStates[3].speedMetersPerSecond
        };
        SmartDashboard.putNumberArray("desired", desired_states);
        double[] actual_states = new double[] {
            frontRight.getState().angle.getRadians(),
            frontRight.getState().speedMetersPerSecond,
            frontLeft.getState().angle.getRadians(),
            frontLeft.getState().speedMetersPerSecond,
            backRight.getState().angle.getRadians(),
            backRight.getState().speedMetersPerSecond,
            backLeft.getState().angle.getRadians(),
            backLeft.getState().speedMetersPerSecond
        };
        SmartDashboard.putNumberArray("actual", actual_states);

        */
}
