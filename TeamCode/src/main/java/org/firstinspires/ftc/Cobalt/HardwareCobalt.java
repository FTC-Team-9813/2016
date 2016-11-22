package org.firstinspires.ftc.Cobalt;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannelController;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IrSeekerSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static android.R.attr.duration;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See CobaltTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"

 */
public class HardwareCobalt
{
    //motors
    public static DcMotor leftFrontMotor = null;
    public static DcMotor rightFrontMotor = null;
    public static DcMotor rightRearMotor = null;
    public static DcMotor leftRearMotor = null;

    // catapult trigger
    public static DcMotor triggerMotor = null;
public static DcMotor armMotor = null;


    //pitching motors
//    public static DcMotor firingMotor = null;
//    public static DcMotor loadingMotor = null;
    //color sensors (or related to color sensors)
    public static DeviceInterfaceModule cdim = null;
    //public static DeviceInterfaceModule deviceInterface = null;
    public static DigitalChannelController bottomLedLight = null;
//transmission
    CobaltTransmission robotDrive  = new CobaltTransmission(leftFrontMotor, leftRearMotor, rightRearMotor, rightFrontMotor);
    //NOT REALLY USING THESE RIGHT NOW BUT TOO LAZY TOO DELETE...     I NEED TO STOP DOING THIS ON CAPS
    public static CatapultFireMech catapultFireMech = new CatapultFireMech(triggerMotor);
    //public static PitchingFireMech pitchFireMech = new PitchingFireMech(firingMotor);

    public static final int GROUND_LED_PORT =5;
    public static ColorSensor groundRGBSensor;
    public static ColorSensor frontRGBSensor;
    public static IrSeekerSensor irSeeker;

//duration!


    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareCobalt(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftFrontMotor = hwMap.dcMotor.get("left_front_drive");
        leftRearMotor = hwMap.dcMotor.get("left_rear_drive");
        rightFrontMotor = hwMap.dcMotor.get("right_front_drive");
        rightRearMotor = hwMap.dcMotor.get("right_rear_drive");
        triggerMotor = hwMap.dcMotor.get("trigger_motor");
        //pitching motors
        //firingMotor = hwMap.dcMotor.get("firing_motor");
        //loadingMotor = hwMap.dcMotor.get("loading_motor");
        //Color Sensors
        cdim = hwMap.deviceInterfaceModule.get("cdim");
        //deviceInterface = hwMap.deviceInterfaceModule.get("core_device_interface");
        //bottomLedLight = hwMap.deviceInterfaceModule.get("LED_light");
      //  triggerMotor = hwMap.dcMotor.get("trigger_fire_motor");
        groundRGBSensor = hwMap.colorSensor.get("colorsensor");
        frontRGBSensor = hwMap.colorSensor.get("colorsensor");
        //duration!

        //the ir sensor
        irSeeker = hwMap.irSeekerSensor.get("irseekersensor");
        //pitching motors
        //firingMotor = hwMap.dcMotor.get("firing_motor");
        //loadingMotor = hwMap.dcMotor.get("loading_motor");


        leftFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        leftRearMotor.setDirection(DcMotor.Direction.FORWARD);
        rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        rightRearMotor.setDirection(DcMotor.Direction.REVERSE);
// catapult motor
       // triggerMotor.setDirection(DcMotor.Direction.FORWARD);
        //pitching motors
        //loadingMotor.setDirection(DcMotor.Direction.FORWARD);
        //firingMotor.setDirection(DcMotor.Direction.FORWARD);

        // Set all motors to zero power
        leftFrontMotor.setPower(0);
        leftRearMotor.setPower(0);
        rightFrontMotor.setPower(0);
        rightRearMotor.setPower(0);

        //catapult trigger motor
      //  triggerMotor.setPower(0);
        //pitching motors




        //motors us encoders!
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //trigger motor use encoder
       // triggerMotor.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        //touch sensor







    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     * @throws InterruptedException
     */
    public void waitForTick(long periodMs) throws InterruptedException {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0)
            Thread.sleep(remaining);

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}

