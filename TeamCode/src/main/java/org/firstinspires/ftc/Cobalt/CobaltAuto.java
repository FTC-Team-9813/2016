package org.firstinspires.ftc.Cobalt;

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
    CobaltTransmission cobaltTransmission = HardwareCobalt.robotDrive;

    /* Declare OpMode members. */
    HardwareCobalt robot = new HardwareCobalt(); // use the class created to define a Pushbot's hardware

    public enum controlState
    {
        MOVE_FORWARD, TURN_RIGHT,DANCE_NOW
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
        telemetry.addData("Say", "RGB Values " +HardwareCobalt.groundRGBSensor.red() * 255.0 / 65535.0 + //this gets 0.0... HELP US
                " "+HardwareCobalt.groundRGBSensor.green() * 255.0 / 65535.0 +" "+HardwareCobalt.groundRGBSensor.blue() * 255.0 / 65535.0 +"\n");    //
        updateTelemetry(telemetry);
        telemetry.addData("Angle", HardwareCobalt.irSeeker.getAngle());
        telemetry.addData("Strength", HardwareCobalt.irSeeker.getStrength());
        telemetry.addData("Say",  "Angle Values" +HardwareCobalt.irSeeker.getAngle());
        telemetry.addData("Say",  "Strength Values" +HardwareCobalt.irSeeker.getStrength());
        updateTelemetry(telemetry);
        if (HardwareCobalt.groundRGBSensor.red() == 255&& HardwareCobalt.groundRGBSensor.green() == 255&& HardwareCobalt.groundRGBSensor.blue() == 255)
        {
             if (HardwareCobalt.irSeeker.getAngle() > 5);
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
            if(HardwareCobalt.irSeeker.getStrength() > 5); //five is just an example
            {
                HardwareCobalt.leftFrontMotor.setPower(1);
                HardwareCobalt.leftRearMotor.setPower(1);
                HardwareCobalt.rightFrontMotor.setPower(1);
                HardwareCobalt.rightRearMotor.setPower(1);
            }
            telemetry.addData("Say", "There is a white line!");
            }
        //IMPORTANT!!!!!!!!!!
        //THURSDAY THE 1ST, MAKE A METHOD FOR STRENGTH TO DISTANCE, AND MAKE IT WORK.
            //This is for red team
            // if(HardwareCobalt.irSeeker.getStrength() > 5)
            //{
            // HardwareCobalt.triggerMotor.setPower(1);//move right
            // if (HardwareCobalt.frontRGBSensor.red() == 255 && HardwareCobalt.frontRGBSensor.green() == 0 && HardwareCobalt.frontRGBSensor.blue() == 0)
            // {

            // }
            // if(HardwareCobalt.triggerMotor == /*Degrees turned 15?*/)
            //  HardwareCobalt.triggerMotor.setPower(-1); // Move left
        //}
        //HardwareCobalt
        //This is some code for pushing the button on the beacon!
       // telemetry.addData("Say", " Front RGB Values " + HardwareCobalt.frontRGBSensor.red() +
               // " " + HardwareCobalt.frontRGBSensor.green() + " " + HardwareCobalt.frontRGBSensor.blue() + "\n");
        //telemetry.addData("Say", " Front RGB Values " + HardwareCobalt.frontRGBSensor.red() +
          //      " " + HardwareCobalt.frontRGBSensor.green() + " " + HardwareCobalt.frontRGBSensor.blue() + "\n");


        //If motor, when 1, turns to right
        //*255/800 If not work, try (*255/4095)
        //WHAT DOES THIS MEAN
         controlState AutoOp = controlState.MOVE_FORWARD;

        switch (AutoOp)
        {
            case MOVE_FORWARD:

                robot.robotDrive.equals(false);
        if(robot.robotDrive.equals(false)){
                robot.robotDrive.driveStraightDistance(10);}else{
            AutoOp = controlState.TURN_RIGHT;
        }

               break;
           case TURN_RIGHT:

               robot.robotDrive.equals(false);
               if(robot.robotDrive.equals(false)){
               robot.robotDrive.turnByDegrees(90);}else{
                   AutoOp = controlState.DANCE_NOW;
               }
                break;
            case DANCE_NOW:

                robot.robotDrive.equals(false);

                if(robot.robotDrive.equals(false)) {
                    robot.robotDrive.driveStraightDistance(5);
                }
                if(robot.robotDrive.equals(true)){
                    robot.robotDrive.equals(false);
                }

                if(robot.robotDrive.equals(false)) {
                    robot.robotDrive.turnByDegrees(45);
                }

                if(robot.robotDrive.equals(true)){
                    robot.robotDrive.equals(false);
                }
                if(robot.robotDrive.equals(false)){
                robot.robotDrive.turnByDegrees(-90);}

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


}


