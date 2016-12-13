package org.firstinspires.ftc.Cobalt;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.TelemetryImpl;



/**
 * Created by Kilroy Programming on 11/15/2016.
 */

public class CobaltTransmission {

    public enum Control {
        SETUP, RUN,QUIT;
    }
        private final double DISTANCE_PER_TICK = 0.009;

        static DcMotor rightFront;
        static DcMotor rightRear;
        static DcMotor leftRear;
        static DcMotor leftFront;

    double distanceRun;
    double remainingDistance;
    double ticks;

    double ticksRanRight;
    double ticksRanLeft;
    double remainingTicksRight;
    double remainingTicksLeft;


        public CobaltTransmission(DcMotor rightRearMotor, DcMotor rightFrontMotor, DcMotor leftFrontMotor, DcMotor leftRearMotor) {



            this.rightRear = rightRearMotor;
            this.rightFront = rightFrontMotor;
            this.leftRear = leftRearMotor;
            this.leftFront = leftFrontMotor;

            rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


            leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        }
        public Control driveStraightStatus = Control.SETUP;
        public boolean driveStraightDistance(double distance) {


            switch (driveStraightStatus) {
                case SETUP:
                    leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


                    distanceRun = rightRear.getCurrentPosition()*DISTANCE_PER_TICK;
                    remainingDistance = distance - distanceRun;
                    ticks = distance / DISTANCE_PER_TICK;

                    leftFront.setTargetPosition((int) ticks);
                    leftRear.setTargetPosition((int) ticks);
                    rightRear.setTargetPosition((int) ticks);
                    rightFront.setTargetPosition((int) ticks);

                    rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    driveStraightStatus = Control.RUN;


                    break;
                case RUN:
                    leftFront.setPower(1.0);
                    rightFront.setPower(1.0);
                    leftRear.setPower(1.0);
                    rightRear.setPower(1.0);


                    if(!(rightRear.getCurrentPosition() > rightRear.getTargetPosition() -49 && rightRear.getCurrentPosition() < rightRear.getTargetPosition() + 49)) {
                       driveStraightStatus = Control.RUN;
                    }

                    else{
                        driveStraightStatus = Control.QUIT;
                    }
                    break;
                case QUIT:
                    leftFront.setPower(0);
                    rightFront.setPower(0);
                    leftRear.setPower(0);
                    rightRear.setPower(0);
                    driveStraightStatus = (Control.SETUP);
                    break;
            }
            return true;
        }

        public boolean turnByDegrees(double degrees) {

            Control State = Control.SETUP;

//(math.pi/180)*degrees*4inches

            //to turn to the left set the target degrees negative
            double RRticks = (Math.PI / 180) * -degrees + 444;
            double RFticks = (Math.PI / 180) * -degrees + 444;
            double LRticks = (Math.PI / 180) * degrees + 444;
            double LFticks = (Math.PI / 180) * degrees + 444;

            switch (State) {
                case SETUP:
                     ticksRanRight = rightFront.getCurrentPosition();
                    ticksRanLeft = leftFront.getCurrentPosition();
                     remainingTicksRight = RRticks - ticksRanRight;
                     remainingTicksLeft = LFticks - ticksRanLeft;

                    leftFront.setTargetPosition((int) LFticks);
                    leftRear.setTargetPosition((int) LRticks);
                    rightRear.setTargetPosition((int) RRticks);
                    rightFront.setTargetPosition((int) RFticks);

                   State = Control.RUN;
                    break;
                case RUN:

                    if (RRticks < 0 && RFticks < 0) {
                        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);
                        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
                    } else {
                        rightRear.setDirection(DcMotorSimple.Direction.FORWARD);
                        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);

                    }
                    if (LRticks < 0 && LFticks < 0) {
                        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);
                        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
                    } else {
                        leftRear.setDirection(DcMotorSimple.Direction.FORWARD);
                        leftRear.setDirection(DcMotorSimple.Direction.FORWARD);
                    }

                    if (remainingTicksLeft > 0 && remainingTicksRight > 0) {
                        rightFront.setPower(1.0);
                        rightRear.setPower(1.0);
                        leftFront.setPower(1.0);
                        leftRear.setPower(1.0);
                    }else{
                        rightFront.setPower(0);
                        rightRear.setPower(0);
                        leftFront.setPower(0);
                        leftRear.setPower(0);
                    }

                    State = Control.SETUP;
                    break;
            }
            return true;
        }

        }