/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveForward extends Command {

  private Timer timer;
  private double _time;
  private double _power;
  private double _gain;

  public DriveForward(double time, double power, double gain) {
    requires(Robot.drivetrain);
    _time = time;
    _power = power;
    _gain = gain;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drivetrain.resetGyro();
    timer = new Timer();
    timer.start();
    timer.reset();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double correction = Robot.drivetrain.getAngle() * _gain;

    Robot.drivetrain.drive(_power, _power - correction);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (timer.get() < _time) {
      return false;
    }
    else return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    timer.stop();
    Robot.drivetrain.drive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
