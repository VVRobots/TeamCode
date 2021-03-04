package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="Auto test", group="Auto")
public class AutoTest extends LinearOpMode {
  private DcMotor FRmotor=null;
  private DcMotor FLmotor=null;
  private DcMotor BRmotor=null;
  private DcMotor BLmotor=null;

  @Override
  public void runOpMode()
  {
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



    telemetry.addData("Status:", "Gata");
    telemetry.update();
    waitForStart();
    while(opModeIsActive())
    {


      FRmotor.setPower(-0.5);
      FLmotor.setPower(0.5);
      BRmotor.setPower(0.5);
      BLmotor.setPower(-0.5);

      telemetry.addData("Status:","Merge");
      telemetry.update();

    }
  }
}