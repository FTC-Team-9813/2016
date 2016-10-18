package org.firstinspires.ftc.Cobalt;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by Kilroy Programming on 10/18/2016.
 */
@Autonomous(name="CobaltAuto", group="Cobalt")
public class CobaltAuto extends OpMode
{
    /* Declare OpMode members. */
    HardwareCobalt robot       = new HardwareCobalt(); // use the class created to define a Pushbot's hardware
    // could also use HardwarePushbotMatrix class.



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
        telemetry.addData("Say", "Motors are moting...      robots are roboting...      winners shall be winnering, cobalt is here.");    //
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
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
//driving? I hope?

        robot.leftFrontMotor.setPower(0);
        robot.leftRearMotor.setPower(0);
        robot.rightFrontMotor.setPower(0);
        robot.rightRearMotor.setPower(0);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
