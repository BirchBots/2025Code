package frc.robot.Subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimelightSubsystem extends SubsystemBase {
    // Network table for Limelight data
    private final NetworkTable limelightTable;
    
    // Key Limelight values
    private NetworkTableEntry tx; // Horizontal offset from crosshair to target (-29.8 to 29.8 degrees)
    private NetworkTableEntry ty; // Vertical offset from crosshair to target (-24.85 to 24.85 degrees)
    private NetworkTableEntry ta; // Target area (0% to 100% of image)
    private NetworkTableEntry tv; // Target valid (0 or 1)
    private NetworkTableEntry tid; // AprilTag ID (-1 if no tag detected)
    
    public LimelightSubsystem() {
        // Get the Limelight network table
        limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
        
        // Initialize network table entries
        tx = limelightTable.getEntry("tx");
        ty = limelightTable.getEntry("ty");
        ta = limelightTable.getEntry("ta");
        tv = limelightTable.getEntry("tv");
        tid = limelightTable.getEntry("tid");
        
        // Set Limelight to AprilTag pipeline
        setPipeline(0); // Assuming pipeline 0 is configured for AprilTags
    }
    
    /**
     * Set the Limelight's operation mode
     * @param mode 0 for normal vision, 1 for driver camera mode
     */
    public void setCameraMode(int mode) {
        limelightTable.getEntry("camMode").setNumber(mode);
    }
    
    /**
     * Set the Limelight's LED state
     * @param state 0 for off, 1 for on, 2 for blink
     */
    public void setLEDMode(int state) {
        limelightTable.getEntry("ledMode").setNumber(state);
    }
    
    /**
     * Set the Limelight's pipeline
     * @param pipeline Pipeline number (0-9)
     */
    public void setPipeline(int pipeline) {
        limelightTable.getEntry("pipeline").setNumber(pipeline);
    }
    
    /**
     * Check if a valid target is detected
     * @return True if target is detected
     */
    public boolean isTargetDetected() {
        return tv.getDouble(0) == 1;
    }
    
    /**
     * Get the AprilTag ID that is currently detected
     * @return AprilTag ID, or -1 if no tag detected
     */
    public int getAprilTagId() {
        return (int) tid.getDouble(-1);
    }
    
    /**
     * Get horizontal offset angle from crosshair to target
     * @return Angle in degrees (-29.8 to 29.8)
     */
    public double getHorizontalOffset() {
        return tx.getDouble(0);
    }
    
    /**
     * Get vertical offset angle from crosshair to target
     * @return Angle in degrees (-24.85 to 24.85)
     */
    public double getVerticalOffset() {
        return ty.getDouble(0);
    }
    
    /**
     * Get target area as percentage of image
     * @return Area from 0% to 100%
     */
    public double getTargetArea() {
        return ta.getDouble(0);
    }
    
    /**
     * Estimate distance to target using the target's vertical offset
     * @param targetHeightMeters Height of the AprilTag in meters
     * @param cameraHeightMeters Height of the camera from the floor in meters
     * @param cameraPitchRadians Camera pitch in radians (positive is up)
     * @return Estimated distance to target in meters
     */
    public double estimateDistance(double targetHeightMeters, double cameraHeightMeters, double cameraPitchRadians) {
        // Vertical offset in radians
        double verticalOffsetRadians = Math.toRadians(getVerticalOffset());
        
        // Calculate distance using trigonometry
        return (targetHeightMeters - cameraHeightMeters) / 
               Math.tan(cameraPitchRadians + verticalOffsetRadians);
    }
    
    @Override
    public void periodic() {
        // Optional: Put useful Limelight data on the SmartDashboard
        // SmartDashboard.putBoolean("Target Detected", isTargetDetected());
        // SmartDashboard.putNumber("AprilTag ID", getAprilTagId());
        // SmartDashboard.putNumber("Target Horizontal Offset", getHorizontalOffset());
        // SmartDashboard.putNumber("Target Distance", estimateDistance(targetHeight, cameraHeight, cameraPitch));
    }
}