package org.firstinspires.ftc.teamcode;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.MotorControlAlgorithm;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ReadWriteFile;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoboDancerConfig {
    /*public DcMotor rightFront;
    public DcMotor leftFront;
    public DcMotor rightBack;
    public DcMotor leftBack;*/
    public Servo Dr1;
    public Servo Dr2;
    public Servo Dr3;
    public DcMotorEx Dr4;
    public Servo St1;
    public Servo St2;
    public Servo St3;
    //public Servo St4;
    public DcMotorEx St4;
    public Servo cap;

    String calibrationFileName = "roboDansatorCalibration.json";

    public static PIDFCoefficients st4CoeffPos = new PIDFCoefficients(15, 0.5, 5, 0, MotorControlAlgorithm.LegacyPID);
    public static PIDFCoefficients st4CoeffVelo = new PIDFCoefficients(30, 0.6, 5, 0);

    public static PIDFCoefficients dr4CoeffPos = new PIDFCoefficients(15, 0.5, 5, 0, MotorControlAlgorithm.LegacyPID);
    public static PIDFCoefficients dr4CoeffVelo = new PIDFCoefficients(30, 0.6, 5, 0);

    public static int st4Pos = 0;
    public static int dr4Pos = 0;

    public static double lastSt4 = Double.NaN;
    public static double lastDr4 = Double.NaN;

    public RoboDancerConfig(HardwareMap hw) {
        /*rightBack = hw.get(DcMotor.class, "rightBack");
        rightFront = hw.get(DcMotor.class, "rightFront");
        leftBack = hw.get(DcMotor.class, "leftBack");
        leftFront = hw.get(DcMotor.class, "leftFront");*/

        lastSt4 = Double.NaN;
        lastDr4 = Double.NaN;

        Dr1 = hw.get(Servo.class, "dreapta1");
        Dr2 = hw.get(Servo.class, "dreapta2");
        Dr3 = hw.get(Servo.class, "dreapta3");
        Dr4 = hw.get(DcMotorEx.class, "dreapta4");

        St1 = hw.get(Servo.class, "stanga1");
        St2 = hw.get(Servo.class, "stanga2");
        St3 = hw.get(Servo.class, "stanga3");
        St4 = hw.get(DcMotorEx.class, "stanga4");

        cap = hw.get(Servo.class, "cap");

        Dr4.setDirection(DcMotorSimple.Direction.REVERSE);

        File file = AppUtil.getInstance().getSettingsFile(calibrationFileName);
        String json = ReadWriteFile.readFile(file);

        if(json != "") {
            Map<String, Integer> calibrationData = new Gson().fromJson(json, new TypeToken<HashMap<String, Integer>>(){}.getType());

            st4Pos = calibrationData.get("st4Pos");
            dr4Pos = calibrationData.get("dr4Pos");
        }

        St4.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, st4CoeffPos);
        St4.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, st4CoeffVelo);

        Dr4.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, dr4CoeffPos);
        Dr4.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, dr4CoeffVelo);

        St4.setTargetPositionTolerance(7);
        Dr4.setTargetPositionTolerance(7);

    }

    public void resetEncoders() {
        St4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Dr4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        St4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Dr4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void resetEncoder(DcMotorEx motor) {
        motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    }

    public void motorSetPosition(DcMotorEx motor, double position, int maxPos, double power) {

        int pos = (int)(maxPos * position);

        if(motor == St4) {
            if(lastSt4 == Double.NaN)
                lastSt4 = position;
            else if(lastSt4 == position)
                return;
            else
                lastSt4 = position;
        }
        else if(motor == Dr4) {
            if(lastDr4 == Double.NaN)
                lastDr4 = position;
            else if(lastDr4 == position)
                return;
            else
                lastDr4 = position;
        }

        motor.setPower(0);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        motor.setTargetPosition(pos);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motor.setPower(power);
    }

    public double clipPower(double power) {
        return (power > 1) ? 1 : ((power < -1) ? -1 : power);
    }
}