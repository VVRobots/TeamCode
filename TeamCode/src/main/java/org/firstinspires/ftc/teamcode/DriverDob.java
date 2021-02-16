/**Copyright Dobrinel */
package org.firstinspires.ftc.teamcode;

//Librarii
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="DriverDob", group="Linear Opmode")
public class DriverDob extends LinearOpMode {

    /**DECLARARE VARIABILE/OBIECTE*/

    private DcMotor FRmotor=null;
    private DcMotor FLmotor=null;
    private DcMotor BRmotor=null;
    private DcMotor BLmotor=null;
    private DcMotor T1motor=null;
    private DcMotor T2motor=null;
    private Servo SVmotor1=null;
    private Servo SVmotor2=null;
    private Servo SVmotor3=null;
    boolean booly1=false;
    boolean booly2=false;

    @Override
    public void runOpMode() {

        /**SETUP MOTOARE*/
        FRmotor=hardwareMap.get(DcMotor.class,"fataDreapta");
        FLmotor=hardwareMap.get(DcMotor.class,"fataStanga");
        BRmotor=hardwareMap.get(DcMotor.class,"spateDreapta");
        BLmotor=hardwareMap.get(DcMotor.class,"spateStanga");
        T1motor=hardwareMap.get(DcMotor.class,"motorTraction1");
        T2motor=hardwareMap.get(DcMotor.class,"motorBrat1");
        SVmotor1=hardwareMap.get(Servo.class,"servoBrat1");
        SVmotor2=hardwareMap.get(Servo.class,"servoBrat2");
        SVmotor3=hardwareMap.get(Servo.class,"servoBrat3");


        FRmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        T1motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        T2motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        FRmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FLmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BRmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BLmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        T1motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        T2motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        FRmotor.setDirection(DcMotorSimple.Direction.FORWARD);
        BRmotor.setDirection(DcMotorSimple.Direction.FORWARD);

        FLmotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BLmotor.setDirection(DcMotorSimple.Direction.REVERSE);

        SVmotor1.setDirection(Servo.Direction.FORWARD);
        SVmotor1.setPosition(0);
        SVmotor2.setDirection(Servo.Direction.FORWARD);
        SVmotor2.setPosition(0);
        SVmotor3.setDirection(Servo.Direction.FORWARD);
        SVmotor3.setPosition(0.5);

        telemetry.addData("Status", "Initializat");
        telemetry.update();
        /**AICI INCEPE DISTRACTIA*/

        waitForStart();

        while (opModeIsActive()) {
            /**BIND MISCARE PRIMARA ROTI 2 CATE 2 PE LATERALE
             * BUTON JOYSTICK AXA Y*/
                FRmotor.setPower(gamepad1.left_stick_y);
                BRmotor.setPower(gamepad1.left_stick_y);

                FLmotor.setPower(gamepad1.right_stick_y);
                BLmotor.setPower(gamepad1.right_stick_y);

            /**BIND SWTICH PENTRU BANDA RULANTA KAUFLAND 1
             * BUTON Y/JOISTICK*/
                if((gamepad2.y==true)&&(booly1==false)) {booly2=true;}
                if(booly2==true) {T1motor.setPower(1);}
                if((gamepad2.y==false)&&(booly2==true)) {booly1=true;}
                if((gamepad2.y==true)&&(booly1==true)) {booly2=false;}
                if(booly2==false) {T1motor.setPower(gamepad2.left_stick_y);}
                if((gamepad2.y==false)&&(booly2==false)) {booly1=false;}
                telemetry.addData("BandaRulantaKaufland", booly2);
                telemetry.update();

            /**BIND PT MISCAREA LATERALA
             * BUTON BUMPERS*/
                if (gamepad1.left_bumper) {                 /**MISCARE LATERALA STANGA*/
                    FRmotor.setPower(-1);                   /**BIND LEFT BUMPER*/
                    BRmotor.setPower(1);
                    FLmotor.setPower(1);
                    BLmotor.setPower(-1);}

                if (gamepad1.right_bumper) {                /**MISCARE LATERALA DREAPTA*/
                    FRmotor.setPower(1);                    /**BIND RIGHT BUMPER*/
                    BRmotor.setPower(-1);
                    FLmotor.setPower(-1);
                    BLmotor.setPower(1);}

                    FRmotor.setPower(0);                    /**RESETARE VALORI*/
                    BRmotor.setPower(0);
                    FLmotor.setPower(0);
                    BLmotor.setPower(0);

            /**BIND BRAT
             * G2 BUMPERS/A/B/X/JOYSTICK*/
                if(gamepad2.left_bumper) SVmotor2.setPosition(0.5);     /**DESCHIDERE CLESTE*/
                if(gamepad2.right_bumper) SVmotor2.setPosition(1);      /**INCHIDERE CLESTE*/
                if(gamepad2.x) SVmotor1.setPosition(0);         /**ROTATIE CAP DE BRAT*/
                if(gamepad2.a) SVmotor1.setPosition(0.5);
                if(gamepad2.b) SVmotor1.setPosition(1);
                SVmotor3.setPosition(gamepad2.right_stick_y/2+0.5);     /**RIDICARE/COBORARE BRAT*/

            /**BIND PT MISCARE FINA FATA-SPATE-LATERALE
             * BUTON D-PAD*/

                if (gamepad1.dpad_up){
                    FRmotor.setPower(-0.25);
                    BRmotor.setPower(-0.25);
                    FLmotor.setPower(-0.25);
                    BLmotor.setPower(-0.25);}
                if (gamepad1.dpad_down){
                    FRmotor.setPower(0.25);
                    BRmotor.setPower(0.25);
                    FLmotor.setPower(0.25);
                    BLmotor.setPower(0.25);}
                if (gamepad1.dpad_left){
                    FRmotor.setPower(-0.25);
                    BRmotor.setPower(0.25);
                    FLmotor.setPower(0.25);
                    BLmotor.setPower(-0.25);}
                if (gamepad1.dpad_right){
                    FRmotor.setPower(0.25);
                    BRmotor.setPower(-0.25);
                    FLmotor.setPower(-0.25);
                    BLmotor.setPower(0.25);}
            FRmotor.setPower(0);
            BRmotor.setPower(0);
            FLmotor.setPower(0);
            BLmotor.setPower(0);
        }
    }
}
