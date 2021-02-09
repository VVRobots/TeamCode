
package org.firstinspires.ftc.teamcode;

//Librarii
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="DriverDob", group="Linear Opmode")
public class DriverDob extends LinearOpMode {

    /**DECLARARE VARIABILE/OBIECTE*/
    private DcMotor FRmotor=null;
    private DcMotor FLmotor=null;
    private DcMotor BRmotor=null;
    private DcMotor BLmotor=null;
    private DcMotor T1motor=null;
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

        FRmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        T1motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FRmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FLmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BRmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BLmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        T1motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        FRmotor.setDirection(DcMotorSimple.Direction.FORWARD);
        BRmotor.setDirection(DcMotorSimple.Direction.FORWARD);

        FLmotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BLmotor.setDirection(DcMotorSimple.Direction.REVERSE);
        telemetry.addData("Status", "Initializat");
        telemetry.update();
        /**AICI INCEPE DISTRACTIA*/
        //HAIDEEEEEEEEEEEEEEEEEEE
        waitForStart();

        while (opModeIsActive()) {
            /**BIND MISCARE PRIMARA ROTI 2 CATE 2 PE LATERALE
             * BUTON JOYSTICK AXA Y*/
            if(true) {
                FRmotor.setPower(gamepad1.left_stick_y);
                BRmotor.setPower(gamepad1.left_stick_y);

                FLmotor.setPower(gamepad1.right_stick_y);
                BLmotor.setPower(gamepad1.right_stick_y);
            }

            /**BIND SWTICH PENTRU BANDA RULANTA KAUFLAND 1
             * BUTON Y*/
            if(true){
                if((gamepad1.y==true)&&(booly1==false)) {booly2=true;}
                if(booly2==true) {T1motor.setPower(-1);}
                if((gamepad1.y==false)&&(booly2==true)) {booly1=true;}
                if((gamepad1.y==true)&&(booly1==true)) {booly2=false;}
                if(booly2==false) {T1motor.setPower(0);}
                if((gamepad1.y==false)&&(booly2==false)) {booly1=false;}
                telemetry.addData("BandaRulantaKaufland", booly2);
                telemetry.update();
            }

            /**BIND PT MISCAREA LATERALA
             * BUTON BUMPERS*/
            if(true) {
                while (gamepad1.left_bumper == true) {
                    FRmotor.setPower(-1);
                    BRmotor.setPower(1);

                    FLmotor.setPower(1);
                    BLmotor.setPower(-1);}
                while (gamepad1.right_bumper == true) {
                    FRmotor.setPower(1);
                    BRmotor.setPower(-1);

                    FLmotor.setPower(-1);
                    BLmotor.setPower(1);}
            }

            /**BIND PT MISCARE FINA FATA-SPATE-LATERALE
             * BUTON D-PAD*/
            if(true){
                while (gamepad1.dpad_up == true){
                    FRmotor.setPower(-0.25);
                    BRmotor.setPower(-0.25);
                    FLmotor.setPower(-0.25);
                    BLmotor.setPower(-0.25);}
                while (gamepad1.dpad_down == true){
                    FRmotor.setPower(0.25);
                    BRmotor.setPower(0.25);
                    FLmotor.setPower(0.25);
                    BLmotor.setPower(0.25);}
                while (gamepad1.dpad_left == true){
                    FRmotor.setPower(-0.25);
                    BRmotor.setPower(0.25);
                    FLmotor.setPower(0.25);
                    BLmotor.setPower(-0.25);}
                while (gamepad1.dpad_right == true){
                    FRmotor.setPower(0.25);
                    BRmotor.setPower(-0.25);
                    FLmotor.setPower(-0.25);
                    BLmotor.setPower(0.25);}
            }
        }
    }
}
