package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class ConfigTest extends OpMode {

    public DcMotor rightFront;
    public DcMotor leftFront;
    public DcMotor rightBack;
    public DcMotor leftBack;

    @Override
    public void init() {
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
    }

    @Override
    public void loop() {
        if(gamepad1.dpad_right)
            rightFront.setPower(0.5);
        else
            rightFront.setPower(0);

        if(gamepad1.dpad_up)
            rightBack.setPower(0.5);
        else
            rightBack.setPower(0);

        if(gamepad1.dpad_left)
            leftBack.setPower(0.5);
        else
            leftBack.setPower(0);

        if(gamepad1.dpad_down)
            leftFront.setPower(0.5);
        else
            leftFront.setPower(0);
    }
}
