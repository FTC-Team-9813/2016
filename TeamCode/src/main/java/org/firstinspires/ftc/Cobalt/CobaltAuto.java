package org.firstinspires.ftc.Cobalt;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


/**
 * Created by Kilroy Programming on 10/18/2016.
 */
@Autonomous(name="CobaltAuto", group="Cobalt")
public class CobaltAuto extends OpMode {


    /* Declare OpMode members. */
    HardwareCobalt robot = new HardwareCobalt(); // use the class created to define a Pushbot's hardware


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
        this.resetStartTime();
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
    public void start() {

         // HardwareCobalt.cdim.setDigitalChannelMode(HardwareCobalt.GROUND_LED_PORT,DigitalChannelController.Mode.OUTPUT);
        // HardwareCobalt.cdim.setDigitalChannelState(HardwareCobalt.GROUND_LED_PORT,true);
    }
    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop()  {
        telemetry.addData("Say", "if the truety of true i false, than the falsity of true is false. therefore, when it is false that it is true, the falsity of true is true. but I don't think you care, so good luck.0");
        telemetry.addData("Say", robot.rightFrontMotor.getCurrentPosition());
        telemetry.addData("Say", robot.rightFrontMotor.getTargetPosition());
        telemetry.addData("Say", robot.rightRearMotor.getCurrentPosition());
        telemetry.addData("Say", robot.rightRearMotor.getTargetPosition());
        telemetry.addData("Say", robot.leftFrontMotor.getCurrentPosition());
        telemetry.addData("Say", robot.leftFrontMotor.getTargetPosition());
        telemetry.addData("Say", robot.leftRearMotor.getCurrentPosition());
        telemetry.addData("Say", robot.leftRearMotor.getTargetPosition());
        updateTelemetry(telemetry);

        robot.transmission.DriveByInches(12, .8);





    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    //makes it stop
    public void stop()
    {
        //HardwareCobalt.cdim.setDigitalChannelState(HardwareCobalt.GROUND_LED_PORT,false);
    }
}


