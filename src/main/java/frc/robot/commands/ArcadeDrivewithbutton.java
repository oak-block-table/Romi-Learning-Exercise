package frc.robot.commands;

import java.io.Console;
import java.util.function.Supplier;

import edu.wpi.first.wpilibj.AnalogInput;
import frc.robot.subsystems.Drivetrain;

public class ArcadeDrivewithbutton extends ArcadeDrive {
    private final AnalogInput m_aInput;
  
    public ArcadeDrivewithbutton(
        Drivetrain drivetrain,
        Supplier<Double> xaxisSpeedSupplier,
        Supplier<Double> zaxisRotateSupplier,
        AnalogInput aInput) {

        super(drivetrain, xaxisSpeedSupplier, zaxisRotateSupplier);
        m_aInput = aInput;
    }
    
      // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        var button = m_aInput.getValue();
        System.out.println(button);
        if (button < 100 || button > 4999) {
            super.execute();
        }
        else {
            m_drivetrain.tankDrive(1.0,-0.49);
        }
    }

}
