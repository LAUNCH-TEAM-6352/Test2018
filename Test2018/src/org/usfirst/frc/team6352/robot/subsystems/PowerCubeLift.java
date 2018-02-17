package org.usfirst.frc.team6352.robot.subsystems;

import org.usfirst.frc.team6352.robot.RobotMap;
import org.usfirst.frc.team6352.robot.commands.DriveWithGamepadController;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The subsystem that lifts the power cube.
 */
public class PowerCubeLift extends Subsystem
{
	SpeedController motor;
	
	public PowerCubeLift()
	{
		if (RobotMap.isCompetitionRobot)
		{
			motor = new Spark(0);
		}
		else
		{
			motor = new Spark(0);
		}
	}

	// Stop rhe motor
	public void stop()
	{
		motor.stopMotor();
	}
	
	// Move lift up
	public void up()
	{
		motor.set(1);
	}
	
	// Move lift down
	public void down()
	{
		motor.set(-1);
	}
	
	public void set(double speed)
	{
		motor.set(speed);
	}
	
	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		//setDefaultCommand(new DoSomething());
	}
}
