/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * The winch controls an elevators height
 */
public class Winch extends Subsystem {
  
  WPI_TalonSRX winchMotor1;
  WPI_TalonSRX winchMotor2;
  
  Solenoid discBrake;

  public Winch() {
    discBrake = new Solenoid(RobotMap.primaryPCMID, RobotMap.discBrake);

    winchMotor1 = new WPI_TalonSRX(RobotMap.winchMotor1);
    winchMotor2 = new WPI_TalonSRX(RobotMap.winchMotor2);
  }

  public void runWinch() {

  }

  public double getRawPosition() {
    return winchMotor1.getSelectedSensorPosition();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
