package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;

public final class Constants {

    public static final class SwerveConstants {
        public static final double kMaxMetersPerSecond = 4.6;
        
        public static final double kP = 0.3;
        public static final double kI = 0.0;
        public static final double kD = 0.14;

        public static final double kBotWidth = Units.inchesToMeters(30);
        public static final double kBotLength = Units.inchesToMeters(30);
        public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
            new Translation2d(kBotLength / 2, -kBotWidth / 2),
            new Translation2d(kBotLength / 2, kBotWidth / 2),
            new Translation2d(-kBotLength / 2, -kBotWidth / 2),
            new Translation2d(-kBotLength / 2, kBotWidth / 2));

        public static final int kFrTurnCAN = 1;
        public static final int kFrDriveCAN = 2;
        public static final int kBrTurnCAN = 3;
        public static final int kBrDriveCAN = 4;
        public static final int kBlTurnCAN = 5;
        public static final int kBlDriveCAN = 6;
        public static final int kFlTurnCAN = 7;
        public static final int kFlDriveCAN = 8;
        
        public static final int kFrEncoderId = 1;
        public static final int kBrEncoderId = 2;
        public static final int kBlEncoderId = 3;
        public static final int kFlEncoderId = 4;

        public static final double kFrOffset = -0.267;
        public static final double kFlOffset = 1.049;
        public static final double kBrOffset = -2.052;
        public static final double kBlOffset = -0.029;

        public static final double kTeleSlew = 3;
    }

    public static final class IOConstants {
        public static final int kDriverControllerPort = 0;
        public static final double kDeadband = 0.05;
    }

    // AprilTag dimensions (in meters)
    public static final double kAprilTagHeight = 0.165; // Standard AprilTag height for FRC
    
    // Camera mounting parameters
    public static final double kCameraHeight = 0.5; // Height of camera from floor in meters
    public static final double kCameraPitchRadians = Math.toRadians(20); // Camera angle (positive is up)
    
    // PID constants for target tracking
    public static final double kP_Rotation = 0.03;
    public static final double kI_Rotation = 0.0;
    public static final double kD_Rotation = 0.001;
    
    public static final double kP_Forward = 0.3;
    public static final double kI_Forward = 0.0;
    public static final double kD_Forward = 0.0;
    
    // Tolerances
    public static final double kAngleToleranceDegrees = 1.0;
    public static final double kDistanceToleranceMeters = 0.1;
}