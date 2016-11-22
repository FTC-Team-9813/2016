package org.firstinspires.ftc.Cobalt;

import android.util.Xml;
import android.widget.Switch;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannelController;
import com.qualcomm.robotcore.hardware.IrSeekerSensor;
/**
 * Created by Kilroy Programming on 10/18/2016.
 */
@Autonomous(name="CobaltAuto", group="Cobalt")
public class CobaltAuto extends OpMode
{
    CobaltTransmission cobaltTransmission;

    /* Declare OpMode members. */
    HardwareCobalt robot       = new HardwareCobalt(); // use the class created to define a Pushbot's hardware

    public enum controlState
    {
        STATE_ONE, STATE_TWO
    }


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Yell at the robot to win harder");
        updateTelemetry(telemetry);
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop()
    {
        AutoOp = controlState.STATE_ONE;
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start()
    {
        robot.leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.leftRearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightRearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        HardwareCobalt.cdim.setDigitalChannelMode(HardwareCobalt.GROUND_LED_PORT,DigitalChannelController.Mode.OUTPUT);
        HardwareCobalt.cdim.setDigitalChannelState(HardwareCobalt.GROUND_LED_PORT,true);




    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop()
    {
        telemetry.addData("Say", "Bottom RGB Values " +HardwareCobalt.groundRGBSensor.red() * 255 / 800 +
                " "+HardwareCobalt.groundRGBSensor.green() * 255 / 800 +" "+HardwareCobalt.groundRGBSensor.blue() * 255 / 800 +"\n");    //
        updateTelemetry(telemetry);
        telemetry.addData("Angle", HardwareCobalt.irSeeker.getAngle());
        telemetry.addData("Strength", HardwareCobalt.irSeeker.getStrength());
        if (HardwareCobalt.groundRGBSensor.red() == 255&& HardwareCobalt.groundRGBSensor.green() == 255&& HardwareCobalt.groundRGBSensor.blue() == 255)
        {
            telemetry.addData("Say", "There is a white line!");
            if(HardwareCobalt.irSeeker.getAngle() > 5);
            {
                HardwareCobalt.leftFrontMotor.setPower(1);
                HardwareCobalt.leftRearMotor.setPower(1);
                HardwareCobalt.rightFrontMotor.setPower(-1);
                HardwareCobalt.rightRearMotor.setPower(-1);
            }
            if(HardwareCobalt.irSeeker.getAngle() < -5);
            {
                HardwareCobalt.leftFrontMotor.setPower(-1);
                HardwareCobalt.leftRearMotor.setPower(-1);
                HardwareCobalt.rightFrontMotor.setPower(1);
                HardwareCobalt.rightRearMotor.setPower(1);
            }
            if (HardwareCobalt.irSeeker.getStrength() > 5); //five is just an example
            {
                HardwareCobalt.leftFrontMotor.setPower(1);
                HardwareCobalt.leftRearMotor.setPower(1);
                HardwareCobalt.rightFrontMotor.setPower(1);
                HardwareCobalt.rightRearMotor.setPower(1);
            }
            //This is for red team
           // if(HardwareCobalt.irSeeker.getStrength() > 5)
            //{
               // HardwareCobalt.triggerMotor.setPower(1);//move right
               // if (HardwareCobalt.frontRGBSensor.red() == 255 && HardwareCobalt.frontRGBSensor.green() == 0 && HardwareCobalt.frontRGBSensor.blue() == 0)
               // {

               // }
               // if(HardwareCobalt.triggerMotor == /*Degrees turned 15?*/)
                  //  HardwareCobalt.triggerMotor.setPower(-1); // Move left
            }
        }
        //HardwareCobalt
        //This is some code for pushing the button on the beacon!
        telemetry.addData("Say", " Front RGB Values " +HardwareCobalt.frontRGBSensor.red() +
                " "+HardwareCobalt.frontRGBSensor.green() +" "+HardwareCobalt.frontRGBSensor.blue() +"\n");

            //If motor, when 1, turns to right
        //*255/800 If not work, try (*255/4095)
       controlState AutoOp = controlState.STATE_ONE;
        int setTargetPositionRR = 360;
        int setTargetPositionFR = 360;
        int setTargetPositionLR = 360;
        int setTargetPositionLF = 360;

        robot.rightRearMotor.setTargetPosition(setTargetPositionRR);
        robot.rightFrontMotor.setTargetPosition(setTargetPositionFR);
        robot.leftRearMotor.setTargetPosition(setTargetPositionLR);
        robot.leftFrontMotor.setTargetPosition(setTargetPositionLF);

        int getTargetPositionRR = robot.rightRearMotor.getTargetPosition();
        int getTargetPositionFR = robot.rightFrontMotor.getTargetPosition();
        int getTargetPositionLR = robot.leftRearMotor.getTargetPosition();
        int getTargetPositionLF = robot.leftFrontMotor.getTargetPosition();

        int getCurrentPositionRR = robot.rightRearMotor.getCurrentPosition();
        int getCurrentPositionFR = robot.rightFrontMotor.getCurrentPosition();
        int getCurrentPositionLR = robot.leftRearMotor.getCurrentPosition();
        int getCurrentPositionLF = robot.leftFrontMotor.getCurrentPosition();

        switch(AutoOp){
            case STATE_ONE:

              break;
            case STATE_TWO:
                break;
        }

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop()
    {
        HardwareCobalt.cdim.setDigitalChannelState(HardwareCobalt.GROUND_LED_PORT,false);
    }

    controlState AutoOp = controlState.STATE_ONE;

// Motor us encoders!

}

