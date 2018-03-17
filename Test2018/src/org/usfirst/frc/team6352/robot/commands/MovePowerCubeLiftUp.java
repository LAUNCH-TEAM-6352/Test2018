package org.usfirst.frc.team6352.robot.commands;

import org.usfirst.frc.team6352.robot.Robot;
import org.usfirst.frc.team6352.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A command for moving the power cube lift up.
 */
public class MovePowerCubeLiftUp extends Command
{
	private String motorSpeedKey = null;
	
	private double motorSpeed;
	
	
	public MovePowerCubeLiftUp()
	{
		requires(Robot.powerCubeLift);
	}
	
	public MovePowerCubeLiftUp(String motorSpeedKey)
	{
		this();
		this.motorSpeedKey = motorSpeedKey;
	}
	
	public MovePowerCubeLiftUp(double motorSpeed)
	{
		this();
		this.motorSpeed = motorSpeed;
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		if (motorSpeedKey != null)
		{
			motorSpeed = SmartDashboard.getNumber(motorSpeedKey, 0);
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		// This will not let us move the lift higher than the max distance:
		Robot.powerCubeLift.set(
				Robot.powerCubeLiftEncoder.getDistance() < RobotMap.liftEncoderMaxDistance ? motorSpeed : 0);
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
