package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.pedro.PIDController;

@TeleOp(name = "Move Towards Type", group = "Limelight")
public class MoveTowardsType extends OpMode {
    Limelight3A limelight;
    PIDController pidController = new PIDController();
    Hardware robot = new Hardware();
    private Follower follower;
    boolean driverControlled; // True if being controlled via controller
    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);
        limelight = hardwareMap.get(Limelight3A.class, "limelight");

        limelight.setPollRateHz(100); // This sets how often we ask Limelight for data (100 times per second)
        limelight.start(); // This tells Limelight to start looking!
        limelight.pipelineSwitch(0);
    }

    @Override
    public void loop() {
        LLResult result = limelight.getLatestResult();
        //if (driverControlled) {

        //} else {
            MoveTowards("pollen");
        //}

        telemetry.addData("Driver Controlled", driverControlled);
    }
    public void MoveTowards(String type) {
        LLResult data;
        switch (type) {
            case "pollen": {
                limelight.pipelineSwitch(3);
                break;
            } case "red": {
                limelight.pipelineSwitch(2);
                break;
            } case "blue": {
                limelight.pipelineSwitch(1);
                break;
            } default: {
                return;
            }
        }
        data = limelight.getLatestResult();
        double pidOutput = pidController.processPID(data.getTx(),0.01,0,0,0);

        telemetry.addData("Target X",data.getTx());
        telemetry.addData("Target Y",data.getTy());
        telemetry.addData("Target Area",data.getTa());
        telemetry.addData("PID Output",pidOutput);

        double area = data.getTa();
        double forwardSpeed = (1/area)/3;
        int tick = 0;

        DrivePowers powers = new DrivePowers(0,0,0);

        tick += 1;
        if (area != 0 && tick > 10) {
            if (forwardSpeed >= 0.4) {
                forwardSpeed = 0.4;
            }
            if (data.isValid() == true)
                {
                    if (area <= 3 && ) {
                    limelight.pipelineSwitch(0);
                }
                }

           else
                {
                    limelight.pipelineSwitch(1);
                }

            double strafe = 0;
            double turn = -pidOutput;

            powers = new DrivePowers(-forwardSpeed,strafe,turn);
            follower.manual(powers);
            tick += 1;
        } else {
            follower.manual(0,0,0);
            tick = 0;
        }
        
        follower.update();



        telemetry.addData("Forward",forwardSpeed);
        telemetry.update();
    }
}
