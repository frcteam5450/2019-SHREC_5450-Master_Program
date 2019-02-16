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
import frc.robot.commands.UpdateCompressor;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Compressor;

/**
 * Class for our Custom Compressor circuit.
 */
public class CustomCompressor extends Subsystem {
  
  //Relay Declaration
  Relay compressorRelay;

  //Pressure Switch Declaration
  Compressor pressureSwitch;

  public CustomCompressor(int relayChannel, int pressureSwitchPCMID) {
    //Relay Definition
    compressorRelay = new Relay(relayChannel);

    //Pressure Switch Definition
    pressureSwitch = new Compressor(pressureSwitchPCMID);
  }

  //Turns on the relay and sets it to forward polarity
  public void startCompressor() {
    compressorRelay.set(Value.kOn);
    compressorRelay.set(Value.kForward);
  }

  //Turns off the relay
  public void stopCompressor() {
    compressorRelay.set(Value.kOff);
  }

  // Returns true if the pressure is lower than ~120 PSI
  public boolean pressureLow() {
    return pressureSwitch.enabled();
  }

  public void displayStats() {
    SmartDashboard.putBoolean("Compressor Enabled?", pressureLow());
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new UpdateCompressor());
  }
}
