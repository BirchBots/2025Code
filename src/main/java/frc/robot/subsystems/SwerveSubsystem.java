package frc.robot.subsystems;

import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SwerveConstants;

public class SwerveSubsystem extends SubsystemBase {
        // Swerve module defined in subsystems, class to represesnt each corner
        // Parameters: drive motor id, turn motor id, absolute encoder id, absolute encoder offset
        // These can all be physically found on the components or in rev software (will show u)
        // Repeat x4

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
        //When u wanna stop the swerve modules, stop the damn swerve modules
        frontLeft.stop();
        frontRight.stop();
        backLeft.stop();
        backRight.stop();
    }


    public void setModuleStates(SwerveModuleState[] desiredStates) {
        // Desaturate wheel speeds normalizes all the speeds, if theyre going faster than they physically can, divide by that number to get max speed of 1
        SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, SwerveConstants.kMaxMetersPerSecond);
        // Swerve module states each store an angle and speed, were given an array of 4 states for each corner, give each state to each corner
        frontRight.setDesiredState(desiredStates[0]);
        frontLeft.setDesiredState(desiredStates[1]);
        backRight.setDesiredState(desiredStates[2]);
        backLeft.setDesiredState(desiredStates[3]);
        // Create an array of numbers with the rotation and speeds (what they should be, NOT WHAT THEY ARE)
        double[] desired_states = new double[] {
                frontRight.getDesiredState().angle.getRadians(),
                frontRight.getDesiredState().speedMetersPerSecond,
                frontLeft.getDesiredState().angle.getRadians(),
                frontLeft.getDesiredState().speedMetersPerSecond,
                backRight.getDesiredState().angle.getRadians(),
                backRight.getDesiredState().speedMetersPerSecond,
                backLeft.getDesiredState().angle.getRadians(),
                backLeft.getDesiredState().speedMetersPerSecond
              };
        // SmartDashboard is just a place to put data, this can be obtained from simulation software, put speeds/ rotations
        SmartDashboard.putNumberArray("states", desired_states);
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

    @Override
    public void periodic() {}
}