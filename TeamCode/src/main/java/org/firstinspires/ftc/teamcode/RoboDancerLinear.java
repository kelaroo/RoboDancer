package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.concurrent.atomic.DoubleAccumulator;

@TeleOp
public class RoboDancerLinear extends LinearOpMode {

    RoboDancerConfig hw;
    //SoundManager soundManager;
    //ElapsedTime macarenaCD = null;

    double coeff = 0.8;
    public static double dr1 = 0.75;
    public static double dr2 = 0.3;
    public static double dr3 = 0.69;

    public static double st1 = 0.05;
    public static double st2 = 1;
    public static double st3 = 0.68;

    public static double cap = 0.53;

    enum DancingState{
        macarena, waving, dans, none
    }
    DancingState dancingState = DancingState.none;

    @Override
    public void runOpMode() throws InterruptedException {

        /*SoundManager soundManager = new SoundManager(hardwareMap);

        if(!soundManager.addFile("macarena"))
            telemetry.addData("Sound Manager", "can't find macarena.wav");
        telemetry.update();

        soundManager.playSound("macarena");*/

        hw = new RoboDancerConfig(hardwareMap);

        hw.St1.setPosition(st1);
        hw.St2.setPosition(st2);
        hw.St3.setPosition(st3);

        hw.Dr1.setPosition(dr1);
        hw.Dr2.setPosition(dr2);
        hw.Dr3.setPosition(dr3);

        hw.cap.setPosition(cap);

        waitForStart();

        while(opModeIsActive()){
            double drive = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotate = gamepad1.right_stick_x;

            double RF = hw.clipPower(drive - strafe - rotate) * coeff;
            double RB = hw.clipPower(drive - strafe - rotate) * coeff;
            double LB = hw.clipPower(drive + strafe + rotate) * coeff;
            double LF = hw.clipPower(drive + strafe + rotate) * coeff;

            /*hw.leftFront.setPower(LF);
            hw.leftBack.setPower(LB);
            hw.rightFront.setPower(RF);
            hw.rightBack.setPower(RB);*/

            telemetry.addData("State", dancingState);
            telemetry.addLine();

            if(dancingState == DancingState.waving){
                hw.cap.setPosition(0.4);
                waveStanga(1);
                hw.cap.setPosition(0.7);
                waveDreapta(1);
                //dancingState = DancingState.waving;
            } else if(dancingState == DancingState.dans){
                //muie visoiu
                hw.cap.setPosition(cap);
                dans(1);
                //dancingState = DancingState.waving;
            } else if(dancingState == DancingState.macarena) {
                hw.cap.setPosition(cap);
                macarenaDance(1, 590);
                //dancingState = DancingState.macarena;
            }

            if (gamepad1.b){
                hw.Dr1.setPosition(dr1);
                hw.St1.setPosition(st1);
                hw.Dr2.setPosition(dr2);
                hw.St2.setPosition(st2);
                hw.Dr3.setPosition(dr3);
                hw.St3.setPosition(st3);
                hw.cap.setPosition(cap);
                dancingState = DancingState.none;
            }
            if(gamepad1.x){
                dancingState = DancingState.macarena;
            }
            if(gamepad1.right_bumper) {
                dancingState = DancingState.waving;
            }
            if(gamepad1.left_bumper) {
                dancingState = DancingState.dans;
            }

            telemetry.addData("dr1", hw.Dr1.getPosition());
            telemetry.addData("dr2", hw.Dr2.getPosition());
            telemetry.addData("dr3", hw.Dr3.getPosition());
            telemetry.addLine();
            telemetry.addData("st1", hw.St1.getPosition());
            telemetry.addData("st2", hw.St2.getPosition());
            telemetry.addData("st3", hw.St3.getPosition());

            telemetry.update();
        }
    }
    public void waveDreapta(int nrRep) {
        hw.Dr1.setPosition(0);
        int cont = 0;
        while(cont < nrRep){
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
        while(cont < nrRep){
            hw.St3.setPosition(0.53);
            waitTimer(400);
            hw.St3.setPosition(st3);
            waitTimer(400);
            cont++;
        }
        hw.St1.setPosition(st1);
    }


    public void dans(int nrRep){
        int cont = 0;
        while(cont < nrRep){
            hw.Dr1.setPosition(0);
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

    public void macarenaDance(int NrReps, int timer){
        int cont = 0;
        while(cont < NrReps) {
            //initial mainile sus
            hw.St1.setPosition(st1);
            hw.St2.setPosition(st2);
            hw.St3.setPosition(st3);

            hw.Dr1.setPosition(dr1);
            hw.Dr2.setPosition(dr2);
            hw.Dr3.setPosition(dr3);

            waitTimer(timer);
            //1
            hw.St1.setPosition(0.7);
            hw.St2.setPosition(0.7);

            waitTimer(timer);
            //2
            hw.Dr1.setPosition(0.1);
            hw.Dr2.setPosition(0.68);

            waitTimer(timer);
            //3
            hw.St3.setPosition(0.35);

            waitTimer(timer);
            //4
            hw.Dr3.setPosition(1);

            waitTimer(timer);
            //5
            hw.St2.setPosition(1);

            waitTimer(timer);
            //6
            hw.Dr2.setPosition(0.3);

            waitTimer(timer);
            //7
            hw.St1.setPosition(0);

            waitTimer(timer);
            //8
            hw.Dr1.setPosition(0.69);

            waitTimer(timer);
            //1
            hw.Dr1.setPosition(0.1);
            hw.St1.setPosition(0.7);

            waitTimer(timer);
            //2
            hw.St2.setPosition(0.7);
            hw.Dr2.setPosition(0.68);

            waitTimer(timer);
            //3
            hw.St2.setPosition(1);
            hw.Dr2.setPosition(0.3);

            waitTimer(timer);
            waitTimer(250);
            //4
            hw.Dr1.setPosition(dr1);
            hw.St1.setPosition(st1);
            hw.Dr2.setPosition(dr2);
            hw.St2.setPosition(st2);
            hw.Dr3.setPosition(dr3);
            hw.St3.setPosition(st3);

            /*if (cont % 2 == 0){
                turnLeft(1, 375);
                turnRight(2, 375);
                turnLeft(1, 375);
            } else {
                turnRight(1, 375);
                turnLeft(2, 375);
                turnRight(1, 375);
            }*/

            waitTimer(250);

            cont++;
        }
    }
    public void turnLeft(int NrReps, int Timer){
        for(int cont = 0; cont < NrReps; cont++){
            ElapsedTime timer = new ElapsedTime();
            while(timer.milliseconds() < Timer){
                /*hw.leftBack.setPower(0.5);
                hw.leftFront.setPower(-0.5);
                hw.rightBack.setPower(-0.5);
                hw.rightFront.setPower(0.5);*/
            }
        }
    }
    public void turnRight(int NrReps, int Timer){
        for(int cont = 0; cont < NrReps; cont++){
            ElapsedTime timer = new ElapsedTime();
            while(timer.milliseconds() < Timer){
                /*hw.leftBack.setPower(-0.5);
                hw.leftFront.setPower(0.5);
                hw.rightBack.setPower(0.5);
                hw.rightFront.setPower(-0.5);*/
            }
        }
    }

    public void waitTimer(int miliseconds){
        ElapsedTime timer;
        timer = new ElapsedTime();
        while(timer.milliseconds() < miliseconds){
            ;
        }
    }
}
