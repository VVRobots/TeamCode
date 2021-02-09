

package org.firstinspires.ftc.teamcode;

//Librarii
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp(name="Driver", group="Linear Opmode")
public class Driver extends LinearOpMode {

    //declarare variabile/obiecte
    private DcMotor FRmotor=null;
    private DcMotor FLmotor=null;
    private DcMotor BRmotor=null;
    private DcMotor BLmotor=null;
    private DcMotor thrower=null;
    boolean toggle=false;
    boolean held=false;

    @Override
    public void runOpMode() {

        //setup motoare
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


        waitForStart();

        while (opModeIsActive()) {

            //controleaza motorul de aruncare
            if (gamepad1.b && !held)
            {
                held=true;
                toggle = !toggle;
            }
            }
        if(!gamepad1.b)
            held=false;

        //deplasare laterala
            while(gamepad1.left_bumper)
            {
                FRmotor.setPower(-0.69);
                BRmotor.setPower(0.69);
                FLmotor.setPower(-0.69);
                BLmotor.setPower(0.69);
            }

            while(gamepad1.right_bumper)
            {
                FRmotor.setPower(0.69);
                BRmotor.setPower(-0.69);
                FLmotor.setPower(0.69);
                BLmotor.setPower(-0.69);
            }

            //restu'
            if(toggle)
                thrower.setPower(1);
            FRmotor.setPower(-gamepad1.right_stick_y);
            BRmotor.setPower(-gamepad1.right_stick_y);

            FLmotor.setPower(-gamepad1.left_stick_y);
            BLmotor.setPower(-gamepad1.left_stick_y);
            telemetry.addData("Status: ", "Mere");
            telemetry.update();

        }
    }

