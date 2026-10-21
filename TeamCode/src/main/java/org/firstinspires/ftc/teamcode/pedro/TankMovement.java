package org.firstinspires.ftc.teamcode.pedro;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Tank Movement", group = "Examples")
public class TankMovement extends OpMode {

    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor rearLeftMotor;
    private DcMotor rearRightMotor;

    private DcMotor bucketMotor;

    @Override
    public void init() {
        // Map motors from configuration
        frontLeftMotor = hardwareMap.get(DcMotor.class, "lf");
        frontRightMotor = hardwareMap.get(DcMotor.class, "rf");
        rearLeftMotor = hardwareMap.get(DcMotor.class, "rr");
        rearRightMotor = hardwareMap.get(DcMotor.class, "lr");

        bucketMotor = hardwareMap.get(DcMotor.class, "bucket");

        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        rearLeftMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        double leftPower = -gamepad1.right_stick_y;
        double rightPower = -gamepad1.left_stick_y;

        frontLeftMotor.setPower(leftPower);
        frontRightMotor.setPower(rightPower);
        rearLeftMotor.setPower(leftPower);
        rearRightMotor.setPower(rightPower);

        telemetry.addData("Left Power", leftPower);
        telemetry.addData("Right Power", rightPower);
        telemetry.update();

        if (gamepad1.left_trigger >= 0.01) {
            bucketMotor.setPower(1);
        } else {
            bucketMotor.setPower(0);
        }
    }
}
