package org.firstinspires.ftc.Cobalt;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by Kilroy on 10/18/2016.
 */

public class CatapultFireMech {
    static final double motorPower = 1;
    static  DcMotor trigger;



    public CatapultFireMech(DcMotor triggerMotor) {

        this.trigger = triggerMotor;


    }
    public static boolean fire() {

        trigger.setPower(motorPower);

    return(true);
}

}
