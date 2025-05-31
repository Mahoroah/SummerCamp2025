package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "DefaultAuto")
public class AutoTemplate extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        // Define a Robot from the defined class
        Robot bot = new Robot(hardwareMap);

        // Method to wait for the start button to be pressed on control hub
        waitForStart();

        // Drive distance method defined in the Robot class
        bot.driveDistance(15, 0.5);

        // Turn to target angle of defined method at specific speed
        bot.turnRight(15, 0.5);

        // Drive distance method defined in the Robot class
        bot.driveDistance(15, 0.5);
    }


}
