package org.firstinspires.ftc.teamcode.pedro;
import com.pedropathing.api.Paths;

import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class BotPositions extends OpMode {

    // Red Alliance ---------------
    Pose redHiveStart = new Pose(0,0); // Red Alliance; Located on the opposite side of the flower.
    Pose redHiveFlowerStart = new Pose(0,0); // Red Alliance; Located on the side with the flower.

    // Blue Alliance --------------
    Pose blueHiveStart = new Pose(0,0); // Blue Alliance; Located on the opposite side of the flower.
    Pose blueHiveFlowerStart = new Pose(0,0); // Blue Alliance; Located on the side with the flower.

    // Other --------------
    Pose flower1 = new Pose(0,0); // Flower closest to the audience; the one directly in front of the blue cell.
    Pose flower2 = new Pose(0,0); // Flower in front of the red garden.
    Pose flower3 = new Pose(0,0); // Flower near the red parking zone.
    Pose flower4 = new Pose(0,0); // Flower in front of the blue garden.

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }
}
