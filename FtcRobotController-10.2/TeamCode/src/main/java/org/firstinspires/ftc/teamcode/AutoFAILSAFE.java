package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@Autonomous(name = "FailsafeAuto")
@Disabled
public class AutoFAILSAFE extends LinearOpMode {

    private DcMotor LDrive;
    private DcMotor RDrive;

    private IMU imu;

    static final double TICKS_PER_REV = 450;
    static final double WHEEL_DIAMETER = 4;

    static final double TICKS_PER_INCH = TICKS_PER_REV / (WHEEL_DIAMETER * Math.PI);

    @Override
    public void runOpMode() {

        imu = hardwareMap.get(IMU.class, "imu");

        imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
        )));

        LDrive = hardwareMap.get(DcMotor.class, "LDrive");
        RDrive = hardwareMap.get(DcMotor.class, "RDrive");


        LDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        LDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);





















        waitForStart();


        // Place instructions here

        driveDistance(12, 0.5);





    }




/*
DO NOT GO PAST THIS POINT
DO NOT GO PAST THIS POINT
DO NOT GO PAST THIS POINT
DO NOT GO PAST THIS POINT
DO NOT GO PAST THIS POINT
DO NOT GO PAST THIS POINT
DO NOT GO PAST THIS POINT
DO NOT GO PAST THIS POINT
DO NOT GO PAST THIS POINT
DO NOT GO PAST THIS POINT
DO NOT GO PAST THIS POINT
DO NOT GO PAST THIS POINT
DO NOT GO PAST THIS POINT
DO NOT GO PAST THIS POINT
DO NOT GO PAST THIS POINT
DO NOT GO PAST THIS POINT
DO NOT GO PAST THIS POINT

 */




    public void driveDistance(double inches, double speed) {

        double LTarget = LDrive.getCurrentPosition() + (inches * TICKS_PER_INCH);
        double RTarget = RDrive.getCurrentPosition() + (inches * TICKS_PER_INCH);

        if(opModeIsActive()) {
            LDrive.setTargetPosition((int) LTarget);
            RDrive.setTargetPosition((int) RTarget);


            LDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            LDrive.setPower(speed);
            RDrive.setPower(speed);


            LDrive.setPower(0);
            RDrive.setPower(0);

        }
    }

    public double getYaw() {
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
    }

    public double wrapAngle(double currentHeading) {
        return (currentHeading + 180) % 360 - 180;
    }

    public void turnLeft(double degrees, double speed) {
        RDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        RDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        imu.resetYaw();

        double yawError;

        if(opModeIsActive()) {
            while (Math.abs(yawError = wrapAngle(getYaw() - degrees)) > 10) {
                if (yawError > 0) {
                    LDrive.setPower(speed);
                    RDrive.setPower(-speed);
                } else {
                    LDrive.setPower(-speed);
                    RDrive.setPower(speed);
                }
            }
        }

        LDrive.setPower(0);
        RDrive.setPower(0);

    }


}
