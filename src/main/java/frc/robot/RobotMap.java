/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/**
 * Please Add your name here if you contributed to this class.
 * Contributers:
 * Evan Garrison
 */

package frc.robot;

import java.net.*;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  //Motor CAN ID's
  public static int backLeftMotor = 0;
  public static int frontLeftMotor = 1;
  public static int backRightMotor = 2;
  public static int frontRightMotor = 3;

  //Controller Ports
  public static int controller1 = 0;
  public static int controller2 = 1;

  //Motor Power Multiplier
  public static double power = .2;

  //Compressor Relay Channel
  public static int compressorRelay = 0;
  public static int pressureSwitchPCMID = 0;

/**
 * Constants for vision tracking
 */

 public static String raspberryPiIP = "10.54.50.19";
 public static String UDPServerIP = "10.54.50.2";
 public static int serverPort = 5800;
}
