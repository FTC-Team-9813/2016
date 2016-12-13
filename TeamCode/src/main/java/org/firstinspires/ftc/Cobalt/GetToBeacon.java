package org.firstinspires.ftc.Cobalt;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.IrSeekerSensor;

/**
 * Created by Kilroy on 12/13/2016.
 */

public class GetToBeacon {
    static ColorSensor groundRGB;

 public enum ircontrol{
       FING_WHITE, SIGNAL_DETECTED
    }
    public GetToBeacon(ColorSensor groundRGBSensor){
        this.groundRGB = groundRGBSensor;


    }
    public boolean lineFollowing(){
        switch(ircontrol){
       if(HardwareCobalt.groundRGBSensor.red() == 255&& HardwareCobalt.groundRGBSensor.green() == 255&& HardwareCobalt.groundRGBSensor.blue() == 255) {

           if (HardwareCobalt.irSeeker.signalDetected() == true) ;
           {
               HardwareCobalt.leftFrontMotor.setPower(1);
               HardwareCobalt.leftRearMotor.setPower(1);
               HardwareCobalt.rightFrontMotor.setPower(1);
               HardwareCobalt.rightRearMotor.setPower(1);
           }
           if (HardwareCobalt.irSeeker.getAngle() < -5) ;
           {
               HardwareCobalt.leftFrontMotor.setPower(-1);
               HardwareCobalt.leftRearMotor.setPower(-1);
               HardwareCobalt.rightFrontMotor.setPower(1);
               HardwareCobalt.rightRearMotor.setPower(1);
           }
           if (HardwareCobalt.irSeeker.getAngle() > 5) //five is just an example
           {
               HardwareCobalt.leftFrontMotor.setPower(1);
               HardwareCobalt.leftRearMotor.setPower(1);
               HardwareCobalt.rightFrontMotor.setPower(-1);
               HardwareCobalt.rightRearMotor.setPower(-1);


           }
       }


       }


        return true;
    }
}
