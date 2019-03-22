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

import edu.wpi.first.wpilibj.ADXRS450_Gyro; 
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.TeleopDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotMap;

/**
 * This is the drivetrain subsystem. Consists of the four drive motors, the drive mounted encoder, and the gryoscope mounted on the roborio.
 * Methods for normal drive, strafing, and displaying motor current.
 */
public class Drivetrain extends Subsystem {
  //Motor Controller Declarations
  WPI_TalonSRX backLeftMotor;
  WPI_TalonSRX frontLeftMotor;
  WPI_TalonSRX backRightMotor;
  WPI_TalonSRX frontRightMotor;

  //gyroscope declaration
  ADXRS450_Gyro gyro;
  
  public Drivetrain() {
    //motor Definitions
    backLeftMotor = new WPI_TalonSRX(RobotMap.backLeftMotor);
    frontLeftMotor = new WPI_TalonSRX(RobotMap.frontLeftMotor);
    backRightMotor = new WPI_TalonSRX(RobotMap.backRightMotor);
    frontRightMotor = new WPI_TalonSRX(RobotMap.frontRightMotor);

    //gyroscope definition
    gyro = new ADXRS450_Gyro();

    
  }

  //Normal drive method
  public void drive(double leftPower, double rightPower) {
    backLeftMotor.set(leftPower);
    frontLeftMotor.set(leftPower);
    backRightMotor.set(-rightPower);
    frontRightMotor.set(-rightPower);
  }

  //Method for fancy strafing!
  public void strafe(double frontPower, double backPower) {
    backLeftMotor.set(-backPower);
    backRightMotor.set(backPower);
    frontLeftMotor.set(-frontPower);
    frontRightMotor.set(frontPower);
  }

  //Method for manual control of all four motors
  public void FourWheelDrive(double backLeftPower, double frontLeftPower, double backRightPower, double frontRightPower) {
    backLeftMotor.set(backLeftPower);
    backRightMotor.set(-backRightPower);
    frontLeftMotor.set(frontLeftPower);
    frontRightMotor.set(-frontRightPower);
  }

  //Method for displaying motor current draw on SDB
  public void displayStats() {
    SmartDashboard.putNumber("Back Left Motor", backLeftMotor.getOutputCurrent());
    SmartDashboard.putNumber("Front Left Motor", frontLeftMotor.getOutputCurrent());
    SmartDashboard.putNumber("Back Right Motor", backRightMotor.getOutputCurrent());
    SmartDashboard.putNumber("Front Right Motor", frontRightMotor.getOutputCurrent());

    SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
  }

  public void resetGyro() {
    gyro.reset();
  }

  public void claibrateGyro() {
    gyro.calibrate();
  }

  public double getAngle() {
    return gyro.getAngle();
  }

  @Override
  public void initDefaultCommand() {
    //Default Command is driver controller of robot.
    setDefaultCommand(new TeleopDrive());
  }
}
