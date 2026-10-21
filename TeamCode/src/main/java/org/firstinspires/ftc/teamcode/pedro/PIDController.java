
package org.firstinspires.ftc.teamcode.pedro;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public class PIDController extends OpMode {
    Hardware robot = new Hardware();

    // 1. Create a timer to measure dt
    private ElapsedTime timer = new ElapsedTime();

    private double integral;
    private double lastError;

    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {

    }

    public double processPID(double processVariable, double Kp, double Ki,double Kd, double setpoint) {
        double dt = timer.seconds();

        timer.reset(); // Reset immediately so it measures the next loop

        if (dt <= 0) { // Protect against a 0 or negative dt (safety fallback)
            dt = 0.001;
        }

        double pidOutput = calculatePID(setpoint, processVariable, Kp, Ki, Kd, dt);

        return pidOutput;
    }
    private double calculatePID(double setpoint,double processVariable,double Kp,double Ki,double Kd,double dt) {
        // Main PID Calculation
        double error = setpoint - processVariable;
        integral += error * dt;

        double derivative = (error - lastError) / dt;
        double pidOutput = (Kp * error) + (Ki * integral) + (Kd * derivative);

        lastError = error;
        timer.reset();

        return pidOutput;
    }
}