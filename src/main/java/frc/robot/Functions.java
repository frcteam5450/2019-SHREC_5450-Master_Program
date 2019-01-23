/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class Functions {
    public static double returnGreatestAbs(double a, double b) {
        double _a = Math.abs(a);
        double _b = Math.abs(b);

        if (_a > _b)
            return a;
        
        else
            return b;
    }
}
