package org.firstinspires.ftc.teamcode;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class armFSM {
    // Enum for state memory
    public enum LiftState {
        ZERO,
        LOW,
        MID
    }

    // Position variables
    final int position_tolerance = 5;
    final int zero_position = 0;
    final int low_position = 800;
    final int mid_position = 1200; // max we could reach was like 1500 ticks so idk

    // LiftState instance variable
    LiftState liftState = LiftState.ZERO;

    // OpMode variables
    robotTemp R;
    Telemetry telemetry;
    Gamepad gamepad1;
    Gamepad previousGamepad1;

    // Import opmode variables when instance is created
    public armFSM(robotTemp Robot, Telemetry t, Gamepad g1, Gamepad gp1) {
        R = Robot;
        telemetry = t;
        gamepad1 = g1;
        previousGamepad1 = gp1;
    }

    // Method to move to a targeted position
    private void moveTo(int position) {
        R.armMotor.setTargetPosition(position);
        R.armMotor.setPower(0.8);
        R.armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        R.armMotor.setPower(0);

    }

    // Method to add encoders and status to telemetry
    private void updateTelemetry(String status) {
        // Add encoder position to telemetry
        telemetry.addData("Left Ticks", R.armMotor.getCurrentPosition());
        // Add lift position to telemetry
        telemetry.addData("Status of Lift", status);
    }

    // Update method for teleop implementation
    public void teleopUpdate() {
        telemetry.addLine("Lift Data");

        switch (liftState) {
            // Lift set to 0
            case ZERO:
                    // Check position and move if not at 0
                    if (abs(R.armMotor.getCurrentPosition() - zero_position) > position_tolerance) {
                        moveTo(zero_position);
                        telemetry.addData("Lift Moved", "TRUE");
                    } else {
                        telemetry.addData("Lift Moved", "FALSE");
                    }

                    // State inputs
                    if (gamepad1.dpad_up && !previousGamepad1.dpad_up) {
                        liftState = LiftState.LOW;
                        telemetry.addData("Move Requested", "TRUE");
                    } else {
                        telemetry.addData("Move Requested", "FALSE");
                    }

                    updateTelemetry("Zero");

                    break;

                // Lift set to 1/3
            case LOW:
                // Check position and move if not at low_position
                if (abs(R.armMotor.getCurrentPosition() - low_position) > position_tolerance) {
                    moveTo(low_position);
                    telemetry.addData("Lift Moved", "TRUE");
                } else {
                    telemetry.addData("Lift Moved", "FALSE");
                }

                // State inputs
                if (gamepad1.dpad_down && !previousGamepad1.dpad_down) {
                    liftState = LiftState.ZERO;
                    telemetry.addData("Move Requested", "TRUE");
                } else if (gamepad1.dpad_up && !previousGamepad1.dpad_up) {
                    liftState = LiftState.MID;
                    telemetry.addData("Move Requested", "TRUE");
                } else {
                    telemetry.addData("Move Requested", "FALSE");
                }

                updateTelemetry("LOW");

                break;

            //Lift set to 2/3
            case MID:
                // Check position and move if not at mid_position
                if (abs(R.armMotor.getCurrentPosition() - mid_position) > position_tolerance) {
                    moveTo(mid_position);
                    telemetry.addData("Lift Moved", "TRUE");
                } else {
                    telemetry.addData("Lift Moved", "FALSE");
                }

                // State inputs
                if (gamepad1.dpad_down && !previousGamepad1.dpad_down) {
                    liftState = LiftState.LOW;
                    telemetry.addData("Move Requested", "TRUE");
                } else {
                    telemetry.addData("Move Requested", "FALSE");
                }

                updateTelemetry("MID");

                break;


        }
    }

    // Update method for auton implementation
    public void autonUpdate(LiftState newState) {
        telemetry.addLine("Lift Data");

        switch (liftState) {
            // Lift set to 0
            case ZERO:
                // Check position and move if not at 0
                if (abs(R.armMotor.getCurrentPosition() - zero_position) <= position_tolerance) {
                    moveTo(zero_position);
                    telemetry.addData("Lift Moved", "TRUE");
                } else {
                    telemetry.addData("Lift Moved", "FALSE");
                }

                // State inputs
                if (newState == LiftState.LOW) {
                    liftState = LiftState.LOW;
                    telemetry.addData("Move Requested", "TRUE");
                } else if (newState == LiftState.MID){
                    liftState = LiftState.MID;
                    telemetry.addData("Move Requested", "TRUE");
                } else {
                        telemetry.addData("Move Requested", "FALSE");
                }

                updateTelemetry("Zero");

                break;

            // Lift set to 1/3
            case LOW:
                // Check position and move if not at low_position
                if (abs(R.armMotor.getCurrentPosition() - low_position) <= position_tolerance) {
                    moveTo(low_position);
                    telemetry.addData("Lift Moved", "TRUE");
                } else {
                    telemetry.addData("Lift Moved", "FALSE");
                }

                // State inputs
                if (newState == LiftState.ZERO) {
                    liftState = LiftState.ZERO;
                    telemetry.addData("Move Requested", "TRUE");
                } else if (newState == LiftState.MID){
                    liftState = LiftState.MID;
                    telemetry.addData("Move Requested", "TRUE");
                } else {
                    telemetry.addData("Move Requested", "FALSE");
                }

                updateTelemetry("LOW");

                break;

            //Lift set to 2/3
            case MID:
                // Check position and move if not at mid_position
                if (abs(R.armMotor.getCurrentPosition() - mid_position) <= position_tolerance) {
                    moveTo(mid_position);
                    telemetry.addData("Lift Moved", "TRUE");
                } else {
                    telemetry.addData("Lift Moved", "FALSE");
                }

                // State inputs
                if (newState == LiftState.ZERO) {
                    liftState = LiftState.ZERO;
                    telemetry.addData("Move Requested", "TRUE");
                } else if (newState == LiftState.LOW){
                    liftState = LiftState.LOW;
                    telemetry.addData("Move Requested", "TRUE");
                } else {
                    telemetry.addData("Move Requested", "FALSE");
                }

                updateTelemetry("MID");

                break;


        }
    }
}