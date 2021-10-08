package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
@Config
public class Vals extends LinearOpMode {

    public static int TIME_BETWEEN = 50;

    RoboDancerConfig hw;
    public static double d=0,s=0,r=0;
    public static int t=0;

    public enum DanceState {
        ON, OFF
    }
    DanceState danceState = DanceState.OFF;

    @Override
    public void runOpMode() throws InterruptedException
    {
        hw=new RoboDancerConfig(hardwareMap);
        hw.Dr1.setPosition(0.86);
        hw.Dr2.setPosition(0.6);
        hw.Dr3.setPosition(0.95);
        hw.motorSetPosition(hw.Dr4, 0.25, hw.dr4Pos, 1);

        hw.St1.setPosition(0.72);
        hw.St2.setPosition(0.4);
        hw.St3.setPosition(0.6);
        hw.motorSetPosition(hw.St4, 0.75, hw.st4Pos, 1);
        waitForStart();
        while(opModeIsActive())
        {
             /*if(gamepad1.x)
             {
                 vals(-0.6,0.4,0.3,1500);
                 sleep(300);
                 vals(0,1,0,1000);
                 sleep(300);
                 vals(-0.6,0.4,0.3,1500);
                 sleep(300);;
                 vals(0,1,0,1000);
                 sleep(300);
            }
            vals(0,0,0,101);*/

            if(gamepad1.x)
                danceState = DanceState.ON;
            else if(gamepad1.b)
                danceState = DanceState.OFF;

            switch (danceState) {
                case ON:
                    vals(1, 0, 0, 1500);
                    vals(0, 0, 1, 1000);
                    sleep(TIME_BETWEEN);
                    vals(-1, 0, 0, 1500);
                    vals(0, 0, 1, 1000);
                    sleep(TIME_BETWEEN);
                    vals(1, 0, 0, 1500);
                    vals(0, 0, 1, 1000);
                    sleep(TIME_BETWEEN);
                    vals(-1, 0, 0, 1500);
                    vals(0, 0, 1, 1000);
                    sleep(TIME_BETWEEN);
                    break;
                case OFF:
                    continue;
            }

            /*if(gamepad1.b) {
                vals(1, 0, 0, 1500);
                vals(0, 0, 1, 1000);
            } else if(gamepad1.y) {
                vals(-1, 0, 0, 1500);
                vals(0, 0, 1, 1000);
            } else if(gamepad1.x) {
                vals(1, 0, 0, 1500);
                vals(0, 0, 1, 1000);
            } else if(gamepad1.a) {
                vals(-1, 0, 0, 1500);
                vals(0, 0, 1, 1000);
            }*/
        }
    }

    public void vals(double drive, double strafe, double rotate, int timp) {
        double RF = hw.clipPower(drive + strafe - rotate);
        double RB = hw.clipPower(drive - strafe - rotate)/2.0;
        double LB = hw.clipPower(drive + strafe + rotate);
        double LF = hw.clipPower(drive - strafe + rotate);

        hw.leftFront.setPower(LF);
        hw.leftBack.setPower(LB);
        hw.rightFront.setPower(RF);
        hw.rightBack.setPower(RB);

        sleep(timp);

        hw.leftFront.setPower(0);
        hw.leftBack.setPower(0);
        hw.rightFront.setPower(0);
        hw.rightBack.setPower(0);
    }
}