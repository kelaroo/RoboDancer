package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp
@Config
public class RoboDancer extends LinearOpMode {

    RoboDancerConfig hw;
    SoundManager soundManager;
    ElapsedTime macarenaCD = null;

    double coeff = 0.8;

    public static double dr1 = 0.84;
    public static double dr2 = 0.85;
    public static double dr3 = 1;
    public static double dr4 = 0.75;

    public static double st1 = 0.52;
    public static double st2 = 0.1;
    public static double st3 = 0;
    public static double st4 = 0.23;

    public static double cap = 0.585;

    int dans = 0;

    enum dansuri {}

    Thread spin = null;

    @Override
    public void runOpMode() throws InterruptedException {

        hw = new RoboDancerConfig(hardwareMap);
        hw.St1.setPosition(st1);
        hw.St2.setPosition(st2);
        hw.St3.setPosition(st3);
        hw.St4.setPosition(st4);


        hw.Dr1.setPosition(dr1);
        hw.Dr2.setPosition(dr2);
        hw.Dr3.setPosition(dr3);
        hw.Dr4.setPosition(dr4);

        hw.cap.setPosition(cap);

        waitForStart();

        while (opModeIsActive()) {
            double drive = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotate = gamepad1.right_stick_x;

            double RF = hw.clipPower(drive - strafe - rotate) * coeff;
            double RB = hw.clipPower(drive - strafe - rotate) * coeff;
            double LB = hw.clipPower(drive + strafe + rotate) * coeff;
            double LF = hw.clipPower(drive + strafe + rotate) * coeff;

            hw.leftFront.setPower(LF);
            hw.leftBack.setPower(LB);
            hw.rightFront.setPower(RF);
            hw.rightBack.setPower(RB);
            if (gamepad1.right_bumper) {
                dans = 3;
            } else if (gamepad1.left_bumper) {
                //muie visoiu
                dans = 2;
            } else if (gamepad1.x) {
                dans = 1;
            } else if(gamepad1.y){
                dans = 0;
            }
            if (dans == 0) {
                hw.St1.setPosition(st1);
                hw.St2.setPosition(st2);
                hw.St3.setPosition(st3);
                hw.St4.setPosition(st4);


                hw.Dr1.setPosition(dr1);
                hw.Dr2.setPosition(dr2);
                hw.Dr3.setPosition(dr3);
                hw.Dr4.setPosition(dr4);

                hw.cap.setPosition(cap);
            }
            if (dans == 1) {
                macarenaDance();
            }
            telemetry.addData("dr1", hw.Dr1.getPosition());
            telemetry.addData("dr2", hw.Dr2.getPosition());
            telemetry.addData("dr3", hw.Dr3.getPosition());
            telemetry.addData("dr4", hw.Dr4.getPosition());
            telemetry.addLine();
            telemetry.addData("st1", hw.St1.getPosition());
            telemetry.addData("st2", hw.St2.getPosition());
            telemetry.addData("st3", hw.St3.getPosition());
            telemetry.addData("st4", hw.St4.getPosition());
        /*if(gamepad1.left_stick_button){
            if(macarenaCD == null || macarenaCD.milliseconds() >= 180000){
                soundManager.playSound("macarena");
                macarenaCD = new ElapsedTime();
            }
        }*/
            // Wobble claw
       /* if(gamepad2.dpad_right) {
            hw.clawWobble.setPosition(CLAW_PRINS);
        } else if(gamepad2.dpad_left && !gamepad2.dpad_up) {
            hw.clawWobble.setPosition(CLAW_LASAT);
            if(oosCD == null || oosCD.milliseconds() >= 700) {
                soundManager.playSound("oos");
                oosCD = new ElapsedTime();
            }
        }*/
        }
    }

    public void waveDreapta ( int nrRep){
        hw.Dr1.setPosition(0);
        int cont = 0;
        while (cont < nrRep) {
            hw.Dr3.setPosition(0.9);
            waitTimer(400);
            hw.Dr3.setPosition(dr3);
            waitTimer(400);
            cont++;
        }
        hw.Dr1.setPosition(dr1);
    }

    public void waveStanga(int nrRep) {
        hw.St1.setPosition(0.8);
        int cont = 0;
        while (cont < nrRep) {
            hw.St3.setPosition(0.53);
            waitTimer(400);
            hw.St3.setPosition(st3);
            waitTimer(400);
            cont++;
        }
        hw.St1.setPosition(st1);
    }


