package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Test Teleop
 * <p>
 * Program for running tests on different systems on the robot
 * Currently used to test arm and wheels
 * </p>
 */
@TeleOp(name="TeleopTestEncoder")
public class TeleopTestEncoder extends LinearOpMode {
    @Override
    public void runOpMode(){
        robot R = new robot(hardwareMap);

        Gamepad currentGamepad1 = new Gamepad();
        Gamepad previousGamepad1 = new Gamepad();

        testWheelsEncoder TestWheelsEncoder = new testWheelsEncoder(R, currentGamepad1,previousGamepad1);

        waitForStart();

        TestWheelsEncoder.initialize();


        while(opModeIsActive()){
            // Previous gamepad implementation code
            previousGamepad1.copy(currentGamepad1);
            currentGamepad1.copy(gamepad1);

            // Drive control update
            TestWheelsEncoder.drive();
            telemetry.addData("leftFront",R.leftFront.getCurrentPosition());
            telemetry.addData("leftRear",R.leftRear.getCurrentPosition());
            telemetry.addData("rightFront",R.rightFront.getCurrentPosition());
            telemetry.addData("rightRear",R.rightRear.getCurrentPosition());


            // Update telemetry data
            telemetry.update();
        }
    }
}
;