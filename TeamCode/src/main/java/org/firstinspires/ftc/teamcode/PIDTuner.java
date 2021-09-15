package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.MotorControlAlgorithm;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@Config
@TeleOp
public class PIDTuner extends LinearOpMode {

    RoboDancerConfig hw;

    public static PIDFCoefficients st4CoeffPos = new PIDFCoefficients(10, 0, 0, 0, MotorControlAlgorithm.LegacyPID);
    public static PIDFCoefficients st4CoeffVelo = new PIDFCoefficients(10, 0, 0, 0);

    public static double Pvelo = 0;
    public static double Ivelo = 0;
    public static double Dvelo = 0;
    public static double Fvelo = 0;

    public static double Ppos = 0;
    public static double Ipos = 0;
    public static double Dpos = 0;
    public static double Fpos = 0;

    public static int currentPosition = 0;
    public static int targetPosition = 0;

    @Override
    public void runOpMode() throws InterruptedException {

        hw = new RoboDancerConfig(hardwareMap);
        telemetry = FtcDashboard.getInstance().getTelemetry();

        st4CoeffPos = hw.St4.getPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION);
        st4CoeffVelo = hw.St4.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        telemetry.addData("PIDPos", hw.St4.getPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION));
        telemetry.addData("PIDVelo", hw.St4.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER));
        telemetry.addData("Tuning", "press x for st4 or y for dr4");
        telemetry.update();

        while(!gamepad1.x && !gamepad1.y);

        if(gamepad1.x) { ///////////////// st4
            // init coefficients
            /*hw.St4.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, st4CoeffPos);
            hw.St4.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, st4CoeffVelo);*/
            PIDFCoefficients prevCoeffPos = new PIDFCoefficients(st4CoeffPos);
            PIDFCoefficients prevCoeffVelo = new PIDFCoefficients(st4CoeffVelo);

            Pvelo = st4CoeffVelo.p;
            Ivelo = st4CoeffVelo.i;
            Dvelo = st4CoeffVelo.d;
            Fvelo = st4CoeffVelo.f;

            Ppos = st4CoeffPos.p;
            Ipos = st4CoeffPos.i;
            Dpos = st4CoeffPos.d;
            Fpos = st4CoeffPos.f;

            int cox = 0;
            // main loop
            while(opModeIsActive()) {
                currentPosition = hw.St4.getCurrentPosition();

                // update coefficients
                if(Ppos != st4CoeffPos.p || Ipos != st4CoeffPos.i
                        || Dpos != st4CoeffPos.d || Fpos != st4CoeffPos.f) {
                    st4CoeffPos = new PIDFCoefficients(Ppos, Ipos, Dpos, Fpos, MotorControlAlgorithm.LegacyPID);
                    hw.St4.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, st4CoeffPos);
                    cox++;
                }
                if(Pvelo != st4CoeffVelo.p || Ivelo != st4CoeffVelo.i
                        || Dvelo != st4CoeffVelo.d || Fvelo != st4CoeffVelo.f) {
                    st4CoeffVelo = new PIDFCoefficients(Pvelo, Ivelo, Dvelo, Fvelo);
                    hw.St4.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, st4CoeffVelo);
                    cox++;
                }

                // run to position
                if(gamepad1.dpad_left) {
                    int position = (int)(0.1 * hw.st4Pos);
                    hw.St4.setPower(0);
                    hw.St4.setTargetPosition(position);

                    hw.St4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    hw.St4.setPower(0.6);

                    targetPosition = position;

                    while(hw.St4.isBusy() && !gamepad1.x && opModeIsActive()) {
                        telemetry.addData("currentPosition", hw.St4.getCurrentPosition());
                        telemetry.addData("targetPosition", targetPosition);
                        telemetry.addData("PidPos", st4CoeffPos);
                        telemetry.addData("PidVelo", st4CoeffVelo);
                        telemetry.addData("prevPos", prevCoeffPos);
                        telemetry.addData("prevVelo", prevCoeffVelo);
                        telemetry.addData("cox", cox);
                        telemetry.update();
                    }
                } else if(gamepad1.dpad_right) {
                    int position = (int)(0.7 * hw.st4Pos);
                    hw.St4.setPower(0);
                    hw.St4.setTargetPosition(position);

                    hw.St4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    hw.St4.setPower(0.6);

                    targetPosition = position;

                    while(hw.St4.isBusy() && !gamepad1.x && opModeIsActive()) {
                        telemetry.addData("currentPosition", hw.St4.getCurrentPosition());
                        telemetry.addData("targetPosition", targetPosition);
                        telemetry.addData("PidPos", st4CoeffPos);
                        telemetry.addData("PidVelo", st4CoeffVelo);
                        telemetry.addData("prevPos", prevCoeffPos);
                        telemetry.addData("prevVelo", prevCoeffVelo);
                        telemetry.addData("cox", cox);
                        telemetry.update();
                    }
                }

                // send values to dashboard
                telemetry.addData("targetPosition", targetPosition);
                telemetry.addData("currentPosition", currentPosition);
                telemetry.addData("PidPos", st4CoeffPos);
                telemetry.addData("PidVelo", st4CoeffVelo);
                telemetry.addData("prevPos", prevCoeffPos);
                telemetry.addData("prevVelo", prevCoeffVelo);

                telemetry.addData("cox", cox);
                telemetry.update();

                // prepare for next iteration
                prevCoeffPos = new PIDFCoefficients(st4CoeffPos);
                prevCoeffVelo = new PIDFCoefficients(st4CoeffVelo);
            }
        }
    }
}
