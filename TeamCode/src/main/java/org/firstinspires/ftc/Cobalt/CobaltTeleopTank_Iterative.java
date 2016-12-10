/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.Cobalt;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannelController;
import com.qualcomm.robotcore.util.Hardware;


/**
 * This file provides basic Telop driving for a Pushbot robot.
 * The code is structured as an Iterative OpMode
 *
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwareCobalt class.
 *
 * This particular OpMode executes a basic Tank Drive Teleop for a PushBot
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="CobaltTeleop1", group="Pushbot")
//@Disabled
public class CobaltTeleopTank_Iterative extends OpMode{

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
        robot.shooterController.setPosition(1); //1 is down, .5 is up
        robot.flipperController.setPosition(0); // 0 is open, .3 is closed.

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "If you are losing win better and if you are winning win better");    //
        updateTelemetry(telemetry);
    }
    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }
    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {

        //color sensors
        //HardwareCobalt.cdim.setDigitalChannelMode(HardwareCobalt.GROUND_LED_PORT,DigitalChannelController.Mode.OUTPUT);
    }
    boolean previous_state = false;

    boolean flippersOpen = true;

    boolean shooterOpen = true;
    boolean previousShooter_state;

    boolean testServoBool = true;
    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {

//        double left;
//        double right;

        //does it work yet?

       // telemetry.addData("ColorValues", robot.groundRGBSensor.red() * (255.0 / 65535.0) + " " + robot.groundRGBSensor.green() * (255.0 / 65535.0) + " " + robot.groundRGBSensor.blue() * (255.0 / 65535.0));
       // telemetry.update();

        robot.leftFrontMotor.setPower(-gamepad1.left_stick_y);
        robot.leftRearMotor.setPower(-gamepad1.left_stick_y);
        robot.rightFrontMotor.setPower(-gamepad1.right_stick_y);
        robot.rightRearMotor.setPower(-gamepad1.right_stick_y);


        robot.flipperController.setPosition(.3);
        robot.shooterController.setPosition(1);
       //capture ball
        if (gamepad2.left_bumper == true && gamepad2.left_bumper != previous_state)
        {
            flippersOpen = !flippersOpen;
        }
        previous_state = gamepad2.left_bumper;

        if (flippersOpen == false)
        {
            robot.flipperController.setPosition(.3); // 0 or 1, we don't know
            telemetry.addData("Say","capturing flippers are is closed");
        }
        else
        {
            //#Thuglife
            robot.flipperController.setPosition(0);
            telemetry.addData("Say","capturing flippers are is open");
        }

//shoot the ball

       if (gamepad2.right_bumper == true && gamepad2.right_bumper != previousShooter_state)
        {
            shooterOpen = !shooterOpen;
        }
        previousShooter_state = gamepad2.right_bumper;

        if (shooterOpen == false)
        {
            robot.shooterController.setPosition(1);//1 is down, .5 is up
            telemetry.addData("Say","shooter flipper is down");
        }
        else
        {
            //#Thuglife
            telemetry.addData("Say", "shooter flipper is up");
            robot.shooterController.setPosition(.5);
        }
        updateTelemetry(telemetry);
//        if(gamepad2.dpad_right && gamepad2.dpad_right != testServoBool)
//            robot.flipperController.setPosition(robot.flipperController.getPosition() + .05);
//        else if(gamepad2.dpad_left&& gamepad2.dpad_left != testServoBool)
//            robot.flipperController.setPosition(robot.flipperController.getPosition() - .05);
//
//        if(gamepad2.dpad_up&& gamepad2.dpad_up!= testServoBool)
//            robot.shooterController.setPosition(robot.shooterController.getPosition() + .05);
//        else if(gamepad2.dpad_down&& gamepad2.dpad_down != testServoBool)
//            robot.shooterController.setPosition(robot.shooterController.getPosition() -.05);
//        testServoBool = gamepad2.dpad_right || gamepad2.dpad_up || gamepad2.dpad_left || gamepad2.dpad_down;
//        telemetry.addData("Servo Positions:", "Flipper: " + robot.flipperController.getPosition() + "\nShooter:" + robot.shooterController.getPosition());
//        updateTelemetry(telemetry);
         }
        @Override
        public void stop(){

        }

   }
