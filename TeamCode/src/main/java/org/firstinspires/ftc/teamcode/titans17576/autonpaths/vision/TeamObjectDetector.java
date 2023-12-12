package org.firstinspires.ftc.teamcode.titans17576.autonpaths.vision;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class TeamObjectDetector extends OpenCvPipeline {
    Telemetry telemetry;
    Mat mat = new Mat();
    public enum Location {
        LEFT,
        CENTER,
        RIGHT,
        NOT_FOUND
    }
    private final Object sync = new Object();

    vision.Location location = vision.Location.NOT_FOUND;



    static final Rect LEFT_ROI = new Rect(
            new Point(0, 0),
            new Point(106, 240));
    static final Rect CENTER_ROI = new Rect(
            new Point(107,0),
            new Point(213,240));
    static final Rect RIGHT_ROI = new Rect(
            new Point(214, 0),
            new Point(320, 240));
    static double PERCENT_COLOR_THRESHOLD = 0.4;

    public TeamObjectDetector(){}
    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
        Scalar lowHSV = new Scalar(95, 50, 70);
        Scalar highHSV = new Scalar(125, 255, 255);

        Core.inRange(mat, lowHSV, highHSV, mat);

        Mat left = mat.submat(LEFT_ROI);
        Mat center = mat.submat(CENTER_ROI);
        Mat right = mat.submat(RIGHT_ROI);

        double leftValue = Core.sumElems(left).val[0] / LEFT_ROI.area() / 255;
        double centerValue = Core.sumElems(center).val[0] / CENTER_ROI.area() / 255;
        double rightValue = Core.sumElems(right).val[0] / RIGHT_ROI.area() / 255;

        left.release();
        center.release();
        right.release();

        /*telemetry.addData("Left raw value", (int) Core.sumElems(left).val[0]);
        telemetry.addData("Right raw value", (int) Core.sumElems(right).val[0]);
        telemetry.addData("Left percentage", Math.round(leftValue * 100) + "%");
        telemetry.addData("Right percentage", Math.round(rightValue * 100) + "%");*/

        boolean leftSpike = leftValue > PERCENT_COLOR_THRESHOLD;
        boolean centerSpike = centerValue > PERCENT_COLOR_THRESHOLD;
        boolean rightSpike = rightValue > PERCENT_COLOR_THRESHOLD;
        if(!(leftSpike || centerSpike || rightSpike)){
            location = vision.Location.NOT_FOUND;
            telemetry.addData("Spike Location", "not found");
        }
        else if (rightValue > centerValue && rightValue > leftValue) {
            location = vision.Location.RIGHT;
            telemetry.addData("Spike Location", "right");
        }
        else if (centerValue > rightValue && centerValue > leftValue) {
            location = vision.Location.CENTER;
            telemetry.addData("Spike Location", "center");
        }
        else if (leftValue > centerValue && leftValue > rightValue) {
            location = vision.Location.LEFT;
            telemetry.addData("Spike Location", "left");
        }
        telemetry.update();

        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2RGB);

        Scalar colorStone = new Scalar(255, 0, 0);
        Scalar colorSkystone = new Scalar(0, 255, 0);

        Imgproc.rectangle(mat, LEFT_ROI, location == vision.Location.LEFT? colorSkystone:colorStone);
        Imgproc.rectangle(mat, CENTER_ROI, location == vision.Location.CENTER? colorSkystone:colorStone);
        Imgproc.rectangle(mat, RIGHT_ROI, location == vision.Location.RIGHT? colorSkystone:colorStone);

        return mat;
    }

    public vision.Location getLocation() {
        return location;
    }
    public vision.Location getColor() {
        synchronized (sync) {
            return location;
        }
    }
}