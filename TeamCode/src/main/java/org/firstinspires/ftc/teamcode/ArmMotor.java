package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Class for controlling the Arm of an FTC robot.
 */
public class ArmMotor {

    // Class variables
    int encoderGoal;
    DcMotor motor;
    Telemetry telemetry;
    int startEncoder;
    boolean both;
    double movement;



    /**
     * Constructor for the drivetrain
     *
     * @param hardwareMap the robot instance of the hardware map
     * @param telemetry the robot instance of the telemetry object
     */
    public ArmMotor(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        // Assign hardware objects
        motor = hardwareMap.get(DcMotor.class, RobotMap.ARM_MOTOR);

        // Set the motor directions
        motor.setDirection(RobotMap.ARM_DIRECTION);

        //Set the encoder starting position
        startEncoder = motor.getCurrentPosition();
        zeroEncoder();
        encoderGoal = motor.getCurrentPosition()*RobotMap.REVERSE_ARM_ENCODER_VALUE;
    }


    /**
     * Set the arm motor power for both left and right motors
     *
     * @param gamepad The gamepad from which to read joystick values
     */
    public void manual(Gamepad gamepad, WinchMotor winchMotor) {

        manual(gamepad.left_stick_y, gamepad.y, gamepad.x, gamepad.left_bumper, gamepad.right_bumper, winchMotor);
    }
    public void manual(double leftStick, Boolean yButton, Boolean xButton,Boolean leftBumper,
                       Boolean rightBumper, WinchMotor winchMotor){
        double power;
        double encoderMax = startEncoder + RobotMap.ARM_DIFF;
        int encoderValue = getEncoder();
        double speedLimit = RobotMap.ARM_SPEED;
        double speedLimitDown = RobotMap.ARM_SPEED_DOWN;

        // greater than and less than signs may need to be switched

        power = leftStick * RobotMap.REVERSE_JOYSTICK_DIRECTION;
/*
        if(encoderValue >= encoderMax && power > 0){
            power = 0;
        }
        else if(encoderValue <= startEncoder && power < 0){
            power = 0;
        }

        if(yButton){
            encoderGoal = startEncoder + RobotMap.FULL_HEIGHT;
        }
        else if(xButton){
            encoderGoal = startEncoder + RobotMap.GRAB_HEIGHT;
        }
*/

        if (Math.abs(power) < RobotMap.DEADZONE) { //when u DON'T TOUCH THE JOySTICK
            double error = (encoderGoal - encoderValue);
            power = (RobotMap.ARM_KP * error);
        }
        else { //This happens when you TOUCH THE JOYSTICK
            encoderGoal = encoderValue;
        }



        // Limit speed of arm
        if(power < 0) {
            power *= speedLimitDown;
        }
        else {
            power *= speedLimit;
        }


        setPower(power);

        //output the encoder value//
        if (RobotMap.DISPLAY_ENCODER_VALUES) {
            telemetry.addData("Arm Encoder", encoderValue);
            telemetry.addData("Encoder Goal", encoderGoal);
            telemetry.addData("Power", power);
        }
/*
        if (leftBumper) {
            both = false;

        } else if (rightBumper) {
            both = true;
        }

        //experimental with moving both
        if(both){
            movement = leftStick * RobotMap.WINCHBOTHMATH;
            winchMotor.manual(movement, false, false);
        }
*/

    }

    private void setPower(double power){
        // Make sure power levels are within expected range
        power = safetyCheck(power);

        // Send calculated power to motors
        motor.setPower(power);
    }

    private double safetyCheck(double inp) {
        double out = inp;
        out = Math.max(-1.0, out);
        out = Math.min(1.0, out);
        return out;
    }

    public int getEncoder () {
        return RobotMap.REVERSE_ARM_ENCODER_VALUE * (motor.getCurrentPosition());
        //return RobotMap.REVERSE_ARM_ENCODER_VALUE * (motor.getCurrentPosition() - startEncoder);
    }
    public void zeroEncoder() {
        startEncoder = motor.getCurrentPosition();
    }


}