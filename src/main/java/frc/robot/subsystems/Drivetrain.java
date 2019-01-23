/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  WPI_TalonSRX backLeftMotor;
  WPI_TalonSRX frontLeftMotor;
  WPI_TalonSRX backRightMotor;
  WPI_TalonSRX frontRightMotor;
  
  Drivetrain() {
    backLeftMotor = new WPI_TalonSRX(RobotMap.backLeftMotor);
    frontLeftMotor = new WPI_TalonSRX(RobotMap.frontLeftMotor);
    backRightMotor = new WPI_TalonSRX(RobotMap.backRightMotor);
    frontRightMotor = new WPI_TalonSRX(RobotMap.frontRightMotor);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
