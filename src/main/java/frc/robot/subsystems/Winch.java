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

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.UpdateWinch;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * Class for the winch that controls the elevators height. Includes a discbrake, two motors and an encoder
 */
public class Winch extends Subsystem {
  
  WPI_TalonSRX winchMotor1;
  WPI_TalonSRX winchMotor2;
  
  Solenoid discBrake;

  private double _rawInitPoint;
  private double _rawSetPoint;
  private double _setPoint;

  public Winch() {
    discBrake = new Solenoid(RobotMap.primaryPCMID, RobotMap.discBrake);

    winchMotor1 = new WPI_TalonSRX(RobotMap.winchMotor1);
    winchMotor2 = new WPI_TalonSRX(RobotMap.winchMotor2);

    setRawInitPosition(getRawPosition());
    setRawSetPosition(_rawInitPoint);
    setHeight(0);
    
  }

  public void runWinch(double power) {
    winchMotor1.set(power);
    winchMotor2.set(power);
  }

  public double getRawPosition() {
    return winchMotor1.getSelectedSensorPosition();
  }

  public double getRawInitPosition() {
    return _rawInitPoint;
  }

  public double getRawSetPosition() {
    return _rawSetPoint;
  }

  public void brake() {
    discBrake.set(true);
  }

  public void releaseBrake() {
    discBrake.set(false);
  }

  public void setRawSetPosition(double setPoint) {
    _rawSetPoint = setPoint;
  }

  public void setRawInitPosition(double initPoint) {
    _rawInitPoint = initPoint;
  }

  public void setHeight(double height) {
    _setPoint = height;
    _rawSetPoint = _rawInitPoint + (height * RobotMap.countsPerInch);
  }

  public double getHeight() {
    return _setPoint;
  }

  public void displayStats() {
    SmartDashboard.putNumber("Winch Motor 1 Current", winchMotor1.getOutputCurrent());
    SmartDashboard.putNumber("Winch Motor 2 Current", winchMotor2.getOutputCurrent());
    
    SmartDashboard.putNumber("Winch Encoder Raw Position", getRawPosition());
    SmartDashboard.putNumber("Winch Raw Set Position", getRawSetPosition());
    SmartDashboard.putString("Winch Set Height", getHeight() + "inches");
    SmartDashboard.putNumber("Winch Raw Init Position", getRawInitPosition());

    SmartDashboard.putBoolean("Disc Brake Value", discBrake.get());
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new UpdateWinch());
  }
}
