package org.firstinspires.ftc.teamcode.pedro;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import  com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name = "LimeLight Camera", group = "LimeLight")
public class LimelightDetection extends OpMode {
    Limelight3A limelight;

    public void init() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.setPollRateHz(100); // This sets how often we ask Limelight for data (100 times per second)
        limelight.start(); // This tells Limelight to start looking!
        limelight.pipelineSwitch(3);
    }

    @Override
    public void loop() {
        LLResult result = limelight.getLatestResult();

        telemetry.addData("Target X",result.getTx());
        telemetry.addData("Target Y",result.getTy());
        telemetry.addData("Target Area",result.getTa());
    }
}