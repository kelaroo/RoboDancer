package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.google.gson.Gson;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ReadWriteFile;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Config
@TeleOp
public class MotorCalibration extends LinearOpMode {

    RoboDancerConfig hw;

    public static int st4Pos = 0;
    public static int dr4Pos = 0;

    @Override
    public void runOpMode() throws InterruptedException {

        hw = new RoboDancerConfig(hardwareMap);

        telemetry.addData("Calibration", "Press start to begin");
        telemetry.update();

        waitForStart();

        telemetry.addData("St4", "Set to eagle position then press 'x'");
        telemetry.update();

        while(!gamepad1.x);
        hw.resetEncoder(hw.St4);

        telemetry.addData("St4", "Set to forward position then press 'y'");
        telemetry.update();

        while(!gamepad1.y) {
            st4Pos = hw.St4.getCurrentPosition();
        }
        st4Pos = hw.St4.getCurrentPosition();

        ////////////////////////////// Dr4
        telemetry.addData("Dr4", "Set to eagle position then press 'x'");
        telemetry.update();

        while(!gamepad1.x);
        hw.resetEncoder(hw.Dr4);

        telemetry.addData("Dr4", "Set to forward position then press 'y'");
        telemetry.update();

        while(!gamepad1.y) {
            dr4Pos = hw.Dr4.getCurrentPosition();
        }
        dr4Pos = hw.Dr4.getCurrentPosition();

        //TODO: write values to file (JSON maybe?)

        double stEagle = 0.23;
        double drEagle = 0.75;

        double stFwd = 0.58;
        double drFwd = 0.41;

        int st = (int)(st4Pos / (stFwd-stEagle));
        int dr = (int)(dr4Pos / (drFwd-drEagle));

        Map<String, Integer> calibratinData = new HashMap<String, Integer>();
        calibratinData.put("st4Pos", st);
        calibratinData.put("dr4Pos", dr);

        File file = AppUtil.getInstance().getSettingsFile(hw.calibrationFileName);
        ReadWriteFile.writeFile(file, new Gson().toJson(calibratinData));

        while(!isStopRequested()) {
            telemetry.addData("Calibration", "done!");
            telemetry.update();
        }
    }
}
