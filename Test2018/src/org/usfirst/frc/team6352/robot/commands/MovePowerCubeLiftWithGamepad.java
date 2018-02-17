package org.usfirst.frc.team6352.robot.commands;

import org.usfirst.frc.team6352.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MovePowerCubeLiftWithGamepad extends Command
{
	private String upSpeedKey = null;
	private String downSpeedKey = null;
	
	private double upSpeed;
	private double downSpeed;
	
	
	public MovePowerCubeLiftWithGamepad()
	{
		requires(Robot.powerCubeLift);
	}
	
	public MovePowerCubeLiftWithGamepad(String upSpeedKey, String downSpeedKey)
	{
		this();
		this.upSpeedKey = upSpeedKey;
		this.downSpeedKey = downSpeedKey;
	}
	
	public MovePowerCubeLiftWithGamepad(double upSpeed, double downSpeed)
	{
		this();
		this.upSpeed = upSpeed;
		this.downSpeed = downSpeed;
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		if (upSpeedKey != null)
		{
			upSpeed = SmartDashboard.getNumber(upSpeedKey, 0);
		}
		if (downSpeedKey != null)
		{
			downSpeed = SmartDashboard.getNumber(downSpeedKey, 0);
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		// TODO: Add code to read POV from gamepad and send proper speed
		Robot.powerCubeLift.set(0);
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
