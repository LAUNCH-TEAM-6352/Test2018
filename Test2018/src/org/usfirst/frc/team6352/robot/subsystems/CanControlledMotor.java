/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6352.robot.subsystems;

import org.usfirst.frc.team6352.robot.RobotMap;
import org.usfirst.frc.team6352.robot.commands.ControlCanControlledMotorWithGamepad;
import org.usfirst.frc.team6352.robot.commands.ControlNidecMotorWithGamepad;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.NidecBrushless;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class CanControlledMotor extends Subsystem
{
	WPI_TalonSRX motor;//
	
	public CanControlledMotor()
	{
		//motor = new WPI_TalonSRX(RobotMap.canControlledMotorDeviceId);
		//motor.stopMotor();
	}
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		//setDefaultCommand(new ControlCanControlledMotorWithGamepad());
	}
	
	public void set(double speed)
	{
		motor.set(speed);
	}
	
	public void stop()
	{
		motor.stopMotor();
	}
}
