// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.sensors.DistanceSensor;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.Supplier;

// TODO: This is prototype code that was never tested due to hardware issues.
public class AssistedFollowWall extends CommandBase {
    protected final Drivetrain drivetrain;
    private final DistanceSensor distanceSensor;
    protected final Supplier<Double> speedSupplier;

    private double targetDistance_meters = 0.1;

    /**
      * Creates a new Assisted Drive that will attempt to follow a fixed distance
      * from a wall.
      *
      * @param drivetrain The drivetrain subsystem on which this command will run
      * @param sensor The sensor that tells us the distance to the wall
      * @param xaxisSpeedSupplier Lambda supplier of forward/backward speed
      */
    public AssistedFollowWall(Drivetrain drivetrain, 
                              DistanceSensor sensor,
                              Supplier<Double> xaxisSpeedSupplier) {
        this.drivetrain = drivetrain;
        distanceSensor = sensor;
        speedSupplier = xaxisSpeedSupplier;
        addRequirements(drivetrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        targetDistance_meters = distanceSensor.getDistance();
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      double distanceError = 1 - distanceSensor.getDistance()/targetDistance_meters;
      double suppliedSpeed = speedSupplier.get();
      double leftSpeed  = (distanceError < -0.1) ? suppliedSpeed*0.7 : suppliedSpeed;
      double rightSpeed = (distanceError > 0.1)  ? suppliedSpeed*0.7 : suppliedSpeed;
      drivetrain.tankDrive(leftSpeed, rightSpeed);
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }
 
}
