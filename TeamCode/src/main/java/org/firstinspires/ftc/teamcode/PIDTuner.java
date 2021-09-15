package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@Config
@TeleOp
public class PIDTuner extends LinearOpMode {

    RoboDancerConfig hw;

    public static PIDFCoefficients st4CoeffPos = new PIDFCoefficients(0, 0, 0, 0);
    public static PIDFCoefficients st4CoeffVelo = new PIDFCoefficients(0, 0, 0, 0);

    public static int currentPosition = 0;
    public static int targetPosition = 0;

    @Override
    public void runOpMode() throws InterruptedException {

        hw = new RoboDancerConfig(hardwareMap);
        telemetry = FtcDashboard.getInstance().getTelemetry();

        waitForStart();

        telemetry.addData("Tuning", "press x for st4 or y for dr4");
        telemetry.update();

        while(!gamepad1.x && !gamepad1.y);

        if(gamepad1.x) { ///////////////// st4
            // init coefficients
            hw.St4.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, st4CoeffPos);
            hw.St4.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, st4CoeffVelo);
            PIDFCoefficients prevCoeffPos = st4CoeffPos;
            PIDFCoefficients prevCoeffVelo = st4CoeffVelo;

            // main loop
            while(opModeIsActive()) {
                currentPosition = hw.St4.getCurrentPosition();

                // update coefficients
                if(prevCoeffPos.p != st4CoeffPos.p || prevCoeffPos.i != st4CoeffPos.i
                        || prevCoeffPos.d != st4CoeffPos.d || prevCoeffPos.f != st4CoeffPos.f)
                    hw.St4.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, st4CoeffPos);
                if(prevCoeffVelo.p != st4CoeffVelo.p || prevCoeffVelo.i != st4CoeffVelo.i
                        || prevCoeffVelo.d != st4CoeffVelo.d || prevCoeffVelo.f != st4CoeffVelo.f)
                    hw.St4.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, st4CoeffVelo);

                // run to position
                if(gamepad1.dpad_left) {
                    hw.motorSetPosition(hw.St4, 0.1, hw.st4Pos, RoboDancerDansuri.POWER4);

                    targetPosition = (int)(0.1 * hw.st4Pos);
                } else if(gamepad1.dpad_right) {
                    hw.motorSetPosition(hw.St4, 0.7, hw.st4Pos, RoboDancerDansuri.POWER4);

                    targetPosition = (int)(0.7 * hw.st4Pos);
                }

                // send values to dashboard
                telemetry.addData("targetPosition", targetPosition);
                telemetry.addData("currentPosition", currentPosition);
                telemetry.update();

                // prepare for next iteration
                prevCoeffPos = st4CoeffPos;
                prevCoeffVelo = st4CoeffVelo;
            }
        }
    }
}