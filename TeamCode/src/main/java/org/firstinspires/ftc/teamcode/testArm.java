package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.robotcore.external.Telemetry;


public class testArm {
    robot R;
    Telemetry telemetry;
    Gamepad gamepad1;
    Gamepad previousGamepad1;

    double armMotorPower = 0;

    public testArm(robot Robot, Telemetry t, Gamepad gp1, Gamepad pgp1) {
        R = Robot;
        gamepad1 = gp1;
        telemetry = t;
        previousGamepad1 = pgp1; }

    public void initialize(){
        R.armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void measure() {
        telemetry.addData("Arm Encoder Ticks", R.armMotor.getCurrentPosition());
    }
}
