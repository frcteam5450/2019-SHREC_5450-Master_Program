/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class TeleopDrive extends Command {
  XboxController driver1;
  XboxController driver2;
  
  public TeleopDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drivetrain);
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
    double leftPower = driver1.getY(Hand.kLeft) * RobotMap.power;
    double rightPower = driver1.getY(Hand.kRight) * RobotMap.power;

    Robot.drivetrain.drive(leftPower, rightPower);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.drive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
