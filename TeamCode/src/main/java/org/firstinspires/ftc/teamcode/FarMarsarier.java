package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

@TeleOp(name="FarMarsarier", group="Linear Opmode")
public class FarMarsarier extends LinearOpMode {

    @Override
    public void runOpMode()
    {
         ColorSensor Far = hardwareMap.get(ColorSensor.class, "senzorCuloare");
         waitForStart();
         while(opModeIsActive())
            Far.enableLed(gamepad1.x);

    }
}
