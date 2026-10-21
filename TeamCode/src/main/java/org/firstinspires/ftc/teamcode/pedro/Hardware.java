package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.follower.Follower;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
public class Hardware {

    private Follower follower;
    public IMU imu;
    public DcMotor lf; // Left Front
    public DcMotor rf; // Right Front
    public DcMotor lr; // Left Rear
    public DcMotor rr; // Right Rear

    public Limelight3A limelight;
    public Servo light ;
    public Servo light2 ;

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap hwMap) {

        // Save reference to Hardware map
        lf = hwMap.get(DcMotor.class, "lf");
        rf = hwMap.get(DcMotor.class, "rf");
        lr = hwMap.get(DcMotor.class, "lr");
        rr = hwMap.get(DcMotor.class, "rr");

        lf.setDirection(DcMotor.Direction.FORWARD);
        rf.setDirection(DcMotor.Direction.REVERSE);
        lr.setDirection(DcMotor.Direction.REVERSE);
        rr.setDirection(DcMotor.Direction.FORWARD);

        lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        limelight = hwMap.get(Limelight3A.class,"limelight");

        imu= hwMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot revHubOrientationOnRobot = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD);
        imu.initialize(new IMU.Parameters(revHubOrientationOnRobot));
    }
    public void setAllMotorPower(Double power){
        lf.setPower(-power);
        rf.setPower(power);
        lr.setPower(-power);
        rr.setPower(power);
    }
}


