package org.firstinspires.ftc.teamcode;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Drive Controls
 * <p>
 * Drive Controls class that powers mechanum drivetrain
 *
 *
 *
 //* @param an absolute URL giving the base location of the image
 //* @param name the location of the image, relative to the url argument
 * @return  the image at the specified URL
 * @see testWheels
 */

public class testWheelsEncoder {
    robot R;

    Gamepad gamepad1;
    Gamepad previousGamepad1;

    double leftfrontPower = 0;
    double leftrearPower = 0;
    double rightfrontPower = 0;
    double rightrearPower = 0;

    public testWheelsEncoder(robot Robot, Gamepad gp1, Gamepad pgp1) {
        R = Robot;
        gamepad1 = gp1;
        previousGamepad1 = pgp1; }

    public void initialize() {
        R.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        R.leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        R.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        R.rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void drive() {
        boolean dpadupPressed = gamepad1.dpad_up && !previousGamepad1.dpad_up;
        boolean dpaddownPressed = gamepad1.dpad_down && !previousGamepad1.dpad_down;
        boolean yPressed = gamepad1.y && !previousGamepad1.y;
        boolean aPressed = gamepad1.a && !previousGamepad1.a;
        if (dpadupPressed){
            if(abs(R.leftFront.getCurrentPosition() - 1000) > 50) {
                R.leftFront.setTargetPosition(1000);
                R.leftFront.setPower(0.8);
                R.leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                R.leftFront.setPower(0);
            }
            else{
                R.leftFront.setTargetPosition(0);
                R.leftFront.setPower(0.8);
                R.leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                R.leftFront.setPower(0);
            }
        }
        if (dpaddownPressed){
            if(abs(R.leftRear.getCurrentPosition() - 1000) > 50) {
                R.leftRear.setTargetPosition(1000);
                R.leftRear.setPower(0.8);
                R.leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                wait(500)
                R.leftRear.setPower(0);
            }
            else{
                R.leftRear.setTargetPosition(0);
                R.leftRear.setPower(0.8);
                R.leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                R.leftRear.setPower(0);
            }        }
        if (yPressed){
            if(abs(R.rightFront.getCurrentPosition() - 1000) > 50) {
                R.rightFront.setTargetPosition(1000);
                R.rightFront.setPower(0.8);
                R.rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                R.rightFront.setPower(0);
            }
            else{
                R.rightFront.setTargetPosition(0);
                R.rightFront.setPower(0.8);
                R.rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                R.rightFront.setPower(0);
            }        }
        if (aPressed) {
            if (abs(R.rightRear.getCurrentPosition() - 1000) > 50) {
                R.rightRear.setTargetPosition(1000);
                R.rightRear.setPower(0.8);
                R.rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                R.rightRear.setPower(0);
            } else {
                R.rightRear.setTargetPosition(0);
                R.rightRear.setPower(0.8);
                R.rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                R.rightRear.setPower(0);
            }
        }
    }
}
