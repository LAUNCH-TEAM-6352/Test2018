package org.usfirst.frc.team6352.robot.subsystems;

import org.usfirst.frc.team6352.robot.OI;
import org.usfirst.frc.team6352.robot.RobotMap;
import org.usfirst.frc.team6352.robot.commands.MovePowerCubeLiftWithGamepadPov;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The subsystem that lifts the power cube.
 */
public class PowerCubeLift extends Subsystem
{
	SpeedController motor;
	
	public PowerCubeLift()
	{
		motor = RobotMap.isCompetitionRobot
				? new Spark(RobotMap.powerCubeLiftPwmChannel)
				: new WPI_TalonSRX(RobotMap.canControlledMotorDeviceId);
	}

	// Stop the motor
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
		setDefaultCommand(new MovePowerCubeLiftWithGamepadPov(OI.dashboardPowerCubeLiftUpFastSpeed, OI.dashboardPowerCubeLiftUpSlowSpeed, OI.dashboardPowerCubeLiftDownFastSpeed, OI.dashboardPowerCubeLiftDownSlowSpeed));
	}
}
