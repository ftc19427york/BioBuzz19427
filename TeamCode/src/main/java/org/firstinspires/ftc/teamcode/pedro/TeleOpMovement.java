package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@TeleOp(group = "test", name = "TeleOp Movement")
public class TeleOpMovement extends OpMode {
    private Follower follower;

    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);
    }

    @Override
    public void loop() {
        double forward = gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;

        DrivePowers drive = ManualDrive.fieldCentric(
                forward,
                strafe,
                turn,
                follower.pose().heading()
        );

        follower.manual(drive);
        follower.update();

        telemetry.addData("Heading",follower.pose().heading());
        telemetry.addData("Position",follower.pose());
    }
}