    public void dans ( int nrRep){
        int cont = 0;
        while (cont < nrRep) {
            hw.Dr1.setPosition
                    (0);
            hw.St1.setPosition(0.8);
            waitTimer(400);
            hw.Dr1.setPosition(dr1);
            hw.St1.setPosition(st1);
            waitTimer(400);
            hw.Dr3.setPosition(0.9);
            hw.St3.setPosition(0.53);
            waitTimer(400);
            hw.Dr1.setPosition(0);
            hw.St1.setPosition(0.8);
            waitTimer(400);
            hw.Dr3.setPosition(dr3);
            hw.St3.setPosition(st3);
            cont++;
        }
    }

    public void macarenaDance () {
        hw.Dr1.setPosition(0.84);
        hw.Dr2.setPosition(0.84);
        hw.Dr3.setPosition(0.75);
        hw.Dr4.setPosition(0.41);

        waitTimer(500);

        hw.St1.setPosition(0.53);
        hw.St2.setPosition(0.1);
        hw.St3.setPosition(0.2);
        hw.St4.setPosition(0.58);


        waitTimer(500);

        hw.Dr2.setPosition(0.17);

        waitTimer(400);

        hw.St2.setPosition(0.78);

        waitTimer(400);

        hw.Dr1.setPosition(0.42);
        hw.Dr2.setPosition(0.61);
        hw.Dr3.setPosition(0.1);
        hw.Dr4.setPosition(0.12);

        waitTimer(500);

        hw.St1.setPosition(0.86);
        hw.St2.setPosition(0.3);
        hw.St3.setPosition(0.5);
        hw.St4.setPosition(0.75);

        waitTimer(600);

        hw.Dr1.setPosition(0.7);
        waitTimer(200);
        hw.Dr2.setPosition(0.5);
        hw.Dr3.setPosition(1);
        hw.Dr4.setPosition(0.74);
        waitTimer(200);
        hw.Dr1.setPosition(0.3);

        waitTimer(500);

        hw.St1.setPosition(1);
        hw.St2.setPosition(0.43);
        hw.St3.setPosition(0);
        hw.St4.setPosition(0.23);

        waitTimer(500);
        hw.Dr1.setPosition(0.84);
        hw.Dr2.setPosition(0.55);
        hw.Dr3.setPosition(0);
        hw.Dr4.setPosition(0.1);

        waitTimer(500);

        hw.St2.setPosition(0.35);
        hw.St3.setPosition(0.7);
        hw.St4.setPosition(0.76);
        hw.St1.setPosition(0.6);

        waitTimer(500);

        hw.Dr4.setPosition(0.75);
        waitTimer(200);
        hw.Dr1.setPosition(0.4);
        waitTimer(200);
        hw.Dr2.setPosition(1);
        hw.Dr3.setPosition(0.4);

        waitTimer(200);

        hw.St4.setPosition(0.23);

        waitTimer(200);
        hw.St1.setPosition(0.93);
        hw.St2.setPosition(0);
        hw.St3.setPosition(0.7);
        waitTimer(600);

        for(int i=1;i<=5;i++)
        {
            hw.rightFront.setPower(0.2);
            hw.leftFront.setPower(-0.2);
            hw.rightBack.setPower(-0.2);
            hw.leftBack.setPower(0.2);
            waitTimer(200);
            hw.leftFront.setPower(0.2);
            hw.rightFront.setPower(-0.2);
            hw.rightBack.setPower(0.2);
            hw.leftBack.setPower(-0.2);
            waitTimer(200);
        }
        hw.leftFront.setPower(0);
        hw.rightFront.setPower(0);
        hw.rightBack.setPower(0);
        hw.leftBack.setPower(0);/*
        hw.rightFront.setPower(0.5);
        hw.leftFront.setPower(-0.5);
        hw.rightBack.setPower(0.5);
        hw.leftBack.setPower(-0.5);
        waitTimer(2000);
        hw.rightFront.setPower(0);
        hw.leftFront.setPower(0);
        hw.rightBack.setPower(0);
        hw.leftBack.setPower(0);*/
    }

    public void waitTimer ( int miliseconds){
        ElapsedTime timer;
        timer = new ElapsedTime();
        while (timer.milliseconds() < miliseconds) {
            ;
        }
    }
}