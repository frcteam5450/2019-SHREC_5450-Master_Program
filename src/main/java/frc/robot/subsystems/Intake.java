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

/**
 * Class for intake mechanism, including both hatch mechanism and cargo intake, along with the pistons 
 * that actuate the cargo intake.
 */
public class Intake extends Subsystem {
  
  private WPI_TalonSRX intakeMotor;
  
  private DoubleSolenoid cargoLift;
  private Solenoid hatchIntake;

  public Intake() {
    intakeMotor = new WPI_TalonSRX(RobotMap.cargoIntakeMotor);

    cargoLift = new DoubleSolenoid(RobotMap.upperPCMID, RobotMap.cargoLiftDown, RobotMap.cargoLiftUp);
    hatchIntake = new Solenoid(RobotMap.upperPCMID, RobotMap.hatchGrabber);

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
    cargoLift.set(Value.kReverse);
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

  public void displayStats() {
    SmartDashboard.putNumber("Intake Motor Current", intakeMotor.getOutputCurrent());
    SmartDashboard.putBoolean("Hatch Grabber Value", hatchIntake.get());
    
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
