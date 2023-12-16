package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.exception.RobotCoreException;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.usb.RobotArmingStateNotifier;
import com.qualcomm.robotcore.util.Range;

import java.util.HashMap;

@TeleOp(name="TeleopTemp")
public class TeleopTemp extends LinearOpMode {
    @Override
    public void runOpMode(){
        robotTemp R = new robotTemp(hardwareMap);

        Gamepad currentGamepad1 = new Gamepad();
        Gamepad previousGamepad1 = new Gamepad();

        tempDriveControls TempDriveControls = new tempDriveControls(R, currentGamepad1,previousGamepad1);

        waitForStart();

        TempDriveControls.initialize();

        while(opModeIsActive()){
            // Previous gamepad implementation code
            previousGamepad1.copy(currentGamepad1);
            currentGamepad1.copy(gamepad1);

            // Drive control update
            TempDriveControls.drive();

            // Update telemetry data
            telemetry.update();
        }
    }
}
;