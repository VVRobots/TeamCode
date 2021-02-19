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


    private final  DcMotor FRwheelMotor=hardwareMap.get(DcMotor.class,"fataDreapta");
    private final DcMotor FLwheelMotor=hardwareMap.get(DcMotor.class,"fataStanga");
    private final DcMotor BRwheelMotor=hardwareMap.get(DcMotor.class,"spateDreapta");
    private final DcMotor BLwheelMotor=hardwareMap.get(DcMotor.class,"spateStanga");

    private final DcMotor inputMotor = hardwareMap.get(DcMotor.class,"motorTraction1");
    private final DcMotor launchMotor = hardwareMap.get(DcMotor.class,"motorTraction2");

    private final Servo armServo = hardwareMap.get(Servo.class,"motorBrat3");
    private final Servo jointServo = hardwareMap.get(Servo.class,"motorBrat1");
    private final Servo clawServo = hardwareMap.get(Servo.class,"motorBrat2");

    private final ColorRangeSensor lowSensor = hardwareMap.get(ColorRangeSensor.class, "senzorCuloare");
    private final Rev2mDistanceSensor highSensor = hardwareMap.get(Rev2mDistanceSensor.class, "senzorDistanta");

    @Override
    public void runOpMode()
    {

        FRwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FLwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BRwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BLwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        inputMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        launchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        FRwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        inputMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        launchMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FRwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FLwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BRwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BLwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        inputMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        launchMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        FRwheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        BRwheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        FLwheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BLwheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        inputMotor.setDirection(DcMotorSimple.Direction.FORWARD);
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



            telemetry.addData("Status:","Merge");
            telemetry.addData("lowSensor:", lowSensor.getDistance(DistanceUnit.CM));
            telemetry.addData("highSensor:", highSensor.getDistance(DistanceUnit.CM));
            telemetry.update();

        }
    }
}