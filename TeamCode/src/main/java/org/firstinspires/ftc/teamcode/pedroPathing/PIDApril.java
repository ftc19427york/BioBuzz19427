
package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Essentials.Constants;
import org.firstinspires.ftc.teamcode.Essentials.LLHardware;


@TeleOp(name = "PID Limelight", group = "LimeLight")
public class PIDApril extends OpMode {
    LLHardware robot = new LLHardware ();
    // 1. Create a timer to measure dt

    private ElapsedTime timer = new ElapsedTime();

    // PID Tuning Variables (You will adjust these during testing)
    private double Kp = 0.058;
    private double Ki = 0.01;
    private double Kd = 0.002;

    private double integral = 0;
    private double lastError = 0;
    private Follower follower;
    private double slowModeMultiplier = 0.5;
    private boolean automatedDrive;
    private boolean slowMode = false;

    Limelight3A limelight;

    public void init() {
        robot.init(hardwareMap);
        follower = Constants.createFollower(hardwareMap);
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.setPollRateHz(100); // This sets how often we ask Limelight for data (100 times per second)
        limelight.start(); // This tells Limelight to start looking!
        limelight.pipelineSwitch(0);
    }

    @Override
    public void loop() {
        LLResult result = limelight.getLatestResult();
        double dt = timer.seconds();
        timer.reset(); // Reset immediately so it measures the next loop

        // Protect against a 0 or negative dt (safety fallback)
        if (dt <= 0) {
            dt = 0.001;
        }

        // --- Your Vision Data ---
        // Replace 'getAprilTagXOffset()' with your actual camera tracking call
        double processVariable = result.getTy();
        double setpoint = 0; // Target is centered (0 offset)

        // --- PID Calculations Using dt ---
        double error = setpoint - processVariable;

        integral += error * dt;
        double derivative = (error - lastError) / dt;

        double pidOutput = (Kp * error) + (Ki * integral) + (Kd * derivative);

        lastError = error;
        timer.reset();


        if (!automatedDrive) {
            if (!slowMode) follower.setTeleOpDrive(
                    -gamepad2.left_stick_y,
                    -gamepad2.left_stick_x,
                    -gamepad2.right_stick_x,
                    true // Robot Centric
            );
                //This is how it looks with slowMode on
            else follower.setTeleOpDrive(
                    gamepad2.left_stick_y * slowModeMultiplier,
                    gamepad2.left_stick_x * slowModeMultiplier,
                    gamepad2.right_stick_x * slowModeMultiplier,
                    true // Robot Centric
            );
        }
//        robot.setAllMotorPower(pidOutput);

        //Data
        telemetry.addData("Target X",result.getTx());
        telemetry.addData("Target Y",result.getTy());
        telemetry.addData("Target Area",result.getTa());
        telemetry.addData("Loop Time (dt)", dt);
        telemetry.addData("PID Output", pidOutput);
        telemetry.update();
    }
    // Placeholder function for your vision data

}
