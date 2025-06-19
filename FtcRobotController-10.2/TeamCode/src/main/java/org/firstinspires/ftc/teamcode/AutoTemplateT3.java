package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name = "AutoT3")
@Disabled
public class AutoTemplateT3 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        // Define a Robot from the defined class
        Robot bot = new Robot(hardwareMap);

        // Method to wait for the start button to be pressed on control hub
        waitForStart();

        while(opModeIsActive()) {
            bot.driveDistance(6, .5);
//            telemetry.addData("target: ", bot.LDrive.getTargetPosition());
//            telemetry.addData("Current", bot.LDrive.getCurrentPosition());
//            telemetry.update();

//            bot.turnRight(50, .5);

//            bot.driveDistance(32, .5);
        }

    }


}