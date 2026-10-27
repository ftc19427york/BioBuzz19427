package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.follower.Follower;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.util.Arrays;

@Autonomous(name = "Get Ball Position", group = "math")
public class GetBallPos extends OpMode {

    double cameraHeight = 0.5;

    double cameraPitch = 0;

    double cameraXOffset = 0;
    double cameraYOffset = 0;

    double targetHeight = 0;

    LLResult data;
    private Limelight3A limelight;
    private Follower follower;

    @Override
    public void init() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        follower = Constants.createFollower(hardwareMap);

        limelight.setPollRateHz(100); // This sets how often we ask Limelight for data (100 times per second)
        limelight.start(); // This tells Limelight to start looking!
        limelight.pipelineSwitch(0);
    }

    @Override
    public void loop() {
        data = limelight.getLatestResult();

        double tX = data.getTx();
        double tY = data.getTy();

        double robotX = follower.pose().x();
        double robotY = follower.pose().y();

        double robotHeading = follower.pose().heading();

        double txRadians = tX * 0.0174533; // converts tX degrees to Radians
        double tyRadians = tY * 0.0174533; // converts tY degrees to Radians

        double angleToTarget = cameraPitch + tyRadians;
        double groundDist = (targetHeight - cameraHeight) / Math.tan(angleToTarget);

// Calculate robot-relative coordinates
        double targetXRobot = (groundDist * Math.cos(txRadians)) + cameraXOffset;
        double targetYRobot = (-groundDist * Math.sin(txRadians)) + cameraYOffset;

// Transform to field-relative coordinates
        double targetXField = robotX + (targetXRobot * Math.cos(robotHeading) - targetYRobot * Math.sin(robotHeading));
        double targetYField = robotY + (targetXRobot * Math.sin(robotHeading) + targetYRobot * Math.cos(robotHeading));

        telemetry.addLine("Bot");
        telemetry.addData("Bot Position",follower.pose());

        telemetry.addLine("Target");
        telemetry.addData("Ball Position X", targetXField);
        telemetry.addData("Ball Position Y", targetYField);

        telemetry.addLine("Debug");
        telemetry.addData("tX",tX);
        telemetry.addData("tY",tY);
        telemetry.addData("robotX",robotX);
        telemetry.addData("robotY",robotY);
        telemetry.addData("robotHeading",robotHeading);
        telemetry.addData("targetXRobot",targetXRobot);
        telemetry.addData("targetYRobot",targetYRobot);

        telemetry.update();
        follower.update();
    }
}
