package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name = "DefaultTeleOp")
public class TeleOpTemplate extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor LDrive = hardwareMap.get(DcMotor.class, "LDrive");
        DcMotor RDrive = hardwareMap.get(DcMotor.class, "RDrive");

        LDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        RDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        while(opModeIsActive()) {
            double x = -gamepad1.right_stick_x;
            double y = gamepad1.left_stick_y;

            double LPower = y + x;
            double RPower = y - x;

            LDrive.setPower(LPower);
            RDrive.setPower(RPower);

            telemetry.addData("Left Power", LPower);
            telemetry.addData("Right Power", RPower);
            telemetry.update();
        }

        RDrive.setPower(0);
        LDrive.setPower(0);
    }

}
