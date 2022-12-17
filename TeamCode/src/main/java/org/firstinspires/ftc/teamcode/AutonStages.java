package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class AutonStages {

    public static enum Color {RED, BLUE;}

    public static enum Side {LEFT, RIGHT;}

    Color color;
    Side side;

    private DriveTrain drivetrain;
    private ArmMotor arm;
    private Claw claw;
    private Telemetry telemetry;
    private int stage = 0;
    private double expirationTime;
    private ElapsedTime runtime;
    private double driveTrainGoal;
    private int driveTrainEncoder;


    public AutonStages(Color color, Side side, HardwareMap hardwareMap,
                       Telemetry telemetry, ElapsedTime runtime) {
        this.color = color;
        this.side = side;
        this.runtime = runtime;
        this.telemetry = telemetry;
        drivetrain = new DriveTrain(hardwareMap, telemetry);
        arm = new ArmMotor(hardwareMap, telemetry);
        claw = new Claw(hardwareMap, telemetry);
    }

    public void mainStages() {
        arm.manual(0, false, false, false, false);
        int driveEncoderRight = drivetrain.getEncoderRight();
        int driveEncoderLeft = drivetrain.getEncoderLeft();
        int armEncoder = arm.getEncoder();
        driveTrainEncoder = driveEncoderRight;
        drivetrain.outputEncoders();
        telemetry.addData("STAGE:", stage);


        if (stage == 0) {

            stage = 10;
        } else if (stage == 10) {
            //strafe
           if (color == Color.BLUE) {
               driveTrainGoal = driveTrainEncoder + 1750;
               drivetrain.arcadeDrive(0, 0, 0.5, false, true);
               //rotate, forward/back, strafe
               expirationTime = runtime.time() + 3.0;
           }
            if (color == Color.RED) {
                driveTrainGoal = driveTrainEncoder + 1750;
                drivetrain.arcadeDrive(0, 0, -0.5, false, true);
                //rotate, forward/back, strafe
                expirationTime = runtime.time() + 3.0;
            }
            //SHAWTY
            stage = 20;
        } else if (stage == 20) {
            //stop
            if (driveTrainEncoder > driveTrainGoal || (runtime.time() > expirationTime)) {
                drivetrain.arcadeDrive(0, 0, .0, false, true);
                stage = 30;
            }


        }
        else if (stage == 30){
            arm.setEncoderGoal(armEncoder - 70);
            stage = 40;
        }


    }
}


