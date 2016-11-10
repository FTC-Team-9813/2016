package org.firstinspires.ftc.Cobalt;

import android.util.Xml;
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
        telemetry.addData("Say", "RGB Values " +HardwareCobalt.groundRGBSensor.red() * 255 / 800 +
                " "+HardwareCobalt.groundRGBSensor.green() * 255 / 800 +" "+HardwareCobalt.groundRGBSensor.blue() * 255 / 800 +"\n");    //
        updateTelemetry(telemetry);





//will this work? probably not
        // RR = rightRear
          // RF = rightFront
          // LR = leftRear
          // LF = leftFront


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

        switch(AutoOp) {
            case STATE_ONE:
                setTargetPositionRR = 2800;
                setTargetPositionFR = 2800;
                setTargetPositionLR = 2800;
                setTargetPositionLF = 2800;

                if (getTargetPositionRR <= setTargetPositionRR - 10 && getTargetPositionRR <= setTargetPositionRR + 10 && getTargetPositionFR <= setTargetPositionFR - 10 && getTargetPositionRR <= setTargetPositionRR + 10 && getTargetPositionLR <= setTargetPositionLR - 10 && getTargetPositionLR <= setTargetPositionLR + 10 && getTargetPositionLF <= setTargetPositionLF - 10 && getTargetPositionLF <= setTargetPositionLF + 10) {
                    robot.leftRearMotor.setPower(1.0);
                    robot.leftFrontMotor.setPower(1.0);
                    robot.rightRearMotor.setPower(1.0);
                    robot.rightFrontMotor.setPower(1.0);
                    // Sets all wheels (RR, FR, LR, LF) to maybe turn 360 degrees


                }else{
                    robot.leftRearMotor.setPower(0);
                robot.leftFrontMotor.setPower(0);
                robot.rightRearMotor.setPower(0);
                robot.rightFrontMotor.setPower(0);
                    AutoOp = controlState.STATE_TWO;
                }
                AutoOp = controlState.STATE_TWO;
                break;
            case STATE_TWO:

                setTargetPositionRR = 0;
                setTargetPositionFR = 0;
                setTargetPositionLR = 0;
                setTargetPositionLF = 0;

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

