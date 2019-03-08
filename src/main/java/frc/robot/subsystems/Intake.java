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

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.UpdateCargoIntake;
import edu.wpi.first.wpilibj.AnalogInput;

/**
 * Class for intake mechanism, including both hatch mechanism and cargo intake, along with the pistons 
 * that actuate the cargo intake.
 */
public class Intake extends Subsystem {
  
  //intake motor declaration
  private WPI_TalonSRX intakeMotor;
  
  //cargoLife and hatchIntake solenoid declarations
  private DoubleSolenoid cargoLift;
  private Solenoid hatchIntake;

  private AnalogInput liftSensor;
  private AnalogInput ultraSonicValue;

  public Intake() {
    //intake motor definition
    intakeMotor = new WPI_TalonSRX(RobotMap.cargoIntakeMotor);
    ultraSonicValue = new AnalogInput(0);
    //cargoLift and hatchIntake solenoid definitions
    cargoLift = new DoubleSolenoid(RobotMap.upperPCMID, RobotMap.cargoLiftDown, RobotMap.cargoLiftUp);
    hatchIntake = new Solenoid(RobotMap.upperPCMID, RobotMap.hatchGrabber);

    liftSensor = new AnalogInput(RobotMap.liftSensor);

    //resets the cargo lift to higher position when robot starts, if it's not already there.
    resetCargo();
  }

  public void runCargoIntake(double power) {
    intakeMotor.set(power);
  }

  public void liftCargo() {
    cargoLift.set(Value.kForward);
  }

  public void lowerCargo() {
    cargoLift.set(Value.kReverse);
  }

  public void resetCargo() {
    cargoLift.set(Value.kForward);
    cargoLift.set(Value.kOff);
  }

  public void grabHatch() {
    hatchIntake.set(true);
  }

  public void releaseHatch() {
    hatchIntake.set(false);
  }

  public boolean hatchGrabberState() {
    return hatchIntake.get();
  }

  public Value cargoLiftState() {
    return cargoLift.get();
  }
  public Double ultrasonicValue() {
    return ultraSonicValue.getAverageVoltage();
  }

  public double getLiftSensorValue() {
    return liftSensor.getVoltage();
  }

  public void displayStats() {
    SmartDashboard.putNumber("Intake Motor Current", intakeMotor.getOutputCurrent());
    SmartDashboard.putBoolean("Hatch Grabber Value", hatchIntake.get());

    SmartDashboard.putNumber("Lift Sensor", getLiftSensorValue());

    boolean ballIn;
    if (ultrasonicValue() < 0.34) ballIn = true;
    else ballIn = false;

    SmartDashboard.putBoolean("Ball In Cargo Intake?", ballIn);
    
    String cargoLiftState;

    if (cargoLift.get() == Value.kForward) {
      cargoLiftState = "Up";
    }
    else if (cargoLift.get() == Value.kReverse) {
      cargoLiftState = "Down";
    }
    else {
      cargoLiftState = "Down, Solenoid off";
    }

    SmartDashboard.putString("Cargo Lift Position", cargoLiftState);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new UpdateCargoIntake());
  }
}
