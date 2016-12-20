package org.firstinspires.ftc.Cobalt;

import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by Kilroy on 12/13/2016.
 */

public class BeaconFinder {
    static ColorSensor groundRGB;


 public enum ircontrol{
     FIND_WHITE, SIGNAL_DETECTED, FIND_IR, FIND_IR2, MOVE_TO_IR, STOP_BEFORE_KILL_IR
    }
    public BeaconFinder(ColorSensor groundRGBSensor){
        this.groundRGB = groundRGBSensor;


    }
    public boolean moveToBeaconControl() {
        ircontrol control = ircontrol.FIND_WHITE;
        switch (control) {
            case FIND_IR:

                    if (HardwareCobalt.irSeeker.signalDetected() == true && HardwareCobalt.irSeeker.getAngle()<= .2)
                    {
                      control = ircontrol.MOVE_TO_IR;
                    }else{
                        control = ircontrol.FIND_IR2;
                    }
                   break;
            case FIND_IR2:
                HardwareCobalt.autoDrive.encoderDrive(TheNewCobaltTransmission.TURN_SPEED, -.5, .5,0);
                if(!HardwareCobalt.leftFrontMotor.isBusy() && !HardwareCobalt.leftRearMotor.isBusy() && !HardwareCobalt.rightRearMotor.isBusy() && !HardwareCobalt.rightFrontMotor.isBusy()){
                control = ircontrol.FIND_IR;
                }
                break;
            case MOVE_TO_IR:
                // !(a&&b)&&(a||b)
                // to see if ir sensed or if it has greater than .2 strength(.2 may be changed)
                if(HardwareCobalt.irSeeker.signalDetected() == true || HardwareCobalt.irSeeker.getAngle()<= .2){
                    //if true moves to ir
                    HardwareCobalt.autoDrive.encoderDrive(TheNewCobaltTransmission.DRIVE_SPEED, 10, 10,.1);
                    //after moving 10 inches, starts again to recheck ir
                    
                    if(!HardwareCobalt.leftFrontMotor.isBusy() && !HardwareCobalt.leftRearMotor.isBusy() && !HardwareCobalt.rightRearMotor.isBusy() && !HardwareCobalt.rightFrontMotor.isBusy() ){
                         if(HardwareCobalt.irSeeker.getStrength()== 1){
                            control = ircontrol.STOP_BEFORE_KILL_IR;
                        }else{
                            control = ircontrol.MOVE_TO_IR;
                        }
                    }
                }else{
                    //if no ir dected rotate robot right
                    HardwareCobalt.autoDrive.encoderDrive(TheNewCobaltTransmission.TURN_SPEED,5,-5, .1);
                    //if still not detected turn left
                    if(!HardwareCobalt.irSeeker.signalDetected() == true || HardwareCobalt.irSeeker.getAngle()<= .2){

                        HardwareCobalt.autoDrive.encoderDrive(TheNewCobaltTransmission.TURN_SPEED,-10,10,.1);

                        if(!HardwareCobalt.leftFrontMotor.isBusy() && !HardwareCobalt.leftRearMotor.isBusy() && !HardwareCobalt.rightRearMotor.isBusy() && !HardwareCobalt.rightFrontMotor.isBusy()) {


                                //once deected starts again
                            if (HardwareCobalt.irSeeker.signalDetected() == true || HardwareCobalt.irSeeker.getAngle()<= .2) {
                                control = ircontrol.MOVE_TO_IR;

                            }

                    }

                    }

                }
                break;
            case STOP_BEFORE_KILL_IR:
                HardwareCobalt.leftFrontMotor.setPower(0);
                HardwareCobalt.leftRearMotor.setPower(0);
                HardwareCobalt.rightFrontMotor.setPower(0);
                HardwareCobalt.rightRearMotor.setPower(0);
                break;
        }


        return true;
    }
}

