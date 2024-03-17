package frc.robot.sensors;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class DistanceSensor {
    private final AnalogInput inputSignal;
  
    // Starting assumption: An input voltage of 5.0 V is 4.00 m and 0.0 V about 0.03 m.
    // TODO: This needs to be calibrated
    private final double METERS_PER_VOLT = 1.25;

    private static final double MESSAGE_INTERVAL = 4.0;
    private double m_nextMessageTime;
  
    /**
     * Constructor.
     *
     * @param analogChannel Specifies pin that provides an analog input from the sensor
     * Availability of I/O pins depends on the hardware "overlay"
     * that is specified when launching the wpilib-ws server on the Romi raspberry pi.
     * By default, the following are available:
     * - Analog In 0 (mapped to Analog Channel 6 / Arduino Pin 4)
     * - Analog In 1 (mapped to Analog Channel 2 / Arduino Pin 20)
     */
    public DistanceSensor(int analogChannel) {
        inputSignal = new AnalogInput(analogChannel);
        inputSignal.setAverageBits(2);
    }

    /** Gets a distance reading from the sensor. */
    public double getDistance() {
        double result = (inputSignal != null) ? 
                        inputSignal.getAverageVoltage() / METERS_PER_VOLT :
                        -1.0;

        double currentTime = Timer.getFPGATimestamp();
        if (currentTime > m_nextMessageTime) {
            if (inputSignal != null) {
                DriverStation.reportWarning(String.format("Distance sensor ouput: %.2f m", result), false);
            } else {
                DriverStation.reportError("Distance sensore was not configured", false);
            }
            m_nextMessageTime = currentTime + MESSAGE_INTERVAL;
        }

        return result;
    }

}
