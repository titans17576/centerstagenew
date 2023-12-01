package org.firstinspires.ftc.teamcode;

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
 * @see driveControls
 */
public class driveControls {
    robot R;

    Gamepad gamepad1;
    Gamepad previousGamepad1;

    double slow; // Slow multiplier for robot
    double regSpeed;
    double slowSpeed;

    public driveControls(robot Robot, Gamepad gp1,Gamepad pgp1) { this(0.7,0.25, Robot, gp1,pgp1); }

    public driveControls(double regularSpeed, double slowedSpeed, robot Robot, Gamepad gp1, Gamepad pgp1){
        regSpeed = regularSpeed;
        slowSpeed = slowedSpeed;
        R = Robot;
        gamepad1 = gp1;
        previousGamepad1 = pgp1;
    }

    public void initialize(){
        R.leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        R.leftRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        R.rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        R.rightRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slow = regSpeed;
    }

    public void drive() {
        if (gamepad1.left_trigger >= 0.5 && previousGamepad1.left_trigger < 0.5) {
            slow = (slow == regSpeed)? slowSpeed : regSpeed;
        }

        // Drive inputs
        double drive = -gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        double strafe = gamepad1.left_stick_x;

        // Drive calculations
        R.leftRear.setPower(calcPower(drive + turn - strafe, slow));
        R.leftFront.setPower(calcPower(drive + turn + strafe, slow));
        R.rightRear.setPower(calcPower(drive - turn + strafe, slow));
        R.rightFront.setPower(calcPower(drive - turn - strafe, slow));

    }

    static double calcPower(double power, double slow) {
        //Returns the power^2 in the same direction as power bounded & multiplied by slow
        return Range.clip(power * power * Math.signum(power) * slow,-slow,slow);
    }
}
