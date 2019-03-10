/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/**
 * Please Add your name here if you contributed to this class.
 * Contributers:
 * Evan Garrison
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.Functions;

/* 
 * Driver Control command, uses input from xbox controllers to drive the robot. Currently, the robot can't drive. Sorry.
 */
public class TeleopDrive extends Command {
  
  //Controller declarations
  XboxController driver1;
  XboxController driver2;
  
  public TeleopDrive() {
    // This command requires the Drivetrain to run! Obviously...
    requires(Robot.drivetrain);

    //Controller definitions
    driver1 = new XboxController(RobotMap.controller1);
    driver2 = new XboxController(RobotMap.controller2);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (DriverStation.getInstance().isEnabled()) {

      //gets input from controller for normal driving
      double leftPower = Functions.returnGreatestAbs(driver1.getY(Hand.kLeft), driver2.getY(Hand.kLeft)) * RobotMap.drivePower;
      double rightPower = Functions.returnGreatestAbs(driver1.getY(Hand.kRight), driver2.getY(Hand.kRight)) * RobotMap.drivePower;

      //gets input from controller for strafing
      double frontPower = Functions.returnGreatestAbs(driver1.getX(Hand.kLeft), driver2.getX(Hand.kLeft));
      double backPower = Functions.returnGreatestAbs(driver1.getX(Hand.kRight), driver2.getX(Hand.kRight));

      //rear motor power calculations
      double backLeftPower = leftPower;// + backPower;
      double backRightPower = rightPower;// - backPower;

      //forward motor power calculations
      double frontLeftPower = leftPower;// - frontPower;
      double frontRightPower = rightPower;// + frontPower;

      //pushes power variables to motors
      Robot.drivetrain.FourWheelDrive(backLeftPower, frontLeftPower, backRightPower, frontRightPower);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    // We always want this command to run, so it should never be finished.
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    //It's probably a good idea to stop the motors when the command's done.
    Robot.drivetrain.drive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    //Calls end() because we want it to do the same thing as end().
    end();
  }
}
