package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Robot {




    // Ticks per Revolution is determined by doing motor's ticks/revolution * gearbox ratio
    private static final double TICKS_PER_REVOLUTION = 537.7 * 20;
    // You should know how to calculate the circumference of a circle
    private static final double WHEEL_CIRCUMFERENCE = 0.0 * Math.PI;
    // Ticks per inch is used in the programming of a sound autonomous to do distance based calcultions
    private static final double TICKS_PER_INCH = TICKS_PER_REVOLUTION / WHEEL_CIRCUMFERENCE;


    // Init statements for components
    public final DcMotor LDrive;
    public final DcMotor RDrive;
    public IMU imu;


    // Constructor for the robot (The default configs of the components on all new instances of the robot when referenced)
    public Robot(HardwareMap hardwareMap) {
        // Left Wheel's Motor define statement
        LDrive = hardwareMap.get(DcMotor.class, "LDrive");
        // Right Wheel's motor define statement
        RDrive = hardwareMap.get(DcMotor.class, "RDrive");

        // IMU define statement
        imu = hardwareMap.get(IMU.class, "imu");

        // The initialization of the gyros to refer to proper axis
        imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.UP,
                    RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
        )));

        // Setting the default operating style
        LDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Setting the default zero power behavior
        LDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    //----------------------------------------------------------------------------------------------------------------------------\\
    //---------------------------AFTER THIS LINE, THE ROBOT'S AUTONOMOUS FUNCTIONS WILL BE DEFINED -------------------------------\\
    //----------------------------------------------------------------------------------------------------------------------------\\

    // Hello how are you guys doing I hope youre doing well and I just want to wish you a good day and an amazing season gggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg


    public void driveDistance(double distance, double speed) {
        // Special motor configurations for method

        // Reset the internal encoders per action to 0
        RDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set motors to run given a target encoder distance
        LDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // Define the target distance by doing distance (In inches) * TICKS_PER_INCH
        double target = distance * TICKS_PER_INCH;

        // Set the target positions to be the motor's target positions (Using type casting)
        LDrive.setTargetPosition((int) target);
        RDrive.setTargetPosition((int) target);

        // Actionable loop to detect motor state
        while(LDrive.isBusy() && RDrive.isBusy()) {
            // Set motors to proper speed
            LDrive.setPower(speed);
            RDrive.setPower(speed);
        }

        // Action finished and motors stopped
        LDrive.setPower(0);
        RDrive.setPower(0);
    }

    // Getting the Rotational axis of the IMU
    public double getYaw() {
        // returning the value in Degrees for proper turning
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
