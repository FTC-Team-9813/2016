

package org.firstinspires.ftc.Cobalt;
// I thought code.e was a better name than Cobalt

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * Demonstrates empty OpMode
 */
@Autonomous(name = "Concept: NullOp", group = "Concept")
@Disabled
public class Teleop extends OpMode {
// the motor controller
    private DcMotorController dc_drive_controller;


  // these are the motors. they mote
    private DcMotor dc_drive_left;
    private DcMotor dc_drive_right;




  private ElapsedTime runtime = new ElapsedTime();

  @Override
  public void init() {
    telemetry.addData("Status", "Initialized");
//adding motors to hardware map
      dc_drive_controller = hardwareMap.dcMotorController.get("drive_controller");
      dc_drive_left = hardwareMap.dcMotor.get("drive_left");
      dc_drive_right = hardwareMap.dcMotor.get("drive_right");

  }

  /*
     * Code to run when the op mode is first enabled goes here
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
     */
  @Override
  public void init_loop() {




  }

  /*
   * This method will be called ONCE when start is pressed
   * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
   */
  @Override
  public void start() {


  }
  /*
   * This method will be called repeatedly in a loop
   * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
   */
      @Override
      public void loop ()
      {
          telemetry.addData("Status", "Run Time: " + runtime.toString());

          dc_drive_left.setPower((double)gamepad1.left_stick_y);
          dc_drive_right.setPower((double)gamepad1.right_stick_y);


      }

  }
