package org.firstinspires.ftc.Cobalt;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Kilroy on 1/12/2017.
 */

public class FlyWheelShooter {

public boolean getBalls(boolean doLoadingStuff){
    if(doLoadingStuff==true) {
        HardwareCobalt.loadLoader.setPower(1);
    }

    return true;
}

public boolean shootStuff(boolean doShootingStuff){

;

    if(doShootingStuff==true){
        HardwareCobalt.loadShooter.setPosition(1);//dont know value yet

           HardwareCobalt.rightShootMotor.setPower(1);
           HardwareCobalt.leftShootMotor.setPower(1);

    }




    return true;
}




}