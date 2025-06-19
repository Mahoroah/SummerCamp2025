package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "DefaultAuto")
public class AutoTemplate extends LinearOpMode {

    @Override
    public void runOpMode() {

        // Define a Robot from the defined class
        NewBot bot = new NewBot(hardwareMap, telemetry);

        // Method to wait for the start button to be pressed on control hub
        waitForStart();

        while(opModeIsActive()) {
            bot.driveDistance(12, .5);


        }
    }


}