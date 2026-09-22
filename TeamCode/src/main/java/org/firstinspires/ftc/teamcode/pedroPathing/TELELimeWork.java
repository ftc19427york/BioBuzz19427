package org.firstinspires.ftc.teamcode.pedroPathing;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Essentials.Constants;
import org.firstinspires.ftc.teamcode.Essentials.LLHardware;

import java.util.function.Supplier;


@TeleOp(name = "TELE PID LIME", group = "LimeLight")
public class TELELimeWork extends OpMode {
    LLHardware robot = new LLHardware();


    private Follower follower;
    public static Pose startingPose; //See ExampleAuto to understand how to use this
    private boolean automatedDrive;
    private Supplier<PathChain> pathChain;
    private TelemetryManager telemetryM;
    private boolean slowMode = false;
    private double slowModeMultiplier = 0.5;
    // 1. Create a timer to measure dt
    private ElapsedTime timer = new ElapsedTime();

    // PID Tuning Variables (You will adjust these during testing)
    private double Kp = 0.047;
    private double Ki = 0.01;
    private double Kd = 0.002;

    private double integral = 0;
    private double lastError = 0;
    Limelight3A limelight;


    @Override
    public void init() {
        robot.init(hardwareMap);
        follower = Constants.createFollower(hardwareMap);
        follower.startTeleOpDrive();
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.setPollRateHz(100); // This sets how often we ask Limelight for data (100 times per second)
        limelight.start(); // This tells Limelight to start looking!
        limelight.pipelineSwitch(0);
        LLResult result = limelight.getLatestResult();
        follower.setStartingPose(startingPose == null ? (new Pose(8.125, 8.875, 0)) : startingPose);
        telemetryM = PanelsTelemetry.INSTANCE.getTelemetry();
    }


    @Override
    public void start() {
        //The parameter controls whether the Follower should use break mode on the motors (using it is recommended).
        //In order to use float mode, add .useBrakeModeInTeleOp(true); to your Drivetrain Constants in Constant.java (for Mecanum)
        //If you don't pass anything in, it uses the default (false)
        follower.startTeleopDrive();
        follower.update();


    }

    @Override
    public void loop() {
        follower.update();
        timer.reset(); // Reset immediately so it measures the next loop
        LLResult result = limelight.getLatestResult();
            follower.update();



            //PID
            double dt = timer.seconds();
            timer.reset();
            if (dt <= 0) {
                dt = 0.001;
            }


            double processVariable = result.getTy(); // Get your camera data
            double setpoint = 0; // Target is center
            double error = setpoint - processVariable;
            integral += error * dt;
            double derivative = (error - lastError) / dt;


            double PIDVariable = (Kp * error) + (Ki * integral) + (Kd * derivative);

            lastError = error;
            //PID
//
//            double drive = gamepad1.left_stick_y;
//            double strafe = gamepad1.left_stick_x;
//            double turn = -gamepad1.right_stick_x;

//            if(gamepad1.right_bumper){
//                if (result.isValid()) {
//                    turn = PIDVariable;
//                } else {
//                    // Fallback: If button is held but tag is lost, keep integral clear
//                    integral = 0;
//                    lastError = 0;
//                }
//            } else {
//                // If button isn't held, clear PID history so it doesn't jump later
//                integral = 0;
//                lastError = 0;
//            }

        if (!automatedDrive) {
            //Make the last parameter false for field-centric
            //In case the drivers want to use a "slowMode" you can scale the vectors


            if (gamepad2.right_bumper) follower.setTeleOpDrive(
                    -gamepad2.left_stick_y,
                    -gamepad2.left_stick_x,
                    PIDVariable,
                    true // Robot Centric
            );

                //This is how it looks with slowMode on
            else follower.setTeleOpDrive(
                    gamepad2.left_stick_y * slowModeMultiplier,
                    gamepad2.left_stick_x * slowModeMultiplier,
                    -gamepad2.right_stick_x * slowModeMultiplier,
                    true // Robot Centric
            );
        }


//            follower.setTeleOpDrive(drive, strafe, turn);

            //Data
            telemetry.addData("Target X", result.getTx());
            telemetry.addData("Target Y", result.getTy());
            telemetry.addData("Target Area", result.getTa());
            telemetry.addData("Loop Time (dt)", dt);



        }
        // Placeholder function for your vision data

    }

