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

    RoboDancerConfig hw;

    @Override
    public void init() {
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");

        hw = new RoboDancerConfig(hardwareMap);
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

        if(gamepad1.x) {
            hw.St4.setPower(-0.2);
        } else {
            hw.St4.setPower(0);
        }

        if(gamepad1.b) {
            hw.Dr4.setPower(-0.2);
        } else {
            hw.Dr4.setPower(0);
        }

        telemetry.addData("St4", hw.St4.getCurrentPosition());
        telemetry.addData("Dr4", hw.Dr4.getCurrentPosition());
    }
}
