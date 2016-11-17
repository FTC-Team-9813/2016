package org.firstinspires.ftc.Cobalt;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Kilroy Programming on 11/15/2016.
 */

public class CobaltTransmission {
    private final double DISTANCE_PER_TICK = 0.009;

    public CobaltTransmission(DcMotor RR ,DcMotor RF, DcMotor LR, DcMotor LF){
         this.rightRear = RR;
        this.rightFront = RF;
        this.leftRear = LR;
        this.leftFront = LF;




    }
    public boolean driveStraightDistance (double distance){


        double distanceRun = rightRear.getCurrentPosition() * DISTANCE_PER_TICK;
        double remainingDistance = distance- distanceRun;


        double ticks = distance/DISTANCE_PER_TICK ;

        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftFront.setTargetPosition((int)ticks);
        leftRear.setTargetPosition((int)ticks);
        rightRear.setTargetPosition((int)ticks);
        rightFront.setTargetPosition((int)ticks);


        if(distance+10 <= distanceRun && distance-10 >= distanceRun){
            leftFront.setPower(1.0);
            rightFront.setPower(1.0);
            leftRear.setPower(1.0);
            rightRear.setPower(1.0);


        }








        return true;
    }




    private DcMotor rightRear = null;
    private DcMotor rightFront = null;
    private DcMotor leftRear = null;
    private DcMotor leftFront = null;
}
