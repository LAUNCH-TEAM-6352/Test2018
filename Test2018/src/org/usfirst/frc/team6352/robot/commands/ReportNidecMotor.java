package org.usfirst.frc.team6352.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team6352.robot.Robot;

/**
 * Reports the status of the Nidec motor.
 */
public class ReportNidecMotor extends Command
{
	public ReportNidecMotor()
	{
		// Use requires() here to declare subsystem dependencies
		requires(Robot.nidecMotorReporter);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		Robot.nidecMotorReporter.report();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end()
	{
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
		end();
	}
}
