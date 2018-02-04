/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6352.robot.subsystems;

import org.usfirst.frc.team6352.robot.RobotMap;
import org.usfirst.frc.team6352.robot.commands.ControlNidecMotorWithGamepad;
import org.usfirst.frc.team6352.robot.commands.ReportNidecMotor;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.NidecBrushless;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class NidecMotorReporter extends Subsystem
{
	DigitalInput direction = new DigitalInput(RobotMap.nidecMotorDirChannel);
	Counter tachometer = new Counter(new DigitalInput(RobotMap.nidecMotorTacChannel));
	
	private final static double pulsesPerRevolution = 15;
		
	public NidecMotorReporter()
	{
		tachometer.setDistancePerPulse(1.0 / pulsesPerRevolution);
	}
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new ReportNidecMotor());
	}
	
	public void report()
	{
		SmartDashboard.putBoolean("Nidec Dir:", direction.get());
		SmartDashboard.putNumber("Nidec rpm:", tachometer.getRate() * 60.0 * (direction.get() ? 1 : -1));
	}
}
