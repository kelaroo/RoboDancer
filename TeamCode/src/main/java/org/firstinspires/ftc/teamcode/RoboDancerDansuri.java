package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp
@Config
public class RoboDancerDansuri extends LinearOpMode {

    RoboDancerConfig hw;
    SoundManager soundManager;
    ElapsedTime macarenaCD = null;

    double coeff = 0.8;
    
    public static double POWER4 = 1;

    // pozitii initiale
    public static double dr1 = 0.86;
    public static double dr2 = 0.85;
    public static double dr3 = 1;
    public static double dr4 = 0.15;

    public static double st1 = 0.52;
    public static double st2 = 0.1;
    public static double st3 = 0;
    public static double st4 = 0.13;

    public static double cap = 0.585;

    int dans = 0;

    enum dansuri {}
    int ok;
    Thread spin = null;

    @Override
    public void runOpMode() throws InterruptedException {

        soundManager = new SoundManager(hardwareMap);

        if(soundManager.addFile("macarena") == false) {
            telemetry.addData("macarena", "not found");
        }
        telemetry.update();


        hw = new RoboDancerConfig(hardwareMap);

        telemetry.addData("Warning", "If you did not set the motors to the 0 position, restart the TeleOp!");
        telemetry.update();

        hw.St1.setPosition(st1);
        hw.St2.setPosition(st2);
        hw.St3.setPosition(st3);
        hw.motorSetPosition(hw.St4, st4, hw.st4Pos, POWER4);

        hw.Dr1.setPosition(dr1);
        hw.Dr2.setPosition(dr2);
        hw.Dr3.setPosition(dr3);
        hw.motorSetPosition(hw.Dr4, dr4, hw.dr4Pos, POWER4);

        hw.cap.setPosition(cap);

        waitForStart();

        while (opModeIsActive()) {


            if (gamepad1.dpad_up) {
                dans = 3;
                ok = 0;
            } else if (gamepad1.left_bumper) {
                dans = 2;
            } else if (gamepad1.x) {
                dans = 1; // macarena
            } else if (gamepad1.y) {
                dans = 0;
            } else if (gamepad1.b) {
                dans = 4; // kethup
            } else if (gamepad1.dpad_right) {
                dans = 5; // dab dreapta
            } else if (gamepad1.dpad_left) {
                dans = 6;
            } else if (gamepad1.dpad_down) {
                dans = 7;
            }

            if (dans == 0) {
                hw.St1.setPosition(st1);
                hw.St2.setPosition(st2);
                hw.St3.setPosition(st3);
                hw.motorSetPosition(hw.St4, st4, hw.st4Pos, POWER4);

                hw.Dr1.setPosition(dr1);
                hw.Dr2.setPosition(dr2);
                hw.Dr3.setPosition(dr3);
                hw.motorSetPosition(hw.Dr4, dr4, hw.dr4Pos, POWER4);

                hw.cap.setPosition(cap);
            }
            if (dans == 1) {
                macarenaDance();
            }
            if (dans == 2) {
                vals();
            }
            if (dans == 4) {
                KetchupDance();
            }
            if (dans == 5) {
                dabDreapta();
            }
            if (dans == 3) {
                salut();
                ok=1;
            }
            if (dans == 6) {
                dabStanga();
            }
            if (dans == 7) {
                sheesh();
            }

            telemetry.addData("dr1", hw.Dr1.getPosition());
            telemetry.addData("dr2", hw.Dr2.getPosition());
            telemetry.addData("dr3", hw.Dr3.getPosition());
            telemetry.addData("dr4", hw.Dr4.getCurrentPosition());
            telemetry.addLine();
            telemetry.addData("st1", hw.St1.getPosition());
            telemetry.addData("st2", hw.St2.getPosition());
            telemetry.addData("st3", hw.St3.getPosition());
            telemetry.addData("st4", hw.St4.getCurrentPosition());
            telemetry.update();
            //telemetry.addData("st1", String.format("%f, %f", hw.St1.getPosition()));

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
        soundManager.playSound("macarena");

        hw.Dr1.setPosition(0.84);
        hw.Dr2.setPosition(0.84);
        hw.Dr3.setPosition(0.75);
        hw.motorSetPosition(hw.Dr4, 0.41, hw.dr4Pos, POWER4);

        waitTimer(500);

        hw.St1.setPosition(0.53);
        hw.St2.setPosition(0.1);
        hw.St3.setPosition(0.2);
        hw.motorSetPosition(hw.St4, 0.58, hw.st4Pos, POWER4);

        waitTimer(500);

        hw.Dr2.setPosition(0.17);

        waitTimer(400);

        hw.St2.setPosition(0.78);

        waitTimer(400);

        hw.Dr1.setPosition(0.42);
        hw.Dr2.setPosition(0.61);
        hw.Dr3.setPosition(0.1);
        hw.motorSetPosition(hw.Dr4, 0.12, hw.dr4Pos, POWER4);

        waitTimer(500);

        hw.St1.setPosition(0.86);
        hw.St2.setPosition(0.3);
        hw.St3.setPosition(0.5);
        hw.motorSetPosition(hw.St4, 0.75, hw.st4Pos, POWER4);

        waitTimer(600);

        hw.Dr1.setPosition(0.7);

        waitTimer(200);

        hw.Dr2.setPosition(0.5);
        hw.Dr3.setPosition(1);
        hw.motorSetPosition(hw.Dr4, 0.74, hw.dr4Pos, POWER4);

        waitTimer(200);

        hw.Dr1.setPosition(0.3);

        waitTimer(500);

        hw.St1.setPosition(1);
        hw.St2.setPosition(0.43);
        hw.St3.setPosition(0);
        hw.motorSetPosition(hw.St4, 0.23, hw.st4Pos, POWER4);

        waitTimer(500);

        hw.Dr1.setPosition(0.84);
        hw.Dr2.setPosition(0.55);
        hw.Dr3.setPosition(0);
        hw.motorSetPosition(hw.Dr4, 0.1, hw.dr4Pos, POWER4);

        waitTimer(500);

        hw.St2.setPosition(0.35);
        hw.St3.setPosition(0.7);
        hw.motorSetPosition(hw.St4, 0.76, hw.st4Pos, POWER4);
        hw.St1.setPosition(0.6);

        waitTimer(500);

        hw.motorSetPosition(hw.Dr4, 0.75, hw.dr4Pos, POWER4);

        waitTimer(200);

        hw.Dr1.setPosition(0.4);

        waitTimer(200);

        hw.Dr2.setPosition(1);
        hw.Dr3.setPosition(0.4);

        waitTimer(200);

        hw.motorSetPosition(hw.St4, 0.23, hw.st4Pos, POWER4);

        waitTimer(200);

        hw.St1.setPosition(0.93);
        hw.St2.setPosition(0);
        hw.St3.setPosition(0.7);

        waitTimer(600);

        for (int i = 1; i <= 5; i++)
        {
            /*hw.rightFront.setPower(0.2);
            hw.leftFront.setPower(-0.2);
            hw.rightBack.setPower(-0.2);
            hw.leftBack.setPower(0.2);*/
            waitTimer(200);
            /*hw.leftFront.setPower(0.2);
            hw.rightFront.setPower(-0.2);
            hw.rightBack.setPower(0.2);
            hw.leftBack.setPower(-0.2);*/
            waitTimer(200);
        }

        /*hw.leftFront.setPower(0);
        hw.rightFront.setPower(0);
        hw.rightBack.setPower(0);
        hw.leftBack.setPower(0);*/

        /* hw.rightFront.setPower(0.5);
        hw.leftFront.setPower(-0.5);
        hw.rightBack.setPower(0.5);
        hw.leftBack.setPower(-0.5);*/
        waitTimer(300);
        /*hw.rightFront.setPower(0);
        hw.leftFront.setPower(0);
        hw.rightBack.setPower(0);
        hw.leftBack.setPower(0);*/
    }

    public void KetchupDance ()
    {
        // ...........1...........
        // ambele maini drept in fata
        hw.Dr1.setPosition(.84);
        hw.Dr2.setPosition(.85);
        hw.Dr3.setPosition(.8);
        hw.motorSetPosition(hw.Dr4, .35, hw.dr4Pos, POWER4);

        hw.St1.setPosition(.52);
        hw.St2.setPosition(.1);
        hw.St3.setPosition(.4);
        hw.motorSetPosition(hw.St4, .65, hw.st4Pos, POWER4);

        waitTimer(450);

        // ambele maini incrucisate, dreapta sus
        hw.Dr1.setPosition(.6);
        hw.Dr2.setPosition(.85);
        hw.Dr3.setPosition(.8);
        hw.motorSetPosition(hw.Dr4, .33, hw.dr4Pos, POWER4);

        hw.St1.setPosition(.8);
        hw.St2.setPosition(.1);
        hw.St3.setPosition(.4);
        hw.motorSetPosition(hw.St4, .67, hw.st4Pos, POWER4);

        waitTimer(170);

        // ambele maini drept in fata
        hw.Dr1.setPosition(.84);
        hw.Dr2.setPosition(.85);
        hw.Dr3.setPosition(.8);
        hw.motorSetPosition(hw.Dr4, .35, hw.dr4Pos, POWER4);

        hw.St1.setPosition(.52);
        hw.St2.setPosition(.1);
        hw.St3.setPosition(.4);
        hw.motorSetPosition(hw.St4, .65, hw.st4Pos, POWER4);

        waitTimer(170);

        // ambele maini incrucisate, dreapta sus
        hw.Dr1.setPosition(.6);
        hw.Dr2.setPosition(.85);
        hw.Dr3.setPosition(.8);
        hw.motorSetPosition(hw.Dr4, .33, hw.dr4Pos, POWER4);

        hw.St1.setPosition(.8);
        hw.St2.setPosition(.1);
        hw.St3.setPosition(.4);
        hw.motorSetPosition(hw.St4, .67, hw.st4Pos, POWER4);

        waitTimer(170);

        // ambele maini in fata
        hw.Dr1.setPosition(.84);
        hw.Dr2.setPosition(.85);
        hw.Dr3.setPosition(.8);
        hw.motorSetPosition(hw.Dr4, .35, hw.dr4Pos, POWER4);

        hw.St1.setPosition(.52);
        hw.St2.setPosition(.1);
        hw.St3.setPosition(.4);
        hw.motorSetPosition(hw.St4, .65, hw.st4Pos, POWER4);

        waitTimer(170);

        // ambele maini incrucisate, stanga sus
        hw.Dr1.setPosition(.55);
        hw.Dr2.setPosition(.85);
        hw.Dr3.setPosition(.65);
        hw.motorSetPosition(hw.Dr4, .37, hw.dr4Pos, POWER4);

        hw.St1.setPosition(.8);
        hw.St2.setPosition(.1);
        hw.St3.setPosition(.2);
        hw.motorSetPosition(hw.St4, .67, hw.st4Pos, POWER4);

        waitTimer(170);

        // ambele maini drept fata
        hw.Dr1.setPosition(.84);
        hw.Dr2.setPosition(.85);
        hw.Dr3.setPosition(.65);
        hw.motorSetPosition(hw.Dr4, .35, hw.dr4Pos, POWER4);

        hw.St1.setPosition(.52);
        hw.St2.setPosition(.1);
        hw.St3.setPosition(.2);
        hw.motorSetPosition(hw.St4, .65, hw.st4Pos, POWER4);

        waitTimer(170);

        // ambele maini incrucisate, stanga sus
        hw.Dr1.setPosition(.55);
        hw.Dr2.setPosition(.85);
        hw.Dr3.setPosition(.65);
        hw.motorSetPosition(hw.Dr4, .37, hw.dr4Pos, POWER4);

        hw.St1.setPosition(.8);
        hw.St2.setPosition(.1);
        hw.St3.setPosition(.2);
        hw.motorSetPosition(hw.St4, .67, hw.st4Pos, POWER4);

        waitTimer(170);

        // ambele maini drept fata
        hw.Dr1.setPosition(.84);
        hw.Dr2.setPosition(.85);
        hw.Dr3.setPosition(.65);
        hw.motorSetPosition(hw.Dr4, .35, hw.dr4Pos, POWER4);

        hw.St1.setPosition(.52);
        hw.St2.setPosition(.1);
        hw.St3.setPosition(.2);
        hw.motorSetPosition(hw.St4, .65, hw.st4Pos, POWER4);

        waitTimer(170);

        //..........2.........
        // ambele maini drept in fata
        hw.Dr1.setPosition(.84);
        hw.Dr2.setPosition(.85);
        hw.Dr3.setPosition(.8);
        hw.motorSetPosition(hw.Dr4, .35, hw.dr4Pos, POWER4);

        hw.St1.setPosition(.52);
        hw.St2.setPosition(.1);
        hw.St3.setPosition(.4);
        hw.motorSetPosition(hw.St4, .65, hw.st4Pos, POWER4);

        waitTimer(170);

        // ambele maini incrucisate, dreapta sus
        hw.Dr1.setPosition(.6);
        hw.Dr2.setPosition(.85);
        hw.Dr3.setPosition(.8);
        hw.motorSetPosition(hw.Dr4, .33, hw.dr4Pos, POWER4);

        hw.St1.setPosition(.8);
        hw.St2.setPosition(.1);
        hw.St3.setPosition(.4);
        hw.motorSetPosition(hw.St4, .67, hw.st4Pos, POWER4);

        waitTimer(170);

        // ambele maini drept in fata
        hw.Dr1.setPosition(.84);
        hw.Dr2.setPosition(.85);
        hw.Dr3.setPosition(.8);
        hw.motorSetPosition(hw.Dr4, .35, hw.dr4Pos, POWER4);

        hw.St1.setPosition(.52);
        hw.St2.setPosition(.1);
        hw.St3.setPosition(.4);
        hw.motorSetPosition(hw.St4, .65, hw.st4Pos, POWER4);

        waitTimer(170);

       /* // ambele maini incrucisate, dreapta sus
        hw.Dr1.setPosition(.6);
        hw.Dr2.setPosition(.85);
        hw.Dr3.setPosition(.8);
        hw.motorSetPosition(hw.Dr4, .33, hw.dr4Pos, POWER4);

        hw.St1.setPosition(.8);
        hw.St2.setPosition(.1);
        hw.St3.setPosition(.4);
        hw.motorSetPosition(hw.St4, .67, hw.st4Pos, POWER4);

        waitTimer(180);

        // ambele maini in fata
        hw.Dr1.setPosition(.84);
        hw.Dr2.setPosition(.85);
        hw.Dr3.setPosition(.8);
        hw.motorSetPosition(hw.Dr4, .35, hw.dr4Pos, POWER4);

        hw.St1.setPosition(.52);
        hw.St2.setPosition(.1);
        hw.St3.setPosition(.4);
        hw.motorSetPosition(hw.St4, .65, hw.st4Pos, POWER4);

        waitTimer(180);

        // ambele maini incrucisate, stanga sus
        hw.Dr1.setPosition(.55);
        hw.Dr2.setPosition(.85);
        hw.Dr3.setPosition(.65);
        hw.motorSetPosition(hw.Dr4, .37, hw.dr4Pos, POWER4);

        hw.St1.setPosition(.8);
        hw.St2.setPosition(.1);
        hw.St3.setPosition(.2);
        hw.motorSetPosition(hw.St4, .67, hw.st4Pos, POWER4);

        waitTimer(180);

        // ambele maini drept fata
        hw.Dr1.setPosition(.84);
        hw.Dr2.setPosition(.85);
        hw.Dr3.setPosition(.65);
        hw.motorSetPosition(hw.Dr4, .35, hw.dr4Pos, POWER4);

        hw.St1.setPosition(.52);
        hw.St2.setPosition(.1);
        hw.St3.setPosition(.2);
        hw.motorSetPosition(hw.St4, .65, hw.st4Pos, POWER4);

        waitTimer(180);*/

        // ambele maini incrucisate, stanga sus
        hw.Dr1.setPosition(.55);
        hw.Dr2.setPosition(.85);
        hw.Dr3.setPosition(.65);
        hw.motorSetPosition(hw.Dr4, .37, hw.dr4Pos, POWER4);

        hw.St1.setPosition(.8);
        hw.St2.setPosition(.1);
        hw.St3.setPosition(.2);
        hw.motorSetPosition(hw.St4, .67, hw.st4Pos, POWER4);

        waitTimer(170);

        // ambele maini drept fata
        hw.Dr1.setPosition(.84);
        hw.Dr2.setPosition(.85);
        hw.Dr3.setPosition(.65);
        hw.motorSetPosition(hw.Dr4, .35, hw.dr4Pos, POWER4);

        hw.St1.setPosition(.52);
        hw.St2.setPosition(.1);
        hw.St3.setPosition(.2);
        hw.motorSetPosition(hw.St4, .65, hw.st4Pos, POWER4);

        waitTimer(170);

        // salut dreapta
        hw.St1.setPosition(0.9);
        hw.St2.setPosition(.1);
        hw.St3.setPosition(0.8);
        hw.motorSetPosition(hw.St4, 0.23, hw.st4Pos, POWER4);
        hw.Dr1.setPosition(.51);
        hw.Dr2.setPosition(.5);
        hw.Dr3.setPosition(.9);
        hw.motorSetPosition(hw.Dr4, .75, hw.dr4Pos, POWER4);

        waitTimer(140);

        hw.Dr1.setPosition(.65);
        waitTimer(140);
        hw.Dr1.setPosition(.45);
        waitTimer(140);
        hw.Dr1.setPosition(.65);
        waitTimer(140);
        hw.Dr1.setPosition(.45);
        waitTimer(140);
        hw.Dr1.setPosition(.65);
        waitTimer(140);
        hw.Dr1.setPosition(.45);
        waitTimer(140);

        // salut stanga
        hw.Dr1.setPosition(0.45);
        hw.Dr2.setPosition(.85);
        hw.Dr3.setPosition(0.2);
        hw.motorSetPosition(hw.Dr4, 0.75, hw.dr4Pos, POWER4);

        hw.St1.setPosition(.85);
        hw.St2.setPosition(.45);
        hw.St3.setPosition(.15);
        hw.motorSetPosition(hw.St4, .23, hw.st4Pos, POWER4);

        waitTimer(140);

        hw.St1.setPosition(.92);
        waitTimer(140);
        hw.St1.setPosition(.75);
        waitTimer(140);
        hw.St1.setPosition(.92);
        waitTimer(140);
        hw.St1.setPosition(.75);
        waitTimer(140);
        hw.St1.setPosition(.92);
        waitTimer(140);
        hw.St1.setPosition(.75);
        waitTimer(140);

        /*// salut dreapta
        hw.St1.setPosition(0.9);
        hw.St2.setPosition(.1);
        hw.St3.setPosition(0.8);
        hw.motorSetPosition(hw.St4, 0.23, hw.st4Pos, POWER4);
        hw.Dr1.setPosition(.51);
        hw.Dr2.setPosition(.5);
        hw.Dr3.setPosition(.9);
        hw.motorSetPosition(hw.Dr4, .75, hw.dr4Pos, POWER4);

        waitTimer(150);

        hw.Dr1.setPosition(.65);
        waitTimer(150);
        hw.Dr1.setPosition(.45);
        waitTimer(150);
        hw.Dr1.setPosition(.65);
        waitTimer(200);
        hw.Dr1.setPosition(.45);
        waitTimer(200);
        hw.Dr1.setPosition(.65);
        waitTimer(200);
        hw.Dr1.setPosition(.45);
        waitTimer(200);

        // salut stanga
        hw.Dr1.setPosition(0.45);
        hw.Dr2.setPosition(.85);
        hw.Dr3.setPosition(0.2);
        hw.motorSetPosition(hw.Dr4, 0.75, hw.dr4Pos, POWER4);

        hw.St1.setPosition(.85);
        hw.St2.setPosition(.45);
        hw.St3.setPosition(.15);
        hw.motorSetPosition(hw.St4, .23, hw.st4Pos, POWER4);

        waitTimer(200);

        hw.St1.setPosition(.92);
        waitTimer(200);
        hw.St1.setPosition(.75);
        waitTimer(200);
        hw.St1.setPosition(.92);
        waitTimer(200);
        hw.St1.setPosition(.75);
        waitTimer(200);
        hw.St1.setPosition(.92);
        waitTimer(200);
        hw.St1.setPosition(.75);

        waitTimer(200);*/

        // urcare
        hw.St1.setPosition(0.9);
        hw.St2.setPosition(.1);
        hw.St3.setPosition(0.8);
        hw.motorSetPosition(hw.St4, 0.23, hw.st4Pos, POWER4);
        waitTimer(190);

        hw.Dr1.setPosition(0.45);
        hw.Dr2.setPosition(.45);
        hw.Dr3.setPosition(0.4);
        hw.motorSetPosition(hw.Dr4, 0.75, hw.dr4Pos, POWER4);
        waitTimer(190);

        hw.St1.setPosition(0.9);
        hw.St2.setPosition(.45);
        hw.St3.setPosition(0.65);
        hw.motorSetPosition(hw.St4, 0.23, hw.st4Pos, POWER4);
        waitTimer(190);

        hw.Dr1.setPosition(0.6);
        hw.Dr2.setPosition(.45);
        hw.Dr3.setPosition(0.75);
        hw.motorSetPosition(hw.Dr4, 0.75, hw.dr4Pos, POWER4);
        waitTimer(190);

        hw.St1.setPosition(0.79);
        hw.St2.setPosition(.45);
        hw.St3.setPosition(0.3);
        hw.motorSetPosition(hw.St4, 0.23, hw.st4Pos, POWER4);
        waitTimer(190);

        hw.Dr1.setPosition(0.68);
        hw.Dr2.setPosition(.45);
        hw.Dr3.setPosition(0.95);
        hw.motorSetPosition(hw.Dr4, 0.75, hw.dr4Pos, POWER4);
        waitTimer(190);

        hw.St1.setPosition(0.65);
        hw.St2.setPosition(.45);
        hw.St3.setPosition(0);
        hw.motorSetPosition(hw.St4, 0.23, hw.st4Pos, POWER4);
        waitTimer(190);

        // maini la cap
        hw.Dr1.setPosition(0.4);
        hw.Dr2.setPosition(.58);
        hw.Dr3.setPosition(0.6);
        hw.motorSetPosition(hw.Dr4, 0.12, hw.dr4Pos, POWER4);
        waitTimer(190);

        hw.St1.setPosition(1);
        hw.St2.setPosition(.45);
        hw.St3.setPosition(0);
        hw.motorSetPosition(hw.St4, 0.23, hw.st4Pos, POWER4);
        waitTimer(190);

        waitTimer(2000);
    }

    public void dabStanga() {
        hw.Dr1.setPosition(.75);
        hw.Dr2.setPosition(.85);
        hw.Dr3.setPosition(1);
        hw.motorSetPosition(hw.Dr4, .8, hw.dr4Pos, POWER4);

        hw.St1.setPosition(.52);
        hw.St2.setPosition(.1);
        hw.St3.setPosition(0);
        hw.motorSetPosition(hw.St4, 0.13, hw.st4Pos, POWER4);

        hw.cap.setPosition(.65);

        waitTimer(200);
    }

    public void dabDreapta() {
        hw.Dr1.setPosition(.84);
        hw.Dr2.setPosition(.85);
        hw.Dr3.setPosition(1);
        hw.motorSetPosition(hw.Dr4, .15, hw.dr4Pos, POWER4);

        hw.St1.setPosition(.7);
        hw.St2.setPosition(.15);
        hw.St3.setPosition(0);
        hw.motorSetPosition(hw.St4, .7, hw.st4Pos, POWER4);

        hw.cap.setPosition(.5);

        waitTimer(200);
    }

    public void salut()
    {
        if(ok==0) {
            hw.cap.setPosition(.585);
            hw.Dr2.setPosition(0.5);
            hw.Dr3.setPosition(1);
            hw.motorSetPosition(hw.Dr4, .15, hw.dr4Pos, POWER4);
            waitTimer(150);

            hw.St2.setPosition(.45);
            hw.St3.setPosition(0);
            hw.motorSetPosition(hw.St4, .13, hw.st4Pos, POWER4);
            waitTimer(150);
        }

        hw.St1.setPosition(0.9);
        hw.Dr1.setPosition(0.7);
        waitTimer(500);

        hw.Dr1.setPosition(0.5);
        hw.St1.setPosition(0.6);
        waitTimer(500);
    }

        public void sheesh() {
        hw.cap.setPosition(.585);

        hw.Dr1.setPosition(.84);
        hw.Dr2.setPosition(0.15);
        hw.Dr3.setPosition(0.5);
        hw.motorSetPosition(hw.Dr4, 0.38, hw.dr4Pos, POWER4);
        waitTimer(150);

        hw.St1.setPosition(0.6);
        hw.St2.setPosition(.45);
        hw.St3.setPosition(0.4);
        hw.motorSetPosition(hw.St4, 0.8, hw.st4Pos, POWER4);
        waitTimer(150);
    }

    public void vals()
    {

    }
    public void waitTimer (int miliseconds) {
        ElapsedTime timer;
        timer = new ElapsedTime();
        while (timer.milliseconds() < miliseconds) {
            ;
        }
    }
}