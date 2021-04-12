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

    private final double diameter = 10;
    private final double countsPerMotorRev = 1120;
    private final double gearRed = 1;
    private final double circumference = Math.PI*diameter;
    private final double actualCountsPerRev = countsPerMotorRev * gearRed;
    private final double countsPerCM = actualCountsPerRev / circumference;
    private final double robotLength = 36;
    private final double robotWidth = 23;
    private final double robotDiameter = Math.sqrt(robotLength*robotLength+robotWidth*robotWidth);
    private final double robotCircumference = Math.PI*robotDiameter;
    private int nrRings = 0;
    private int servoStart=;   //Daca nu setati direct servoul modificati aici pana da bine
    private int servoEnd=;

    private DcMotor RampMotor = null;
    private DcMotor LauncherMotor = null;
    private DcMotor FRwheelMotor = null;
    private DcMotor FLwheelMotor = null;
    private DcMotor BRwheelMotor = null;
    private DcMotor BLwheelMotor = null;
    private Servo LauncherServo = null;
    private ColorRangeSensor lowSensor =null;
    private Rev2mDistanceSensor highSensor = null;

    Thread Fire = new Thread(new Runnable() {
        @Override
        public void run() {
            LauncherServo.setPosition(servoEnd);
            sleep(1500);
            LauncherServo.setPosition(servoStart);
        }
    });

    /**Autonomia presupune ca se incepe de pe linia din stanga, cu lansatorul in fata
    * !!! CODUL NU O SA MEARGA IN STAREA CURENTA, trebuie completat cu numele
     * din config ale componentelor in Initialize() si pozitiile bune ale servoului mai sus,
     * la servoStart si End
     *
    * Unde se modifica pentru tuning:
     * -In runOpMode()(functia principala), toate distantele si vitezele, chiar si
     * instructiunile in sine. VALORILE MELE SUNT DATE ORIENTATIV, SANSE EXTREM DE MARI
     * SA FIE PROASTE.
     * -la blocul de variabile, servoStart si servoEnd drept pozitiile servoului
     * -durata de sleep din diversele functii
     * -Scenario A, B, C pentru diversele zone de dropat Wobbleul
     * (DropWobble nu face nimic, dar l-am pus sa incerce sa se duca acolo sa vad cum merge)
     * ca sa nu mai incerce asta comentati tot switchul
     *
     * Codul presupune ca senzorii sunt montati, daca vreti fara ei comentati liniile
     * relevante din Initialize() si elinimati functia ScanRings() din runOpMode()
     **/


    @Override
    public void runOpMode()
    {

        Initialize();

        telemetry.addData("Status:", "Gata");
        telemetry.update();
        waitForStart();
        DriveRight(0.8,15);
        DriveForward(0.8,55);

        nrRings=ScanRings();

        DriveLeft(0.8,60);

        LauncherMotor.setPower(1);

        DriveForward(0.8,135);
        Launch(1);
        TurnLeft(0.5,5); //sau pur si simplu DriveLeft
        Launch(1);
        TurnLeft(0.5,5); //sau pur si simplu DriveLeft
        Launch(1);

        LauncherMotor.setPower(0);

        TurnRight(0.5,10); //omisa daca s-a facut Drive si nu Turn
        DriveForward(0.8,15);

        switch (nrRings)
        {
            case 0:
                ScenarioA();
                break;
            case 1:
                ScenarioB();
                break;
            case 4:
                ScenarioC();
                break;
        }



    }

    public void Initialize()
    {
        FRwheelMotor = hardwareMap.get(DcMotor.class,"fataDreapta");
        FLwheelMotor = hardwareMap.get(DcMotor.class,"fataStanga");
        BRwheelMotor = hardwareMap.get(DcMotor.class,"spateDreapta");
        BLwheelMotor = hardwareMap.get(DcMotor.class,"spateStanga");

        RampMotor = hardwareMap.get(DcMotor.class,"NUME DIN CONFIG");
        LauncherMotor = hardwareMap.get(DcMotor.class,"NUME DIN CONFIG");

        LauncherServo = hardwareMap.get(Servo.class,"NUME SERVO DIN CONFIG ");

        lowSensor =  hardwareMap.get(ColorRangeSensor.class, "NUME SENZOR CULOARE");
        highSensor = hardwareMap.get(Rev2mDistanceSensor.class, "NUME SENZOR DISTANTA");

        FRwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FLwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BRwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BLwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        FRwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        FRwheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FLwheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BRwheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BLwheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        RampMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        LauncherMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        FRwheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        BRwheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        FLwheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BLwheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        RampMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        LauncherMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        LauncherServo.setPosition(servoStart);


    }

    public void ScenarioA()
    {
        DriveRight(0.8,135);
        DropWobble();
    }

    public void ScenarioB()
    {
        DriveRight(0.8,75);
        DriveForward(0.8,60);
        DropWobble();
        DriveBackwards(0.8,60);
    }

    public void ScenarioC()
    {
        DriveRight(0.8,135);
        DriveForward(0.8,120);
        DropWobble();
        DriveBackwards(0.8,120);
    }

    public int ScanRings()
    {
        if(lowSensor.getDistance(DistanceUnit.CM)<10)
            if(highSensor.getDistance(DistanceUnit.CM)<10)
                return 4;
            else
                return 1;
        else
            return 0;
    }

    public void Launch(int nofRings)
    {
        if(!LauncherMotor.isBusy())
        {
            LauncherMotor.setPower(1);
            sleep(1000);
        }
        while(nofRings>0)
        {
            if(!Fire.isAlive())
            {
                Fire.start();
                nofRings--;
            }
        }
        sleep(500);
    }

    public void DropWobble()
    {

    }

    public void DriveTo(double speed, double frontRightCM, double backRightCM, double frontLeftCM, double backLeftCM)
    {

        if(opModeIsActive()) {

            int frontRightNewPos = FRwheelMotor.getCurrentPosition() + (int) (frontRightCM * countsPerCM);
            int backRightNewPos = BRwheelMotor.getCurrentPosition() + (int) (backRightCM * countsPerCM);
            int frontLeftNewPos = FLwheelMotor.getCurrentPosition() + (int) (frontLeftCM * countsPerCM);
            int backLeftNewPos = BLwheelMotor.getCurrentPosition() + (int) (backLeftCM * countsPerCM);

            FRwheelMotor.setTargetPosition(frontRightNewPos);
            BRwheelMotor.setTargetPosition(backRightNewPos);
            FLwheelMotor.setTargetPosition(frontLeftNewPos);
            BLwheelMotor.setTargetPosition(backLeftNewPos);

            FRwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FLwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BRwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BLwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            FRwheelMotor.setPower(speed);
            BRwheelMotor.setPower(speed);
            FLwheelMotor.setPower(speed);
            BLwheelMotor.setPower(speed);

            while(  opModeIsActive() &&
                    ( FRwheelMotor.isBusy() ||
                      BRwheelMotor.isBusy() ||
                      FLwheelMotor.isBusy() ||
                      BLwheelMotor.isBusy()
                    )
                ) {

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

            sleep(200);
        }

    }
    public void DriveForward(double speed, double distanceCM)
    {
        DriveTo(speed,distanceCM,distanceCM,distanceCM,distanceCM);
    }
    public void DriveBackwards(double speed, double distanceCM)
    {
        DriveTo(speed,-distanceCM,-distanceCM,-distanceCM,-distanceCM);
    }
    public void DriveRight(double speed, double distanceCM)
    {
        DriveTo(speed,-distanceCM,distanceCM,distanceCM,-distanceCM);
    }
    public void DriveLeft(double speed, double distanceCM)
    {
        DriveTo(speed,distanceCM,-distanceCM,-distanceCM,distanceCM);
    }
    public void TurnRight(double speed, double degrees)
    {
        double arcRatio=degrees/360;
        double distanceCM = robotCircumference*arcRatio;
        DriveTo(speed,-distanceCM,-distanceCM,distanceCM,distanceCM);
    }
    public void TurnLeft(double speed, double degrees)
    {
        double arcRatio=degrees/360;
        double distanceCM = (robotCircumference*arcRatio);
        DriveTo(speed,distanceCM,distanceCM,-distanceCM,-distanceCM);
    }
}