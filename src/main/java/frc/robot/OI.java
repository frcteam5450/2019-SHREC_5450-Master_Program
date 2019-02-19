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
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.*;

import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  
  //Drivetrain driver button declarations
  public static final XboxController driveController = new XboxController(RobotMap.controller1);
  Button A1 = new JoystickButton(driveController, RobotMap.A);
  Button B1 = new JoystickButton(driveController, RobotMap.B);
  Button X1 = new JoystickButton(driveController, RobotMap.X);
  Button Y1 = new JoystickButton(driveController, RobotMap.Y);
  Button LB1 = new JoystickButton(driveController, RobotMap.LB);
  Button RB1 = new JoystickButton(driveController, RobotMap.RB);
  Button LS1 = new JoystickButton(driveController, RobotMap.LS);
  Button RS1 = new JoystickButton(driveController, RobotMap.RS);
  Button select1 = new JoystickButton(driveController, RobotMap.select);
  Button start1 = new JoystickButton(driveController, RobotMap.start);

  //Mechanism driver button declarations
  public static final XboxController mechanismController = new XboxController(RobotMap.controller2);
  Button A2 = new JoystickButton(mechanismController, RobotMap.A);
  Button B2 = new JoystickButton(mechanismController, RobotMap.B);
  Button X2 = new JoystickButton(mechanismController, RobotMap.X);
  Button Y2 = new JoystickButton(mechanismController, RobotMap.Y);
  Button LB2 = new JoystickButton(mechanismController, RobotMap.LB);
  Button RB2 = new JoystickButton(mechanismController, RobotMap.RB);
  Button LS2 = new JoystickButton(mechanismController, RobotMap.LS);
  Button RS2 = new JoystickButton(mechanismController, RobotMap.RS);
  Button select2 = new JoystickButton(mechanismController, RobotMap.select);
  Button start2 = new JoystickButton(mechanismController, RobotMap.start);

  public OI() {
    A2.whenPressed(new SetWinchToHeight(RobotMap.lowerCargoPos));
    B2.whenPressed(new SetWinchToHeight(RobotMap.middleCargoPos));
    Y2.whenPressed(new SetWinchToHeight(RobotMap.upperCargoPos));

    RB2.whenPressed(new ToggleCargoLift());
    LB2.whenPressed(new ToggleHatchIntake());
  }
}
