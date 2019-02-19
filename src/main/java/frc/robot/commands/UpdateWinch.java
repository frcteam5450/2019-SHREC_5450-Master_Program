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

import javax.swing.text.StyleContext.SmallAttributeSet;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class UpdateWinch extends Command {
  public UpdateWinch() {
    requires(Robot.winch);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.winch.runWinch(0);
    Robot.winch.brake();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (!DriverStation.getInstance().isTest()) {
    double offset = Robot.winch.getRawSetPosition() - Robot.winch.getRawPosition();
    double power = offset * RobotMap.proportionControlValue;
    
      SmartDashboard.putNumber("Winch power set", power);

    if (Math.abs(Robot.winch.getSpeed()) < RobotMap.stopSpeed && 
    (Math.abs(power) < .1 && Robot.winch.getHeight() == RobotMap.lowerCargoPos)
    || (Math.abs(power) < .37 && Robot.winch.getHeight() == RobotMap.middleCargoPos)
    || (Math.abs(power) < .65 && Robot.winch.getHeight() == RobotMap.upperCargoPos)) {
      Robot.winch.brake();
      try {Thread.sleep(10);}
      catch (Exception e){ e.printStackTrace();}
      Robot.winch.runWinch(0);
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
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.winch.runWinch(0);
    Robot.winch.releaseBrake();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
