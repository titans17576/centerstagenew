package org.firstinspires.ftc.teamcode.titans17576.autonpaths;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


import org.firstinspires.ftc.teamcode.robot;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Config
@Autonomous(group = "drive")
public class autonPath1 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        robot drive = new robot(hardwareMap);
        drive.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        drive.leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        drive.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        drive.rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        TrajectorySequence blueLeft = drive.trajectorySequenceBuilder(new Pose2d(-60,-36,Math.toRadians(0)))
                //if TeamProp=left {
                //.lineToSplineHeading(new Pose2d(-35, -34, Math.toRadians(90)
                //else if TeamProp=center
                //.lineToSplineHeading(new Pose2d(-35, -36, Math.toRadians(0)))
                //else if TeamProp=right
                .lineToSplineHeading(new Pose2d(-35, -36, Math.toRadians(270)))
                //}
                .waitSeconds(0.5)
                .lineToLinearHeading(new Pose2d(-60,-36,Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(-56, -57, Math.toRadians(270)))
                .lineToSplineHeading(new Pose2d(-12, -58, Math.toRadians(270)))
                .waitSeconds(2)
                .lineToLinearHeading(new Pose2d(-12, 58, Math.toRadians(270)))
                .build();
        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            drive.followTrajectorySequence(blueLeft);
        }
    }
}