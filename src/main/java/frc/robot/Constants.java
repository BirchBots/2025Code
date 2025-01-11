package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;

// Physical constants, need to be found

public final class Constants {

    public static final class SwerveConstants {
        public static final double kRPM = 5676;
        public static final double kMaxAngularRadPerSecond = kRPM*Math.PI/30;
        public static final double kMaxMetersPerSecond = 4.6;
        public static final double kDriveGearRatio = 6.75;
        public static final double kTurnGearRatio = 6.75;
<<<<<<< Updated upstream
        public static final double kPTurning = 0.5;
=======
        public static final double kP = 0.35;
        public static final double kI = 0.0;
        public static final double kD = 0.08;
        public static final double kF = 0.1;
>>>>>>> Stashed changes

        public static final double kTrackWidth = Units.inchesToMeters(30);
        // Distance between right and left wheels
        public static final double kWheelBase = Units.inchesToMeters(30);
        // Distance between front and back wheels
        public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
                new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
                new Translation2d(kWheelBase / 2, kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, -kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, kTrackWidth / 2));

<<<<<<< Updated upstream
        public static final int kFlDriveCAN = 5; 
        public static final int kBlDriveCAN = 1; 
        public static final int kFrDriveCAN = 3;
        public static final int kBrDriveCAN = 7;
            
        public static final int kFlTurnCAN = 6;
        public static final int kBlTurnCAN = 2;
        public static final int kFrTurnCAN = 4;
        public static final int kBrTurnCAN = 8;
        
        public static final int kFlEncoderId = 0;
        public static final int kFrEncoderId = 1;
        public static final int kBlEncoderId = 3;
        public static final int kBrEncoderId = 2;
=======
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
>>>>>>> Stashed changes

        public static final double kFlOffset = 0;
        public static final double kFrOffset = 0;
        public static final double kBlOffset = 0;
        public static final double kBrOffset = 0;

        public static final double kTeleMetersPerSec = kMaxMetersPerSecond / 4;
        public static final double kTeleRadsPerSec = kMaxAngularRadPerSecond / 4; 

        public static final double kTeleSlew = 3;
        public static final double kTeleAngularSlew = 3;
    }

    public static final class AutoConstants {
        public static final double kMaxSpeedMetersPerSecond = SwerveConstants.kMaxMetersPerSecond / 4;
        public static final double kMaxAngularSpeedRadiansPerSecond = //
                SwerveConstants.kMaxAngularRadPerSecond / 10;
        public static final double kMaxAccelerationMetersPerSecondSquared = 3;
        public static final double kMaxAngularAccelerationRadiansPerSecondSquared = Math.PI / 4;
        public static final double kPXController = 1.5;
        public static final double kPYController = 1.5;
        public static final double kPThetaController = 3;

        public static final TrapezoidProfile.Constraints kThetaControllerConstraints = //
                new TrapezoidProfile.Constraints(
                        kMaxAngularSpeedRadiansPerSecond,
                        kMaxAngularAccelerationRadiansPerSecondSquared);
    }

    public static final class IOConstants {
        public static final int kDriverControllerPort = 0;

        public static final double kDeadband = 0.05;
    }
}