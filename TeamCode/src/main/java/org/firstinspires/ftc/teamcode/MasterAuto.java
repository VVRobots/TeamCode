package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name="MasterAuto", group="Auto")
public class MasterAuto extends LinearOpMode {

    private final double diameter = 9;
    private final double countsPerMotorRev = 1120;
    private final double gearRed = 40;
    private final double circumference = Math.PI*diameter;
    private final double actualCountsPerRev = countsPerMotorRev * gearRed;
    private final double countsPerCM = actualCountsPerRev / circumference;
    private final double robotRadius = 43;
    private int nrRings = 0;

    private DcMotor FRwheelMotor = null;
    private DcMotor FLwheelMotor = null;
    private DcMotor BRwheelMotor = null;
    private DcMotor BLwheelMotor = null;

    private DcMotor rampMotor = null;
    private DcMotor launchMotor = null;

    private Servo armServo = null;
    private Servo jointServo = null;
    private Servo clawServo = null;

    private  ColorRangeSensor lowSensor = null;
    private  Rev2mDistanceSensor highSensor = null;

    @Override
    public void runOpMode()
    {

        lowSensor = hardwareMap.get(ColorRangeSensor.class, "senzorCuloare");
        highSensor = hardwareMap.get(Rev2mDistanceSensor.class, "senzorDistanta");

        FRwheelMotor = hardwareMap.get(DcMotor.class,"fataDreapta");
        FLwheelMotor = hardwareMap.get(DcMotor.class,"fataStanga");
        BRwheelMotor = hardwareMap.get(DcMotor.class,"spateDreapta");
        BLwheelMotor = hardwareMap.get(DcMotor.class,"spateStanga");

        rampMotor = hardwareMap.get(DcMotor.class,"motorTraction1");
        launchMotor = hardwareMap.get(DcMotor.class,"motorTraction2");

        armServo = hardwareMap.get(Servo.class,"servoBrat3");
        jointServo = hardwareMap.get(Servo.class,"servoBrat1");
        clawServo = hardwareMap.get(Servo.class,"servoBrat2");


        FRwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FLwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BRwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BLwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rampMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        launchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        FRwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rampMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        launchMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FRwheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FLwheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BRwheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BLwheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rampMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        launchMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        FRwheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        BRwheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        FLwheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BLwheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        rampMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        launchMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        armServo.setDirection(Servo.Direction.FORWARD);
        jointServo.setDirection(Servo.Direction.FORWARD);
        clawServo.setDirection(Servo.Direction.FORWARD);

        armServo.setPosition(0);
        jointServo.setPosition(1);
        clawServo.setPosition(0.5);

        telemetry.addData("Status:", "Gata");
        telemetry.update();
        waitForStart();
        while(opModeIsActive())
        {
           DriveForward(1,60);
           DriveRight(1,10);

            /*if(lowSensor.getDistance(DistanceUnit.CM) <15.0)
            {
                    if(highSensor.getDistance(DistanceUnit.CM) <15.0)
                    {
                        nrRings=4;
                    }
                    else
                        nrRings=1;
            }*/


            telemetry.addData("Status:","Merge");
            telemetry.update();

        }
    }

    public void DriveForward(double speed, int distanceCM)
    {
        DriveTo(speed,distanceCM,distanceCM,distanceCM,distanceCM);
    }
    public void DriveBackwards(double speed, int distanceCM)
    {
        DriveTo(speed,-distanceCM,-distanceCM,-distanceCM,-distanceCM);
    }
    public void DriveRight(double speed, int distanceCM)
    {
        DriveTo(speed,-distanceCM,distanceCM,distanceCM,-distanceCM);
    }
    public void DriveLeft(double speed, int distanceCM)
    {
        DriveTo(speed,distanceCM,-distanceCM,-distanceCM,+distanceCM);
    }
    public void TurnRight(double speed, int degrees)
    {
        int distanceCM = (int)(360*robotRadius / degrees);
        DriveTo(speed,distanceCM,distanceCM,-distanceCM,-distanceCM);
    }
    public void TurnLeft(double speed, int degrees)
    {
        int distanceCM = (int)(360*robotRadius / degrees);
        DriveTo(speed,-distanceCM,-distanceCM,distanceCM,distanceCM);
    }
    public void DriveTo(double speed, int frontRightCM, int backRightCM, int frontLeftCM, int backLeftCM)
    {

        if(opModeIsActive()) {

            int frontRightNewPos = FRwheelMotor.getCurrentPosition() + (int) (frontRightCM * countsPerCM);
            int backRightNewPos = FRwheelMotor.getCurrentPosition() + (int) (backRightCM * countsPerCM);
            int frontLeftNewPos = FRwheelMotor.getCurrentPosition() + (int) (frontLeftCM * countsPerCM);
            int backLeftNewPos = FRwheelMotor.getCurrentPosition() + (int) (backLeftCM * countsPerCM);

            FRwheelMotor.setTargetPosition(frontRightNewPos);
            BRwheelMotor.setTargetPosition(backRightNewPos);
            FLwheelMotor.setTargetPosition(frontLeftNewPos);
            BLwheelMotor.setTargetPosition(backLeftNewPos);

            FRwheelMotor.setPower(speed);
            BRwheelMotor.setPower(speed);
            FLwheelMotor.setPower(speed);
            BLwheelMotor.setPower(speed);

            while(  opModeIsActive() &&
                    FRwheelMotor.isBusy() &&
                    BRwheelMotor.isBusy() &&
                    FLwheelMotor.isBusy() &&
                    BLwheelMotor.isBusy()) {

                telemetry.addData("Destination", "FR: %7d ; BR: %7d ; FL: %7d ; BL: %7d",
                        frontRightNewPos,
                        backRightNewPos,
                        frontLeftNewPos,
                        backLeftNewPos);

                telemetry.addData("Position", "FR: %7d ; BR: %7d ; FL: %7d ; BL: %7d",
                        FRwheelMotor.getCurrentPosition(),
                        BRwheelMotor.getCurrentPosition(),
                        FLwheelMotor.getCurrentPosition(),
                        BLwheelMotor.getCurrentPosition());

                telemetry.addData("lowSensor:", lowSensor.getDistance(DistanceUnit.CM));
                telemetry.addData("highSensor:", highSensor.getDistance(DistanceUnit.CM));

                telemetry.update();
            }

            FRwheelMotor.setPower(0);
            BRwheelMotor.setPower(0);
            FLwheelMotor.setPower(0);
            BLwheelMotor.setPower(0);

            FRwheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FLwheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BRwheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BLwheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(100);
        }

    }
}