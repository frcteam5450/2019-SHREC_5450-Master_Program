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

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // This is where we'll store most of our variables like motor power, and CAN ID's. If you need to change
  // motor power, change the motor power variables. Anyone can do it!

  //Motor CAN ID's
  public static int backLeftMotor = 0;
  public static int frontLeftMotor = 1;
  public static int backRightMotor = 2;
  public static int frontRightMotor = 3;
  public static int winchMotor1 = 4;
  public static int winchMotor2 = 5;
  public static int cargoIntakeMotor = 6;

  //Controller Ports
  public static int controller1 = 0;
  public static int controller2 = 1;

  //Motor Power Multipliers
  public static double drivePower = .75;
  public static double winchPower = .75;
  public static double cargoIntakePower = .75;

  //Compressor Relay Channel
  public static int compressorRelay = 0;

  //PCM CAN ID's
  public static int primaryPCMID = 0;
  public static int upperPCMID = 1;

  //Solenoids
  public static int discBrake = 2;
  public static int cargoLiftUp = 1;
  public static int cargoLiftDown = 0;
  public static int hatchGrabber = 2;
  public static int forwardClimb = 1;
  public static int rearClimb = 0;

  //Controller Mapping
  public static int A = 1;
  public static int B = 2;
  public static int X = 3;
  public static int Y = 4;
  public static int LB = 5;
  public static int RB = 6;
  public static int select = 7;
  public static int start = 8;
  public static int LS = 9;
  public static int RS = 10;

  //Winch Control Loop
  public static double countsPerInch = 1500;
  public static double proportionControlValue = .0001;
  public static double stopSpeed = 100;
  public static double lowerCargoPos = .5;
  public static double middleCargoPos = 25;
  public static double upperCargoPos = 43;
  public static double cargoShipCargoPos = 20;
  public static double lowerHatchPos = 8;
  public static double middleHatchPos = 20;
  public static double upperHatchPos = 40;

  //AIO and DIO ports
  public static int upperUltraSonicSensor = 0;
  public static int liftSensor = 1;
}
