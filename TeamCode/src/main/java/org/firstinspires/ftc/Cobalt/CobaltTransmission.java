package org.firstinspires.ftc.Cobalt;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Kilroy Programming on 11/15/2016.
 */

public class CobaltTransmission {
    public enum Control {
        STATE_ONE
    }
        private final double DISTANCE_PER_TICK = 0.009;

        static DcMotor rightFront;
        static DcMotor rightRear;
        static DcMotor leftRear;
        static DcMotor leftFront;


        public CobaltTransmission(DcMotor rightRearMotor, DcMotor rightFrontMotor, DcMotor leftFrontMotor, DcMotor leftRearMotor) {


            this.rightRear = rightRearMotor;
            this.rightFront = rightFrontMotor;
            this.leftRear = leftRearMotor;
            this.leftFront = leftFrontMotor;

            //rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
           // leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        }

        public boolean driveStraightDistance(double distance) {

            Control State = Control.STATE_ONE;


            switch (State) {
                case STATE_ONE:

                    double distanceRun = rightRear.getCurrentPosition();
                    double remainingDistance = distance - distanceRun;
                    double ticks = distance / DISTANCE_PER_TICK;
                    leftFront.setTargetPosition((int) ticks);
                    leftRear.setTargetPosition((int) ticks);
                    rightRear.setTargetPosition((int) ticks);
                    rightFront.setTargetPosition((int) ticks);

                    if (remainingDistance > 0) {
                        leftFront.setPower(1.0);
                        rightFront.setPower(1.0);
                        leftRear.setPower(1.0);
                        rightRear.setPower(1.0);
                    }
                    break;
            }
            return true;
        }

        public boolean turnByDegrees(double degrees) {

            Control State = Control.STATE_ONE;


//(math.pi/180)*degrees*4inches

            //to turn to the left set the target degrees negative
            double RRticks = (Math.PI / 180) * -degrees + 444;
            double RFticks = (Math.PI / 180) * -degrees + 444;
            double LRticks = (Math.PI / 180) * degrees + 444;
            double LFticks = (Math.PI / 180) * degrees + 444;

            switch (State) {
                case STATE_ONE:
                    double ticksRanRight = rightFront.getCurrentPosition();
                    double ticksRanLeft = leftFront.getCurrentPosition();
                    double remainingTicksRight = RRticks - ticksRanRight;
                    double remainingTicksLeft = LFticks - ticksRanLeft;


                    leftFront.setTargetPosition((int) LFticks);
                    leftRear.setTargetPosition((int) LRticks);
                    rightRear.setTargetPosition((int) RRticks);
                    rightFront.setTargetPosition((int) RFticks);

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
                    }

                    break;
            }
            return true;
        }


        }
