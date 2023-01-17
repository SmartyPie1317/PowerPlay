package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robot.Robot;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Class for controlling the Arm of an FTC robot.
 */
public class Wrist {

    // Class variables
    Servo clawServo;
    Telemetry telemetry;
    double currentAngle = RobotMap.WRIST_SERVO_OPEN;

    /**
     * Constructor for the drivetrain
     *
     * @param hardwareMap the robot instance of the hardware map
     * @param telemetry the robot instance of the telemetry object
     */
    public Wrist(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        // Assign hardware objects
        clawServo = hardwareMap.get(Servo.class, RobotMap.WRIST_SERVO);
        clawServo.setPosition(RobotMap.SERVO_WRIST_DEFAULT);
    }

    /**
     * Move Servo
     *
     * @param gamepad The gamepad from which to read joystick values
     */
    private boolean clawOpen = false;
    private boolean xReleased = true;
    public void buttonServo(Gamepad gamepad) {

        if (gamepad.x) {
            if (xReleased) clawOpen = !clawOpen;
            xReleased = false;
        }
        else{
            xReleased = true;
        }

        double joystick = -gamepad.left_stick_y;
        if(Math.abs (joystick) > RobotMap.DEADZONE){
            currentAngle+= joystick * RobotMap.WRIST_RATE;
        }
        else if(gamepad.x){currentAngle = (clawOpen) ? RobotMap.WRIST_SERVO_OPEN : RobotMap.WRIST_SERVO_CLOSED;}



        currentAngle = safetyCheck(currentAngle);
        clawServo.setPosition(currentAngle);
   }

    private double safetyCheck(double inp) {
        double out = inp;
        out = Math.max(RobotMap.MINIMUM_WRIST_POSITION, out);
        out = Math.min(RobotMap.MAXIMUM_WRIST_POSITION, out);
        return out;
    }

    public void open(){
        clawServo.setPosition(RobotMap.WRIST_SERVO_OPEN);


    }

    public void close(){
        clawServo.setPosition(RobotMap.WRIST_SERVO_CLOSED);


    }


}
