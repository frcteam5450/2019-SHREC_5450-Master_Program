/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * 
 */
public class Climber extends Subsystem {
  
  private Solenoid rearClimb;
  private Solenoid forwardClimb;

  public Climber() {
    rearClimb = new Solenoid(RobotMap.rearClimb);
    forwardClimb = new Solenoid(RobotMap.forwardClimb);
  }

  public void rearClimb() {
    rearClimb.set(true);
  }

  public void forwardClimb() {
    forwardClimb.set(true);
  }

  public void releaseRearClimb() {
    rearClimb.set(false);
  }

  public void releaseForwardClimb() {
    forwardClimb.set(false);
  }

  public boolean getRearClimb() {
    return rearClimb.get();
  }

  public boolean getForwardClimb() {
    return forwardClimb.get();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
