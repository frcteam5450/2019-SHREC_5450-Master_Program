/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class PDP extends Subsystem {

  private PowerDistributionPanel pdp;

  public PDP() {
    pdp = new PowerDistributionPanel();
  }

  public double batteryVoltage() {
    return pdp.getVoltage();
  }

  public double totalCurrentDraw() {
    return pdp.getTotalCurrent();
  }

  public double temperature() {
    return pdp.getTemperature();
  }

  public double totalWattage() {
    return pdp.getTotalPower();
  }

  public void displayStats() {
    SmartDashboard.putNumber("Total Current Draw", totalCurrentDraw());
    SmartDashboard.putNumber("PDP Temperature", temperature());
    SmartDashboard.putNumber("Total Wattage", totalWattage());
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
