package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.exception.RobotCoreException;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.usb.RobotArmingStateNotifier;
import com.qualcomm.robotcore.util.Range;

import java.util.HashMap;

@TeleOp(name="Teleop1")
public class Teleop1 extends LinearOpMode {
    @Override
    public void runOpMode(){
        robot R = new robot(hardwareMap);

        Gamepad currentGamepad1 = new Gamepad();
        Gamepad previousGamepad1 = new Gamepad();

        driveControls driveControls = new driveControls(R, currentGamepad1,previousGamepad1);

        waitForStart();

        driveControls.initialize();

        while(opModeIsActive()){
            // Previous gamepad implementation code
            previousGamepad1.copy(currentGamepad1);
            currentGamepad1.copy(gamepad1);

            // Drive control update

            driveControls.drive();
            if(gamepad1.a && !previousGamepad1.a){
                R.claw.setDirection(DcMotorSimple.Direction.REVERSE);
                R.claw.setPower(1);
            }
            else if(gamepad1.y && !previousGamepad1.y){
                R.claw.setDirection(DcMotorSimple.Direction.FORWARD);
                R.claw.setPower(1);
            }
            else if(gamepad1.x && !previousGamepad1.x){
                R.claw.setPower(0);
            }

            if(gamepad1.dpad_down && !previousGamepad1.dpad_down){
                R.pixelPreload.setDirection(Servo.Direction.FORWARD);
                R.pixelPreload.setPosition(0);
            }
            else if(gamepad1.dpad_up && !previousGamepad1.dpad_up){
                R.pixelPreload.setDirection(Servo.Direction.FORWARD);
                R.pixelPreload.setPosition(1);
            }

            if(gamepad1.left_bumper && !previousGamepad1.left_bumper){
                R.actClaw.setPosition(0);
            }
            else if(gamepad1.right_bumper && !previousGamepad1.right_bumper){
                R.actClaw.setPosition(1);
            }

            if(gamepad1.dpad_left && !previousGamepad1.dpad_left){
                R.hinge.setPosition(0);
            }
            else if(gamepad1.dpad_right && !previousGamepad1.dpad_right){
                R.hinge.setPosition(1);
            }


                // Update telemetry data
            telemetry.update();
        }
    }
}
;