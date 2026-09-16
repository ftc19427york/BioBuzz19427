/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.Mechanisms; /**This is the folder path of where this file is located. */

//import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

        import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class MecanumDrive {

    //Giving names to each device
    public DcMotor lift = null;

    public DcMotor lr;
    public DcMotor rf;
    public DcMotor rr;
    public DcMotor lf;
    public DcMotor tilt = null;
    public Servo panClaw = null;
    public Servo claw = null;
    public Servo twistClaw = null;
    public AnalogInput encoder;
    Limelight3A limelight;

    //  public DistanceSensor rangeFinder = null;
    //  public TouchSensor toucher = null;
    //  public RevColorSensorV3 colorSensor = null;

   //Defining the servo starting positions
    public final static double panClawARM_HOME = 0.15; // Starting point for Servo Arm 0.75
    public final static double clawARM_HOME = 0.39; // Starting point for Servo Arm
    public final static double twistClawARM_HOME = 0.75; // 0.6 Starting point for Servo Arm **twisting of the entire claw assembly
    double panPosition = panClawARM_HOME;  // servo's position
    final double panARM_SPEED = 0.10;  // set rate to move servo
    double twistPosition = twistClawARM_HOME;  // servo's position
    final double twistARM_SPEED = 0.005;  // set rate to move servo

    double clawPosition = clawARM_HOME;  // servo's position
    final double clawARM_SPEED = 0.005;  // set rate to move servo

    //HardwareMap hwMap = null;

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap hwMap) {
        lf = hwMap.get(DcMotor.class, "lf");
        lr = hwMap.get(DcMotor.class, "lr");
        rf = hwMap.get(DcMotor.class, "rf");
        rr = hwMap.get(DcMotor.class, "rr");

        lf.setDirection(DcMotor.Direction.FORWARD);
        lr.setDirection(DcMotor.Direction.FORWARD);
        rf.setDirection(DcMotor.Direction.REVERSE);
        rr.setDirection(DcMotor.Direction.REVERSE);

        lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Save reference to Hardware map
        limelight = hwMap.get(Limelight3A.class, "limelight");
        limelight.setPollRateHz(100);
    }


    public void drive(float forward,float strafe,float rotate) {
        double frontLeftPower = forward + strafe + rotate;
        double backLeftPower = forward - strafe + rotate;
        double forwardRightPower = forward - strafe - rotate;
        double backRightPower = forward + strafe - rotate;

        double maxPower = 1;
    }


    public YawPitchRollAngles getOrientation() {
        return null;
    }
}


