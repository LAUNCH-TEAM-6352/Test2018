package org.usfirst.frc.team6352.robot.commands;

import java.util.HashMap;

import org.usfirst.frc.team6352.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Used the POV control from the gamepad to control the cube lift.
 * 
 * Values from the POV represent integer degrees starting with 0 at the up direction
 * and increasing clockwise with upper-left being 315. -1 is returned if the POV is
 * not pressed.
 */
public class MovePowerCubeLiftWithGamepadPov extends Command
{
	private String upFastSpeedKey = null;
	private String upSlowSpeedKey = null;
	private String downFastSpeedKey = null;
	private String downSlowSpeedKey = null;
	
	private double upFastSpeed;
	private double upSlowSpeed;
	private double downFastSpeed;
	private double downSlowSpeed;
	
	private HashMap<Integer, Double> liftSpeeds = new HashMap<Integer, Double>();
	
	
	protected MovePowerCubeLiftWithGamepadPov()
	{
		requires(Robot.powerCubeLift);
	}
	
	public MovePowerCubeLiftWithGamepadPov(String upFastSpeedKey, String upSlowSpeedKey, String downFastSpeedKey, String downSlowSpeedKey)
	{
		this();
		this.upFastSpeedKey = upFastSpeedKey;
		this.upSlowSpeedKey = upSlowSpeedKey;
		this.downFastSpeedKey = downFastSpeedKey;
		this.downSlowSpeedKey = downSlowSpeedKey;
	}
	
	public MovePowerCubeLiftWithGamepadPov(double upFastSpeed, double upSlowSpeed, double downFastSpeed, double downSlowSpeed)
	{
		this();
		this.upFastSpeed = upFastSpeed;
		this.upSlowSpeed = upSlowSpeed;
		this.downFastSpeed = downFastSpeed;
		this.downSlowSpeed = downSlowSpeed;
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		if (upFastSpeedKey != null)
		{
			upFastSpeed = SmartDashboard.getNumber(upFastSpeedKey, 0);
		}
		if (upSlowSpeedKey != null)
		{
			upSlowSpeed = SmartDashboard.getNumber(upSlowSpeedKey, 0);
		}
		if (downFastSpeedKey != null)
		{
			downFastSpeed = SmartDashboard.getNumber(downFastSpeedKey, 0);
		}
		if (downSlowSpeedKey != null)
		{
			downSlowSpeed = SmartDashboard.getNumber(downSlowSpeedKey, 0);
		}
		
		// Initialize map of speeds (map POV value to speed):
		liftSpeeds.put(-1, 0.0);
		liftSpeeds.put(0, upFastSpeed);
		liftSpeeds.put(45, upSlowSpeed);
		liftSpeeds.put(90, 0.0);
		liftSpeeds.put(135, downSlowSpeed);
		liftSpeeds.put(180, downFastSpeed);
		liftSpeeds.put(225, downSlowSpeed);
		liftSpeeds.put(270, 0.0);
		liftSpeeds.put(315, upSlowSpeed);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		// Use value from POV to get motor speed from map:
		Robot.powerCubeLift.set(liftSpeeds.get(Robot.oi.gameController.getPOV()));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return isCanceled();
	}

	// Called once after isFinished returns true
	protected void end()
	{
		Robot.powerCubeLift.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		end();
	}
}
