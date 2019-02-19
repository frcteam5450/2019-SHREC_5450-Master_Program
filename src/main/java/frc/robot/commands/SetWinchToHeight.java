/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class SetWinchToHeight extends Command {
  private double _height;
  private boolean _end = false;

  public SetWinchToHeight(double height) {
    requires(Robot.winch);
    _height = height;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.winch.setHeight(_height);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    _end = false;
    Robot.winch.setHeight(_height);
    if (!DriverStation.getInstance().isTest()) {
      double offset = Robot.winch.getRawSetPosition() - Robot.winch.getRawPosition();
      double power = offset * RobotMap.proportionControlValue;
      
        SmartDashboard.putNumber("Winch power set", power);
  
      if (Math.abs(Robot.winch.getSpeed()) < RobotMap.stopSpeed && 
      ((Math.abs(power) < .15 && Robot.winch.getHeight() == RobotMap.lowerCargoPos)
      || (Math.abs(power) < .37 && Robot.winch.getHeight() == RobotMap.middleCargoPos)
      || (Math.abs(power) < .65 && Robot.winch.getHeight() == RobotMap.upperCargoPos))) {
        _end = true;
      }
      else {
        if (power < 0) {
          power = power * .2 - .05;
        }
        Robot.winch.releaseBrake();
        Robot.winch.runWinch(-power);
      }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return _end;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.winch.runWinch(0);
    Robot.winch.brake();
    SmartDashboard.putBoolean("IsFinished?", true);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
