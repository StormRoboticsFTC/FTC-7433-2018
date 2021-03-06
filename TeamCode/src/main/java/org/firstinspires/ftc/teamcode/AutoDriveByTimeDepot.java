/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file illustrates the concept of driving a path based on time.
 * It uses the common Pushbot hardware class to define the drive on the robot.
 * The code is structured as a LinearOpMode
 *
 * The code assumes that you do NOT have encoders on the wheels,
 *   otherwise you would use: PushbotAutoDriveByEncoder;
 *
 *
 *
 *  The code is written in a simple form with no optimizations.
 *  However, there are several ways that this type of sequence could be streamlined,
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="Pushbot: Auto Drive By Time", group="Pushbot")
//Disabled
public class AutoDriveByTimeDepot extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareTest         robot   = new HardwareTest();   // Use a Pushbot's hardware
    private ElapsedTime     runtime = new ElapsedTime();


    static final double     FORWARD_SPEED = 0.6;
    static final double     TURN_SPEED    = 0.5;

    @Override

    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way


        sleep(15000);
        // Step 1:  Unlatch
        robot.B2.setPower(0.8);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.8)) {
            //telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            //telemetry.update();
        }
        robot.B2.setPower(0);
        //sleep(1000);

        // Step 2: Rotate
        robot.A0.setPower(-0.5);
        robot.A1.setPower(TURN_SPEED);
        robot.A2.setPower(-0.5);
        robot.A3.setPower(TURN_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.45)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        robot.A0.setPower(0);
        robot.A1.setPower(0);
        robot.A2.setPower(0);
        robot.A3.setPower(0);
        //sleep(1000);

        // Step 3: Return hook to starting position
        robot.B2.setPower(-0.8);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.8)) {
            //telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            //telemetry.update();
        }
        robot.B2.setPower(0);
        //sleep(1000);



        // Step 4: N/A
            robot.A0.setPower(-0.5);
            robot.A1.setPower(-0.5);
            robot.A2.setPower(-0.5);
            robot.A3.setPower(-0.5);
            //robot.B2.setPower(-0.8);
            runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.0)) {
            //telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            //telemetry.update();
        }
        robot.A0.setPower(0);
        robot.A1.setPower(0);
        robot.A2.setPower(0);
        robot.A3.setPower(0);
        //sleep(1000);

        // 2.9 - 0.35 = 2.55

            //Actual Step 4: "Unrotate" the robot
            robot.A0.setPower(0.4);
            robot.A1.setPower(-0.4);
            robot.A2.setPower(0.4);
            robot.A3.setPower(-0.4);
            //robot.B2.setPower(-0.8);
            runtime.reset();
            while (opModeIsActive() && (runtime.seconds() < 0.4)) {
                telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
                telemetry.update();
            }
                       robot.A0.setPower(0);
                       robot.A1.setPower(0);
                       robot.A2.setPower(0);
                       robot.A3.setPower(0);
                       //sleep(1000);

        // 2.9 - 0.35 = 2.55
        // 2.55 - 1.2 = 1.35

            //speed
            //Step 5: Move forward
            robot.A0.setPower(0.4);
            robot.A1.setPower(0.4);
            robot.A2.setPower(0.4);
            robot.A3.setPower(0.4);
            //robot.B2.setPower(-0.8);
            runtime.reset();
            while (opModeIsActive() && (runtime.seconds() < 1.4)) {
                telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
                telemetry.update();
            }
        robot.A0.setPower(0);
        robot.A1.setPower(0);
        robot.A2.setPower(0);
        robot.A3.setPower(0);
        //sleep(1000);

        //2.9 - 0.35 = 2.55
        // 2.55 - 1.2 = 1.35
        // 1.35 - 0.9 = 0.45


        //Step 6: Release marker
        robot.B1.setPower(-0.8);
        //robot.B2.setPower(-0.8);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.45)) {
            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.B1.setPower(0);
        //robot.B2.setPower(0);

        //Step 7: Back up a bit
        robot.A0.setPower(-0.4);
        robot.A1.setPower(-0.4);
        robot.A2.setPower(-0.4);
        robot.A3.setPower(-0.4);
        //robot.B2.setPower(-0.8);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.15)) {
            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.A0.setPower(0);
        robot.A1.setPower(0);
        robot.A2.setPower(0);
        robot.A3.setPower(0);

        //Step 8: Rotate toward crater
        robot.A0.setPower(0.4);
        robot.A1.setPower(-0.4);
        robot.A2.setPower(0.4);
        robot.A3.setPower(-0.4);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 9: Move toward crater
        robot.A0.setPower(0.6);
        robot.A1.setPower(0.6);
        robot.A2.setPower(0.6);
        robot.A3.setPower(0.6);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.7)) {
            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
/*
        // Step 3:  Drive Backwards for 1 Second
        robot.A0.setPower(-FORWARD_SPEED);
        robot.A1.setPower(-FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Path", "Leg 3: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 4:  Stop and close the claw.
        robot.A0.setPower(0);
        robot.A1.setPower(0);
        // robot.leftClaw.setPosition(1.0);
        // robot.rightClaw.setPosition(0.0);
        */
        
        telemetry.addData("Path", "Complete");
        telemetry.update();

        sleep(1000);

}

}

