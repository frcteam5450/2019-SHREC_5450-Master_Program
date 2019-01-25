/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class UpdateCompressor extends Command {
  public UpdateCompressor() {
    // This command requires the compressor in order to run! I hope you guessed that.
    requires(Robot.compressor);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // We'll start with the compressor turned off, just to be safe
    Robot.compressor.stopCompressor();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    if (Robot.compressor.pressureLow()) {
      Robot.compressor.startCompressor();
    }
    else {
      Robot.compressor.stopCompressor();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //This should run until the robot is disabled.
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    //We'll stop the compressor when the robot is disabled.
    Robot.compressor.stopCompressor();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    //No reason not to just use end() for this.
    end();
  }
}
