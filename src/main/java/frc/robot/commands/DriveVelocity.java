// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveVelocity extends CommandBase {
  private final Drivetrain m_drive;
  private final double m_minimumVelocity;
  private final double m_speed;
  private boolean reachedSpeed = false;

  /**
   * Creates a new DriveDistance. This command will drive your your robot for a desired distance at
   * a desired speed.
   *
   * @param speed The speed at which the robot will drive
   * @param minimumVelocity The number of inches the robot will drive
   * @param drive The drivetrain subsystem on which this command will run
   */
  public DriveVelocity(double speed, double minimumVelocity, Drivetrain drive) {
    m_minimumVelocity = minimumVelocity;
    m_speed = speed;
    m_drive = drive;
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    reachedSpeed = false;
    m_drive.arcadeDrive(0, 0);
    m_drive.resetEncoders();
    logCurrentState();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.arcadeDriveCalibrated(m_speed, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.arcadeDrive(0, 0);
    logCurrentState();
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.println(String.format(
        "Left Encoder = %6d | Right Encoder = %6d | Accel X speed = %6.2f | Has reached? = %6b" ,
        m_drive.getLeftEncoderCount(), m_drive.getRightEncoderCount(), m_drive.getAccelX(), reachedSpeed));
    // Compare distance travelled from start to desired distance
    // reachedSpeed = (m_drive.getAccelX() >= m_minimumVelocity) || (reachedSpeed);
    return m_drive.getAccelX() <= -0.11;
    // return (m_drive.getAccelX() <= 0.02) && (reachedSpeed);
  }

  private void logCurrentState() {
    System.out.println(String.format(
        "Left Encoder = %6d | Right Encoder = %6d | Gyro Z angle = %6.2f" ,
        m_drive.getLeftEncoderCount(), m_drive.getRightEncoderCount(), m_drive.getGyroAngleZ()));
  }
}
