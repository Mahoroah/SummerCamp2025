package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class NewBot {

    Telemetry telemetry;
    private static final double TICKS_PER_REVOLUTION = 435 * 1;
    // You should know how to calculate the circumference of a circle
    private static final double WHEEL_CIRCUMFERENCE = 4 * Math.PI;
    // Ticks per inch is used in the programming of a sound autonomous to do distance based calcultions
    // Basic Wheels Tetrix wheels are 4", Big wheel is 4.6875"


    private static final double TICKS_PER_INCH = TICKS_PER_REVOLUTION / WHEEL_CIRCUMFERENCE;

    private DcMotor LDrive;
    private DcMotor RDrive;

    private IMU imu;

    public NewBot(HardwareMap hardwareMap, Telemetry telem) {
        LDrive = hardwareMap.get(DcMotor.class, "LDrive");
        RDrive = hardwareMap.get(DcMotor.class, "RDrive");

        telemetry = telem;
        LDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Setting the default zero power behavior
        LDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        imu = hardwareMap.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
        )));
    }

    public void driveDistance(double distance, double speed) {

        RDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set motors to run given a target encoder distance
        LDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        double LTarget = LDrive.getCurrentPosition() + (distance * TICKS_PER_INCH);
        double RTarget = RDrive.getCurrentPosition() + (distance * TICKS_PER_INCH);

        LDrive.setTargetPosition((int) LTarget);
        RDrive.setTargetPosition((int) RTarget);

        LDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(LDrive.isBusy() && RDrive.isBusy()) {
            LDrive.setPower(speed);
            RDrive.setPower(speed);
            addTelemetry(telemetry);
        }

        LDrive.setPower(0);
        RDrive.setPower(0);
    }

    public void addTelemetry(Telemetry telemetry) {
        telemetry.addData("LDrive Target: ", LDrive.getTargetPosition());
        telemetry.addData("RDrive Target: ", RDrive.getTargetPosition());
        telemetry.addData("LDrive Current: ", LDrive.getCurrentPosition());
        telemetry.addData("RDrive Current: ", RDrive.getCurrentPosition());
        telemetry.addData("LDrive Power: ", LDrive.getPower());
        telemetry.addData("LDrive Power: ", LDrive.getPower());
        telemetry.update();

    }
    public double getYaw() {
        // returning the value in Degrees for proper tur    // Getting the Rotational axis of the IMU
        // Getting the Rotational axis of the IMU
        // Getting the Rotational axis of the IMU

        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
    }

    // Wrapping a provided angle (in another function) to a 360 degree angle
    public double wrapAngle(double heading) {
        heading += 100;
        heading = (heading % 360 + 360) % 360;
        heading -= 100;
        return heading;
    }


    public void turnLeft(double degrees, double speed) {
        RDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        imu.resetYaw();

        double yawError;

        while(Math.abs(yawError = wrapAngle(getYaw() - degrees)) > 10 ) {
            if (yawError > 0) {
                LDrive.setPower(speed);
                RDrive.setPower(-speed);
            } else {
                LDrive.setPower(-speed);
                RDrive.setPower(speed);
            }
        }

        LDrive.setPower(0);
        RDrive.setPower(0);
    }

    public void turnRight(double degrees, double speed) {
        turnLeft(-degrees, speed);
    }

}

