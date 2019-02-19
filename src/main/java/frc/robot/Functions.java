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

package frc.robot;

import java.sql.Driver;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * These are miscellaneous functions that may or may not be useful.
 * Please note the usage of the function above it in a comment.
 */
public class Functions {

    /**
     * Takes two doubles 'a' and 'b' and returns the double with the greatest absolute value.
     * If a == b, returns 'b'.
     * ie: if a == -10 and b == 5, this function returns '-10'.
     */
    public static double returnGreatestAbs(double a, double b) {
        double _a = Math.abs(a);
        double _b = Math.abs(b);

        if (_a > _b)
            return a;
        
        else
            return b;
    }

    public static void reportRobotStatus() {
        String status = "Browned Out: ";
        if (DriverStation.getInstance().isBrownedOut()) status = status + "true.\n";
        else status = status + "false.\n";

        status = status + "Enabled: ";
        if (DriverStation.getInstance().isEnabled()) status = status + "true.\n";
        else status = status + "false.\n";

        status = status + "Mode: ";
        if (DriverStation.getInstance().isEnabled())
            if (DriverStation.getInstance().isOperatorControl()) status = status + "Teleop.\n";
            else status = status + "Autonomous.\n";
        else status = status + "Disabled.\n";

        status = status + "Recommendation: ";

        if (DriverStation.getInstance().isBrownedOut()) status = status + "Check Battery.\n";
        else status = status + "none.\n";
        
        
    }
}
