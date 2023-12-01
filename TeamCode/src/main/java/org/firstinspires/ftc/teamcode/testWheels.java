package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

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

public class testWheels {
    robot R;

    Gamepad gamepad1;
    Gamepad previousGamepad1;

    double leftfrontPower = 0;
    double leftrearPower = 0;
    double rightfrontPower = 0;
    double rightrearPower = 0;

    public testWheels(robot Robot, Gamepad gp1, Gamepad pgp1) {
        R = Robot;
        gamepad1 = gp1;
        previousGamepad1 = pgp1; }

    public void initialize(){
        R.leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        R.leftRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        R.rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        R.rightRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void drive() {
        boolean dpadupPressed = gamepad1.dpad_up && !previousGamepad1.dpad_up;
        boolean dpaddownPressed = gamepad1.dpad_down && !previousGamepad1.dpad_down;
        boolean yPressed = gamepad1.y && !previousGamepad1.y;
        boolean aPressed = gamepad1.a && !previousGamepad1.a;

        if (dpadupPressed){
            leftfrontPower = (R.leftFront.getPower() == 0)? 0.7 : 0;
        }
        if (dpaddownPressed){
            leftrearPower = (R.leftRear.getPower() == 0)? 0.7 : 0;
        }
        if (yPressed){
            rightfrontPower = (R.rightFront.getPower() == 0)? 0.7 : 0;
        }
        if (aPressed){
            rightrearPower = (R.rightRear.getPower() == 0)? 0.7 : 0;
        }
        R.leftFront.setPower(leftfrontPower);
        R.leftRear.setPower(leftrearPower);
        R.rightFront.setPower(rightfrontPower);
        R.rightRear.setPower(rightrearPower);
    }

}
