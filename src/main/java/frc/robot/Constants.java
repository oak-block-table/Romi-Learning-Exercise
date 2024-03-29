// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // We considered using this to remove the deadband when calibrating Auto sequences
    // However, this factor is probably smaller than the contribution of assumptions 
    // that we made during this exercise.  So, we are not adding in this correction for now.
    public final static double DeadbandCompensationForAutos = 0.02;
    
    // Attenuates power sent to one wheel or the other 
    // Range: (-1.0 to +1.0) - positive values slow down the left
    public final static double WheelSpeedMatchingAttenuationFactor =  0.045;

    // Scale: -1.0 and +1.0 represent maximum reverse and forward motor speeds
    public final static double CalibrationDriveSpeed = -0.4;
    public final static double CalibrationDriveDistanceInches = 10;
}
