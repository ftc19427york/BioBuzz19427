package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Essentials.LLHardware;
import org.firstinspires.ftc.teamcode.Mechanisms.LimelightController;

@TeleOp(name = "Move Towards Type", group = "Limelight")
public class MoveTowardsType extends OpMode {
    LimelightController llDetection = new LimelightController();
    Follower follower;
    LLHardware robot = new LLHardware();

    boolean driverControlled; // True if being controlled via controller
    @Override
    public void init() {

    }

    @Override
    public void loop() {
        if (gamepad2.circleWasPressed()) {
            driverControlled = false;
        } else {
            driverControlled = true;
        }

        if (driverControlled) {
            follower.setTeleOpDrive(
                    gamepad2.left_stick_y,
                    gamepad2.left_stick_x,
                    gamepad2.right_stick_x,
                    true // Robot Centric
            );
        } else {
            MoveTowards("pollen");
        }

        telemetry.addData("Driver Controlled:", driverControlled);
    }
    public void MoveTowards(String type) {
        LLResult data;
        switch (type) {
            case "pollen": {
                llDetection.changePipeline(3);
                break;
            } case "red": {
                llDetection.changePipeline(2);
                break;
            } case "blue": {
                llDetection.changePipeline(1);
                break;
            } default: {
                return;
            }
        }
        llDetection.requestData();
        // Turn and drive towards data
    }
}
