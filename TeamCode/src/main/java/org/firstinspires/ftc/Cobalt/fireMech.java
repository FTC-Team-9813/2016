package org.firstinspires.ftc.Cobalt;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by Kilroy on 10/18/2016.
 */

public class FireMech {
    final double motorPower = 1;

    public FireMech(DcMotor triggerMotor, TouchSensor  triggerTouchSensor) {

            if (triggerTouchSensor.isPressed()) {
                //stop if pressed
                triggerMotor.setPower(0);

            } else {
                triggerMotor.setPower(motorPower);
            }
    }
}
