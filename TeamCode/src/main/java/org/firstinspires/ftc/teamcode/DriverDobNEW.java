/**Copyright Dobrinel */
package org.firstinspires.ftc.teamcode;

//Librarii
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogOutput;
import com.qualcomm.robotcore.hardware.AnalogOutputController;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name="DriverDobNEW", group="Linear Opmode")
public class DriverDobNEW extends LinearOpMode {

    /**DECLARARE VARIABILE/OBIECTE*/

    private DcMotor FRmotor=null;
    private DcMotor FLmotor=null;
    private DcMotor BRmotor=null;
    private DcMotor BLmotor=null;
    private int marsarier=-1;


    boolean booly100=false;
    boolean booly200=false;

    @Override
    public void runOpMode() {

        /**SETUP MOTOARE*/
        FRmotor=hardwareMap.get(DcMotor.class,"fataDreapta");
        FLmotor=hardwareMap.get(DcMotor.class,"fataStanga");
        BRmotor=hardwareMap.get(DcMotor.class,"spateDreapta");
        BLmotor=hardwareMap.get(DcMotor.class,"spateStanga");

        FRmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        FRmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FLmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BRmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BLmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        FRmotor.setDirection(DcMotorSimple.Direction.FORWARD);
        BRmotor.setDirection(DcMotorSimple.Direction.FORWARD);

        FLmotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BLmotor.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Status", "Initializat");
        telemetry.update();
        /**AICI INCEPE DISTRACTIA*/
        waitForStart();

        while (opModeIsActive()) {

            /**BIND MISCARE PRIMARA ROTI 2 CATE 2 PE LATERALE
             * G1 BUTON JOYSTICK AXA Y*/
            /**++MARSARIER*/
            if((gamepad1.a==true)&&(booly100==false)) {booly200=true;}
            if(booly200==true) {
                marsarier=1;

                FRmotor.setPower(gamepad1.left_stick_y*marsarier);
                BRmotor.setPower(gamepad1.left_stick_y*marsarier);
                FLmotor.setPower(gamepad1.right_stick_y*marsarier);
                BLmotor.setPower(gamepad1.right_stick_y*marsarier); }
            if((gamepad1.a==false)&&(booly200==true)) {booly100=true;}
            if((gamepad1.a==true)&&(booly100==true)) {booly200=false;}
            if(booly200==false) {
                marsarier=-1;
                FRmotor.setPower(gamepad1.right_stick_y*marsarier);
                BRmotor.setPower(gamepad1.right_stick_y*marsarier);
                FLmotor.setPower(gamepad1.left_stick_y*marsarier);
                BLmotor.setPower(gamepad1.left_stick_y*marsarier); }
            if((gamepad1.a==false)&&(booly200==false)) {booly100=false;}


            /**BIND PT MISCAREA LATERALA
             * G1 BUTON BUMPERS*/
            if (gamepad1.left_bumper) {                 /**MISCARE LATERALA STANGA*/
                FRmotor.setPower(-1*marsarier);                   /**BIND LEFT BUMPER*/
                BRmotor.setPower(1*marsarier);
                FLmotor.setPower(1*marsarier);
                BLmotor.setPower(-1*marsarier);}
            if (gamepad1.right_bumper) {                /**MISCARE LATERALA DREAPTA*/
                FRmotor.setPower(1*marsarier);                    /**BIND RIGHT BUMPER*/
                BRmotor.setPower(-1*marsarier);
                FLmotor.setPower(-1*marsarier);
                BLmotor.setPower(1*marsarier);}
            FRmotor.setPower(0);                    /**RESETARE VALORI*/
            BRmotor.setPower(0);
            FLmotor.setPower(0);
            BLmotor.setPower(0);

            /**BIND PT MISCARE FINA FATA-SPATE-LATERALE
             * G1 BUTON D-PAD*/

            if (gamepad1.dpad_up){
                FRmotor.setPower(-0.45*marsarier);
                BRmotor.setPower(-0.45*marsarier);
                FLmotor.setPower(-0.45*marsarier);
                BLmotor.setPower(-0.45*marsarier);}
            if (gamepad1.dpad_down){
                FRmotor.setPower(0.45*marsarier);
                BRmotor.setPower(0.45*marsarier);
                FLmotor.setPower(0.45*marsarier);
                BLmotor.setPower(0.45*marsarier);}
            if (gamepad1.dpad_left){
                FRmotor.setPower(-0.45*marsarier);
                BRmotor.setPower(0.45*marsarier);
                FLmotor.setPower(0.45*marsarier);
                BLmotor.setPower(-0.45*marsarier);}
            if (gamepad1.dpad_right){
                FRmotor.setPower(0.45*marsarier);
                BRmotor.setPower(-0.45*marsarier);
                FLmotor.setPower(-0.45*marsarier);
                BLmotor.setPower(0.45*marsarier);}

            /**test*/
            if(gamepad1.b){
                FRmotor.setPower(1*marsarier);
                BRmotor.setPower(-1*marsarier);
                FLmotor.setPower(1*marsarier);
                BLmotor.setPower(-1*marsarier);}

             /**test*/

            FRmotor.setPower(0);
            BRmotor.setPower(0);
            FLmotor.setPower(0);
            BLmotor.setPower(0);



            if(booly100==false) telemetry.addData("Gearbox","1st Gear");
            if(booly100==true) telemetry.addData("Gearbox","Reverse");
            /*if(booly10==false) telemetry.addData("troubleshooting","off");
            if(booly10==true) telemetry.addData("Troubleshooting","ON");*/

            telemetry.update();

        }   /**while (opModeIsActive())*/
    }   /**RunOpMode*/

}   /**LinearOpMode*/
