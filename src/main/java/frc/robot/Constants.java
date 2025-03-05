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
}