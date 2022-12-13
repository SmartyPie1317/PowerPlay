package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class RobotMap {

    // Robot Parameters
    public static final Boolean DISPLAY_TIME = true;
    public static final double DEADZONE = 0.005;

    // Drivetrain Parameters
    public static final String LEFT_FRONT_MOTOR = "left_front_drive";
    public static final String RIGHT_FRONT_MOTOR = "right_front_drive";
    public static final String LEFT_BACK_MOTOR = "left_back_drive";
    public static final String RIGHT_BACK_MOTOR = "right_back_drive";
    public static final DcMotor.Direction LEFT_DRIVE_DIRECTION = DcMotor.Direction.FORWARD;
    public static final DcMotor.Direction RIGHT_DRIVE_DIRECTION = DcMotor.Direction.REVERSE;
    public static final int REVERSE_DRIVETRAIN_ENCODER_VALUE = -1;
    public static final double GYRO_CORRECTION_kP = .0375;

    // TankDrive Parameters
    public static final Boolean DISPLAY_MOTOR_VALUES = true;
    public static final Boolean REVERSE_JOYSTICKS = false;
    public static final double HIGHSPEED = 1.0;
    public static final double LOWSPEED = 0.3;

    // Encoder Parameters
    public static final Boolean DISPLAY_ENCODER_VALUES = true;
    public static final int ENCODER_TOLERANCE = 20;

    // Claw Parameters
    public static final String CLAW_SERVO = "claw_motor";
    public static final double SERVO_OPEN = 1.0;
    public static final double SERVO_CLOSED = 0.0;
    public static final double MINIMUM_SERVO_POSITION = 0.0;
    public static final double MAXIMUM_SERVO_POSITION = 1.0;
    public static final double SERVO_ANGLE_DEFAULT = SERVO_CLOSED;


    // Arm Parameters
    public static final int FULL_HEIGHT = 2200;
    public static final int GRAB_HEIGHT = 2600;
    public static final int ARM_DIFF = 3980;
    public static final String ARM_MOTOR = "arm_motor";
    public static final DcMotor.Direction ARM_DIRECTION = DcMotor.Direction.REVERSE;
    public static final double REVERSE_JOYSTICK_DIRECTION = 1.0;
    public static final double ARM_SPEED = 0.75;
    public static final double ARM_SPEED_DOWN = 0.75;
    public static final int REVERSE_ARM_ENCODER_VALUE = 1;
    public static final double ARM_KP = 0.03;
    public static final double WINCHBOTHMATH = 0.2;
    public static final double CLICKS = 3.0;

    //Winch Parameters
    public static final String WINCH_MOTOR = "winch_motor";
    public static final DcMotor.Direction WINCH_DIRECTION = DcMotor.Direction.REVERSE;
    public static final double WINCH_SPEED = 0.99;
    public static final int REVERSE_WINCH_ENCODER_VALUE = 1;
    public static final int WINCH_DIFF = 3980;

    //Webcam Parameters
    public static final String WEBCAM = "webcam";

    //Color Sensor Parameters
    public static final String COLOR_SENSOR = "color_sensor";


}
