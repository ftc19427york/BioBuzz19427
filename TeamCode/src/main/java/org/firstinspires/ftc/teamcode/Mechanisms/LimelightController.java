package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import  com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class LimelightController extends OpMode {
    Limelight3A limelight;
    /* Available Pipelines:
    0: apriltag_detection_23
    1: blue_nectar_detection
    2: red_nectar_detection
    3: pollen_detection
    */

    @Override
    public void init() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.setPollRateHz(100); // This sets how often we ask Limelight for data (100 times per second)
        limelight.start(); // This tells Limelight to start looking!
        limelight.pipelineSwitch(0);
    }

    @Override
    public void loop() {
//        LLResult result = limelight.getLatestResult();
//        telemetry.addData("Target X",result.getTx());
//        telemetry.addData("Target Y",result.getTy());
//        telemetry.addData("Target Area",result.getTa());
    }

    public LLResult requestData() {
        return limelight.getLatestResult();
    }

    public void changePipeline(int pipeline) {
        limelight.pipelineSwitch(pipeline);
    }
}