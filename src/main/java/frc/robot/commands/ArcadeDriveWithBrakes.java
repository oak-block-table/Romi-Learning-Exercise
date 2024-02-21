package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import java.util.function.Supplier;

public class ArcadeDriveWithBrakes extends ArcadeDrive {
    private final Supplier<Double> m_leftBrakeSupplier;
    private final Supplier<Double> m_rightBrakeSupplier;
  
    public ArcadeDriveWithBrakes(
        Drivetrain drivetrain,
        Supplier<Double> xaxisSpeedSupplier,
        Supplier<Double> zaxisRotateSupplier,
        Supplier<Double> leftBrakeSupplier,
        Supplier<Double> rightBrakeSupplier) {

        super(drivetrain, xaxisSpeedSupplier, zaxisRotateSupplier);
        m_leftBrakeSupplier = leftBrakeSupplier;
        m_rightBrakeSupplier = rightBrakeSupplier;
    }
    
      // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        var leftBrakeFactor = m_leftBrakeSupplier.get();
        var rightBrakeFactor = m_rightBrakeSupplier.get();
        if (Math.abs(leftBrakeFactor) < 0.06 && Math.abs(rightBrakeFactor) < 0.06) {
            super.execute();
        }
        else {
            m_drivetrain.tankDrive(m_xaxisSpeedSupplier.get() * (1-0.46*leftBrakeFactor), 
                                   m_xaxisSpeedSupplier.get() * (1-0.46*rightBrakeFactor));
        }
    }

}
