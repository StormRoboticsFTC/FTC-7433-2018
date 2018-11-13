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

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

    /*
    Notes to self:
    leftDrive = Motor A0
    leftDriveTwo = Motor A2
    rightDrive = Motor A1
    rightDriveTwo = Motor A4
    latchingMotor = Motor B0
    intakeMotor = Motor
    */


@TeleOp(name="Test TeleOp Mode", group="Linear Opmode")
//@Disabled
public class TestOpSecond extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor A0 = null;
    private DcMotor A1 = null;
    private DcMotor A2 = null;
    private DcMotor A3 = null;
    private DcMotor B0 = null;
    private DcMotor B1 = null;
    private DcMotor intakeMotor = null;
    private DcMotor outakeMotor = null;
    private Servo outakeServo = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        A0  = hardwareMap.get(DcMotor.class, "left_drive");
        A1 = hardwareMap.get(DcMotor.class, "right_drive");
        A2  = hardwareMap.get(DcMotor.class, "left_drive2");
        A3 = hardwareMap.get(DcMotor.class, "right_drive2");
        B0 = hardwareMap.get(DcMotor.class, "latching_motor");
        B1 = hardwareMap.get(DcMotor.class, "latching_motor2");
        intakeMotor = hardwareMap.get(DcMotor.class, "intake_motor");
        outakeMotor = hardwareMap.get(DcMotor.class, "outake_motor");
        outakeServo  = hardwareMap.get(Servo.class, "outake_servo");

                // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        A0.setDirection(DcMotor.Direction.REVERSE);
        A1.setDirection(DcMotor.Direction.FORWARD);
        A2.setDirection(DcMotor.Direction.REVERSE);
        A3.setDirection(DcMotor.Direction.FORWARD);
        B0.setDirection(DcMotor.Direction.FORWARD);
        B1.setDirection(DcMotor.Direction.FORWARD);
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);
        outakeMotor.setDirection(DcMotor.Direction.FORWARD);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;
            double latchingPowerForward;
            double latchingPowerBackward;
            double intakePowerForward;
            double intakePowerBackward;
            double outakePower;
            boolean outtakeservopower;


            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.
/*
            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.right_stick_x;
            leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
            rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;
*/
            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            leftPower  = -gamepad1.left_stick_y ;
            rightPower = -gamepad1.right_stick_y ;
            latchingPowerForward = gamepad1.right_trigger;
            latchingPowerBackward = gamepad1.left_trigger;
            intakePowerForward = gamepad2.right_trigger;
            intakePowerBackward = gamepad2.left_trigger;
            outakePower = -gamepad2.left_stick_y;
            outtakeservopower = gamepad2.y;

            if (outtakeservopower) {
                outakeServo.setPosition(1.0);
            }else {
                outakeServo.setPosition(0.0);
            }





            // Send calculated power to wheels
            A0.setPower(leftPower);
            A1.setPower(rightPower);
            A2.setPower(leftPower);
            A3.setPower(rightPower);

            B0.setPower(latchingPowerForward - latchingPowerBackward);
            B1.setPower(latchingPowerForward - latchingPowerBackward);
            intakeMotor.setPower(intakePowerForward - intakePowerBackward);
            outakeMotor.setPower(outakePower);



            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%%.+2f), right (%%.2f)", leftPower, rightPower);
            telemetry.addData("Latching", "" + "(%%.+2f)", latchingPowerForward - latchingPowerBackward);
            telemetry.addData("Intake", "" + "(%%.+2f)", intakePowerForward - intakePowerBackward);
            telemetry.addData("Outake", "(%%.+2f)", outakePower);
            telemetry.update();

        }

    }
}
