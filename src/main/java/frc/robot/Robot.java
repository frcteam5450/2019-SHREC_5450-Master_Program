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

/** 
 * March 8, 2019 | Version 0.1.6 | Auburn Competition Load Day Code
 * 
 * -all subsystems are usable, all hatch and cargo heights are programmed.
 */

/**
 * February 18, 2019 | Version 0.1.5 | Bag Day Code
 */

/**
 * Febuary 13, 2019 | Version 0.1.3 | **Version in Process**
 * 
 * -removed SDBStats cause it didn't freakin' work, added methods for SDBStats to robotperiodic.
 */

/**
 * Febuary 12, 2019 | Version 0.1.2 | Confirmed working Compressor Code
 * 
 * -modified motor power
 * -temporarily added solenoid for testing, but was removed.
 */

/**
 * January 30, 2019 | Version 0.1.1 | Confirmed Working Drivetrain Code
 * 
 * -Fixed Drivetrain after testing
 * -Fixed How the robot displays stats on SDB
 * 
 * **COMPRESSOR CODE IS STILL UNTESTED**
 */

 /**
  * January 25, 2019 | Version 0.1.0
  * 
  * -Drivetrain Subsystem for controlling drive motors
  * -TeleopDrive Command for driver control of drivetrain
  * -SmartDashboardStats Command displays statistics from subsystems on SDB.
  * 
  * **THIS VERSION IS UNTESTED**
  */

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.OI;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static Drivetrain drivetrain = new Drivetrain();
  public static CustomCompressor compressor = new CustomCompressor(RobotMap.compressorRelay, RobotMap.primaryPCMID);
  public static Winch winch = new Winch();
  public static Intake intake = new Intake();
  public static PDP pdp = new PDP();
  public static Climber climber = new Climber();
  
  //CameraServer leftCamera;
  //CameraServer rightCamera;

  public static OI m_oi;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    //leftCamera.addAxisCamera("Left Camera", "10.54.50.4");
    //rightCamera.addAxisCamera("Right Camera", "10.54.50.3");
    //rightCamera.getInstance().startAutomaticCapture();
    m_oi = new OI();
    //m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    //drivetrain.displayStats();
    winch.displayStats();
    intake.displayStats();
    compressor.displayStats();
    //pdp.displayStats();
    //Functions.reportRobotStatus();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
    intake.runCargoIntake(0);
    drivetrain.drive(0, 0);
    winch.runWinch(0);
    intake.resetCargo();
    intake.releaseHatch();
    winch.setHeight(1.5);
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

    

    //Robot.intake.runCargoIntake(.5 * (OI.mechanismController.getTriggerAxis(Hand.kLeft) - OI.mechanismController.getTriggerAxis(Hand.kRight)));
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    if (OI.driveController.getBumper(Hand.kLeft)) {
      winch.releaseBrake();
      winch.runWinch(.15);
    }
    else if (OI.driveController.getBumper(Hand.kRight)) {
      winch.releaseBrake();
      winch.runWinch(-RobotMap.winchPower);
    }
    else {
      winch.runWinch(0);
      winch.brake();
    }
  }
}
