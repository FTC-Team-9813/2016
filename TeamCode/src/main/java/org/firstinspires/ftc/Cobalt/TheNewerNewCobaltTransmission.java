package org.firstinspires.ftc.Cobalt;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Kilroy on 5/30/2017.
 */

public class TheNewerNewCobaltTransmission {
    HardwareCobalt robot = new HardwareCobalt();


    public void DriveByInches(int inches, double speed){
    boolean setup = false;
         boolean test = true;
        double ticks = COUNTS_PER_INCH * inches;

        if(setup == false){
            robot.rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.rightRearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.leftRearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            System.out.println("in setup");
            setup = true;
        }
    if(robot.rightFrontMotor.getCurrentPosition() >= ticks || robot.leftFrontMotor.getCurrentPosition() >= ticks){
        robot.rightFrontMotor.setPower(0);
        robot.rightRearMotor.setPower(0);
        robot.leftFrontMotor.setPower(0);
        robot.leftFrontMotor.setPower(0);
        System.out.println("in driving");

    }else{
        robot.rightFrontMotor.setPower(speed);
        robot.rightRearMotor.setPower(speed);
        robot.leftFrontMotor.setPower(speed);
        robot.leftFrontMotor.setPower(speed);
    }

    }
    public void StopSafely(){
        Double AverageSpeed = (robot.rightFrontMotor.getPower()+robot.rightRearMotor.getPower()
                + robot.leftFrontMotor.getPower() + robot.leftRearMotor.getPower())/4;



    }





    public final double     COUNTS_PER_MOTOR_REV    = 2800;    // eg: Andymark Motor Encoder
   public final double     DRIVE_GEAR_REDUCTION    = 1.5;     // This is < 1.0 if geared UP
   public final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.141592654);

}
