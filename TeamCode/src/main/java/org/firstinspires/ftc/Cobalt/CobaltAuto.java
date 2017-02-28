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

    public enum controlState {
        MOVE_FORWARD, TURN_LEFT, DANCE_NOW, STOP_NOW, IR_IS_MEAN
    }
    public enum danceControl{
        Initiate_DANCE, Boogey, Shake, Spasm, Circular_Motion
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
        robot.leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.leftRearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightRearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         // HardwareCobalt.cdim.setDigitalChannelMode(HardwareCobalt.GROUND_LED_PORT,DigitalChannelController.Mode.OUTPUT);
        // HardwareCobalt.cdim.setDigitalChannelState(HardwareCobalt.GROUND_LED_PORT,true);
    }
    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop()  {
        telemetry.addData("Say", "Prepare for the sketch. That sketchy program or the sketchy life you may ask. The answer is both. #Thuglife");
        telemetry.addData("Say", robot.rightFrontMotor.getCurrentPosition());
        telemetry.addData("Say", robot.rightFrontMotor.getTargetPosition());
        telemetry.addData("Say", robot.rightRearMotor.getCurrentPosition());
        telemetry.addData("Say", robot.rightRearMotor.getTargetPosition());
        telemetry.addData("Say", robot.leftFrontMotor.getCurrentPosition());
        telemetry.addData("Say", robot.leftFrontMotor.getTargetPosition());
        telemetry.addData("Say", robot.leftRearMotor.getCurrentPosition());
        telemetry.addData("Say", robot.leftRearMotor.getTargetPosition());
        updateTelemetry(telemetry);

        //controlState AutoOp = controlState.MOVE_FORWARD;
        danceControl DanceMove = danceControl.Initiate_DANCE;
        int repeat =0;
        int yes = 0;

        switch(DanceMove)
        {
            //make dance
            case Initiate_DANCE:
                yes++;
                repeat = 0;
                robot.rightFrontMotor.setPower(0);
                robot.rightRearMotor.setPower(0);
                robot.leftFrontMotor.setPower(0);
                robot.leftRearMotor.setPower(0);
                robot.rightFrontMotor.setTargetPosition(0);
                robot.rightRearMotor.setTargetPosition(0);
                robot.leftFrontMotor.setTargetPosition(0);
                robot.leftRearMotor.setTargetPosition(0);
                switch (yes){
                    case 1:
                        this.resetStartTime();
                        DanceMove = danceControl.Spasm;
                        break;
                    case 2:
                        DanceMove = danceControl.Shake;
                        break;
                    case 3:
                        DanceMove = danceControl.Boogey;
                        break;
                    case 4:
                        DanceMove = danceControl.Circular_Motion;
                        break;
                }

                break;
            case Spasm:
                //makes spasm
              repeat++;
                if(repeat<20) {
                    telemetry.addData("Say", "Now spasming");
                    robot.autoDrive.encoderDrive(1, .5, -.5);
                    robot.autoDrive.encoderDrive(1, -.5, .5);
                       }else{
                    DanceMove = danceControl.Initiate_DANCE;
                }
                break;
            case Shake:
                //makes shake
                repeat++;
                telemetry.addData("Say","Now shaking");
                if(repeat<20){
                    robot.autoDrive.encoderDrive(.6,5,-5);
                    robot.autoDrive.encoderDrive(.6, -5,5);
                }else{
                    DanceMove = danceControl.Initiate_DANCE;
                }
                break;
            case Boogey:
                //makes boogey
                break;
            case Circular_Motion:
                //makes circular motion occur
                telemetry.addData("Say","currently making circular motion occur in space-time");
                if(!robot.rightFrontMotor.isBusy() && !robot.leftFrontMotor.isBusy() && !robot.rightRearMotor.isBusy() && !robot.leftRearMotor.isBusy()){
                    robot.autoDrive.encoderDrive(.8,360,-360);

                     if(!robot.rightFrontMotor.isBusy() && !robot.leftFrontMotor.isBusy() && !robot.rightRearMotor.isBusy() && !robot.leftRearMotor.isBusy()){
                        DanceMove = danceControl.Initiate_DANCE;
                    }
                }else{

                }
                break;
        }

        //  if(robot.robotDrive.equals(false)){
        //      Dance = dance.DANCE_1;
        //}

      /* switch (AutoOp) {
            case MOVE_FORWARD:
                robot.rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.rightRearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.leftRearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                telemetry.addData("Say", "now we play the waiting game");
                telemetry.addData("Say", this.getRuntime());
                if(this.getRuntime()>=11 && this.getRuntime()< 16.5){
                    robot.rightRearMotor.setPower(1);
                    robot.rightFrontMotor.setPower(1);
                    robot.leftFrontMotor.setPower(1);
                    robot.leftRearMotor.setPower(1);

                }else{
                    robot.rightRearMotor.setPower(0);
                robot.rightFrontMotor.setPower(0);
                robot.leftFrontMotor.setPower(0);
                robot.leftRearMotor.setPower(0);}
                // robot.robotDrive.driveStraightDistance(102);
               //telemetry.addData("Say", robot.robotDrive.driveStraightStatus);
                telemetry.addData("Say", "driving straight distance");
                break;
            case STOP_NOW:
                robot.rightRearMotor.setPower(0);
                robot.rightFrontMotor.setPower(0);
                robot.leftFrontMotor.setPower(0);
                robot.leftRearMotor.setPower(0);
        }*/

       /* switch(AutoOp){
            case MOVE_FORWARD:
                telemetry.addData("Say", this.getRuntime());
                //DRIVE_SPEED/TURN_SPEED
                // Note: Reverse movement is obtained by setting a negative distance (not speed)
                //robot.autoDrive.encoderDrive(speed, right distance, left distance, wait time)
                // Note: Reverse movement is obtained by setting a negative distance (not speed)
                robot.autoDrive.encoderDrive(1.0, 102.0, 102.0);

                //geting telemety on one line was
                telemetry.addData("Say", robot.rightFrontMotor.getCurrentPosition());
                telemetry.addData("Say", robot.rightFrontMotor.getTargetPosition());
                telemetry.addData("Say", robot.rightRearMotor.getCurrentPosition());
                telemetry.addData("Say", robot.rightRearMotor.getTargetPosition());
                telemetry.addData("Say", robot.leftFrontMotor.getCurrentPosition());
                telemetry.addData("Say", robot.leftFrontMotor.getTargetPosition());
                telemetry.addData("Say", robot.leftRearMotor.getCurrentPosition());
                telemetry.addData("Say", robot.leftRearMotor.getTargetPosition());
                break;

            case TURN_LEFT:

                robot.autoDrive.encoderDrive(.7,-12.0,12.0);
                break;

            case IR_IS_MEAN:
              robot.beaconFinder.moveToBeaconControl();

             break;
            case STOP_NOW:
                robot.rightRearMotor.setPower(0);
                robot.leftRearMotor.setPower(0);
                robot.rightFrontMotor.setPower(0);
                robot.leftFrontMotor.setPower(0);

break;

        }*/


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


