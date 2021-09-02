package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Arrays;
import java.util.List;

public class RoboDancerConfig {
    public DcMotor rightFront;
    public DcMotor leftFront;
    public DcMotor rightBack;
    public DcMotor leftBack;
    public Servo Dr1;
    public Servo Dr2;
    public Servo Dr3;
    public Servo Dr4;
    public Servo St1;
    public Servo St2;
    public Servo St3;
    public Servo St4;
    public Servo cap;


    public RoboDancerConfig(HardwareMap hw) {
        rightBack = hw.get(DcMotor.class, "rightBack");
        rightFront = hw.get(DcMotor.class, "rightFront");
        leftBack = hw.get(DcMotor.class, "leftBack");
        leftFront = hw.get(DcMotor.class, "leftFront");

        Dr1 = hw.get(Servo.class, "dreapta1");
        Dr2 = hw.get(Servo.class, "dreapta2");
        Dr3 = hw.get(Servo.class, "dreapta3");
        Dr4 = hw.get(Servo.class, "dreapta4");

        St1 = hw.get(Servo.class, "stanga1");
        St2 = hw.get(Servo.class, "stanga2");
        St3 = hw.get(Servo.class, "stanga3");
        St4 = hw.get(Servo.class, "stanga4");

        cap = hw.get(Servo.class, "cap");

    }

    public double clipPower(double power) {
        return (power > 1) ? 1 : ((power < -1) ? -1 : power);
    }
}