package frc.robot.subsystems;

import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SwerveConstants;

public class SwerveSubsystem extends SubsystemBase {
    // 4 Swerve modules used for each corner, ID's can be set with software, offset must be found with physical measurements

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


    public void stopModules() {
        frontLeft.stop();
        frontRight.stop();
        backLeft.stop();
        backRight.stop();
    }


    public void setModuleStates(SwerveModuleState[] desiredStates) {
        // Desaturate wheel speeds normalizes all the speeds, if theyre going faster than they physically can, divide by that number to get max speed of 1
        SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, SwerveConstants.kMaxMetersPerSecond);
        // Swerve module states each store an angle and speed, we're given an array of 4 states for each corner, give each state to each corner
        frontRight.setDesiredState(desiredStates[0]);
        frontLeft.setDesiredState(desiredStates[1]);
        backRight.setDesiredState(desiredStates[2]);
        backLeft.setDesiredState(desiredStates[3]);


        // For testing: values can be put on SmartDashboard, used for visualization
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
    }
}