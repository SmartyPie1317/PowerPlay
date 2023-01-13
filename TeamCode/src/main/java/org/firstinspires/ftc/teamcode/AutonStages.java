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
    private Claw claw;
    private Wrist wrist;
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
        claw = new Claw(hardwareMap, telemetry);
    }

    public void mainStages() {
        int driveEncoderRight = drivetrain.getEncoderRight();
        int driveEncoderLeft = drivetrain.getEncoderLeft();
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

            stage = 40;
        }


    }
}


