package org.firstinspires.ftc.Cobalt;

import android.widget.Switch;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannelController;

/**
 * Created by Kilroy Programming on 10/18/2016.
 */
@Autonomous(name="CobaltAuto", group="Cobalt")
public class CobaltAuto extends OpMode
{
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
        telemetry.addData("Say", "Yell at the robot to win harder");    //
        updateTelemetry(telemetry);
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop()
    {

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


    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop()
    {
       controlState AutoOp = controlState.STATE_ONE;
        //don't think we need this because its already declared in harware class but kept it just in case
        /*robot.leftRearMotor.setPower(0.0);
        robot.leftFrontMotor.setPower(0.0);
        robot.rightRearMotor.setPower(0.0);
        robot.rightFrontMotor.setPower(0.0);*/




//will this work? probably not
        /* RR = rightRear
           RF = rihhtFront
           LR = leftRear
           LF = leftFront
         */

        int setTargetPositionRR = 0;
        int setTargetPositionFR = 0;
        int setTargetPositionLR = 0;
        int setTargetPositionLF = 0;

        robot.rightRearMotor.setTargetPosition(setTargetPositionRR);
        robot.rightFrontMotor.setTargetPosition(setTargetPositionRR);
        robot.leftRearMotor.setTargetPosition(setTargetPositionRR);
        robot.leftFrontMotor.setTargetPosition(setTargetPositionRR);

        int getTargetPositionRR = robot.rightRearMotor.getTargetPosition();
        int getTargetPositionFR = robot.rightFrontMotor.getTargetPosition();
        int getTargetPositionLR = robot.leftRearMotor.getTargetPosition();
        int getTargetPositionLF = robot.leftFrontMotor.getTargetPosition();


        switch(AutoOp){
            case STATE_ONE:
                

                break;

        }


        if(getTargetPositionLR != setTargetPositionLR){
            robot.leftRearMotor.setPower(1.0);
        }else {
            robot.leftRearMotor.setPower(0.0);
            getTargetPositionLR = robot.leftRearMotor.getTargetPosition();
        }
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
// Motor us encoders!

}

