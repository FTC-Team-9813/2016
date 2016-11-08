package org.firstinspires.ftc.Cobalt;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Kilroy on 11/8/2016.
 */

public class CobaltTransmission {
    public CobaltTransmission(DcMotor RR, DcMotor RF, DcMotor LR, DcMotor LF) {
        this.rightRear = RR;
        this.rightFront = RF;
        this.leftRear = LR;
        this.leftFront = LF;
    }

    public boolean driveStraightDistance(double distance) {
        //we're measuring distance in inches.

        this.rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //this sets the DcMotors to mode Run to position.
        return true;
    }

    private DcMotor rightRear = null;
    private DcMotor rightFront = null;
    private DcMotor leftRear = null;
    private DcMotor leftFront = null;
}

