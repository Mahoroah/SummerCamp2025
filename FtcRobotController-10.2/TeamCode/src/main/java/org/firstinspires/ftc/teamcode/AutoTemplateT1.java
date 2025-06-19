package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

//@Autonomous(name = "T1Auto")
public class AutoTemplateT1 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        // Define a Robot from the defined class
        Robot bot = new Robot(hardwareMap);

        // Method to wait for the start button to be pressed on control hub
        waitForStart();

        // Drive distance method defined in the Robot class

        bot.driveDistance(12, 0.5 );

        bot.turnLeft(60, 0.5);

        bot.driveDistance(37, 0.5);

    }


}