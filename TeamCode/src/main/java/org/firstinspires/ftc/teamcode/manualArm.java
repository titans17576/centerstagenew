package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class manualArm {
    // OpMode variables
    robot R;
    Telemetry telemetry;
    Gamepad gamepad1;
    Gamepad previousGamepad1;

    // Import opmode variables when instance is created
    public manualArm(robot Robot, Telemetry t, Gamepad g1, Gamepad gp1) {
        R = Robot;
        telemetry = t;
        gamepad1 = g1;
        previousGamepad1 = gp1;
    }
    public void initialize(){
        R.armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        R.armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    // Method to move to a targeted position

    // Method to add encoders and status to telemetry

    // Update method for teleop implementation
    public void teleopUpdate() {
        if(gamepad1.y){
            R.armMotor.setPower(0.7);
        }
        else if(gamepad1.a){
            R.armMotor.setPower(-0.7);
        }
        else if(gamepad1.x){
            R.armMotor.setPower(0.2);
        }
        else if (gamepad1.right_trigger >= 0.5){
            R.armMotor.setPower(-1);
        }
        else {
            R.armMotor.setPower(0);
        }
    }

    // Update method for auton implementation
}