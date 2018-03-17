/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6352.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.io.Console;

import org.usfirst.frc.team6352.robot.commands.ControlNidecMotorWithGamepad;
import org.usfirst.frc.team6352.robot.commands.DriveAutonomousSimple;
import org.usfirst.frc.team6352.robot.subsystems.CanControlledMotor;
import org.usfirst.frc.team6352.robot.subsystems.DriveTrain;
import org.usfirst.frc.team6352.robot.subsystems.NidecMotor;
import org.usfirst.frc.team6352.robot.subsystems.NidecMotorReporter;
import org.usfirst.frc.team6352.robot.subsystems.PowerCubeIntake;
import org.usfirst.frc.team6352.robot.subsystems.PowerCubeLift;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot
{
	//public static final NidecMotor nidecMotor = new NidecMotor();
	//public static final NidecMotorReporter nidecMotorReporter = new NidecMotorReporter();
	public static final NidecMotor nidecMotor = null;
	public static final NidecMotorReporter nidecMotorReporter = null;
	
	//public static final CanControlledMotor canControlledMotor = new CanControlledMotor();
	public static final CanControlledMotor canControlledMotor = null;
	
	public static final DriveTrain driveTrain = new DriveTrain();
	//public static final DriveTrain driveTrain = null;
	
	//public static final PowerCubeIntake powerCubeIntake = new PowerCubeIntake();
	public static final PowerCubeIntake powerCubeIntake = null;
	
	public static final PowerCubeLift powerCubeLift = new PowerCubeLift();
	//public static final PowerCubeLift powerCubeLift = null;
	
	public static OI oi;

	private REVDigitBoard digitBoard;

	private int voltageRefreshCounter = 0;
	private static final int voltageRefreshCount = 10;

	private int potRefreshCounter = 0;
	private static final int potRefreshCount = 10;

	private boolean buttonA = false;
	private boolean buttonB = false;

	private static final String[] options = { "LEFT", "RGHT", "SMPL" };
	private int optionIndex = 0;

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	// I2C address of the digit board is 0x70
	// I2C i2c = new I2C(Port.kMXP, 0x70);

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit()
	{
		oi = new OI();
		//m_chooser.addDefault("Default Auto", new ControlNidecMotorWithGamepad());
		// chooser.addObject("My Auto", new MyAutoCommand());
		//SmartDashboard.putData("Auto mode", m_chooser);

		//scanMXPI2C();

		digitBoard = new REVDigitBoard();
		// new DigitBoardThread().start();

		/***
		 * // set up the board - turn on, set blinking and brightness byte[] osc = new
		 * byte[1]; byte[] blink = new byte[1]; byte[] bright = new byte[1]; osc[0] =
		 * (byte)0x21; blink[0] = (byte)0x81; bright[0] = (byte)0xEF;
		 * 
		 * i2c.writeBulk(osc); Timer.delay(.01); i2c.writeBulk(bright);
		 * Timer.delay(.01); i2c.writeBulk(blink); Timer.delay(.01);
		 ***/

	}

	private void scanMXPI2C()
	{
		System.out.println("MXP I2C Scan: Begin");
		for (int addr = 0x08; addr <= 0x77; addr++)
		{
			I2C i2c = new I2C(Port.kMXP, addr);
			if (!i2c.addressOnly())
			{
				System.out.printf("Found I2C at 0x%h\n", addr);
			}
			i2c.free();
		}
		System.out.println("MXP I2C Scan: End");
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit()
	{

	}

	@Override
	public void disabledPeriodic()
	{
		Scheduler.getInstance().run();
	}

	@Override
	public void robotPeriodic()
	{
		if (digitBoard.getButtonA() != buttonA)
		{
			buttonA = !buttonA;
			if (buttonA)
			{
				if (++optionIndex >= options.length)
				{
					optionIndex = 0;
				}
			}
		}

		if (digitBoard.getButtonB() != buttonB)
		{
			buttonB = !buttonB;
		}

		if (buttonA)
		{
			digitBoard.display(options[optionIndex]);
		} else if (buttonB)
		{
			if (++potRefreshCounter > potRefreshCount)
			{
				potRefreshCounter = 0;
			}
			if (potRefreshCounter == 0)
			{
				digitBoard.display(digitBoard.getPot());
			}
		} else
		{
			if (++voltageRefreshCounter > voltageRefreshCount)
			{
				voltageRefreshCounter = 0;
			}
			if (voltageRefreshCounter == 0)
			{
				digitBoard.display(RobotController.getBatteryVoltage());
			}
		}
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit()
	{
		System.out.printf("   Option: %s\n", options[optionIndex]);
		System.out.printf("Game Data: %s\n", DriverStation.getInstance().getGameSpecificMessage());
		System.out.flush();

		m_autonomousCommand = m_chooser.getSelected();
		
		m_autonomousCommand = new DriveAutonomousSimple(
				OI.dashboardSimpleAutoDriveSpeed,
				OI.dashboardSimpleAutoDriveCurve,
				OI.dashboardSimpleAutoDriveTimeout);

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		 * switch(autoSelected) { case "My Auto": autonomousCommand = new
		 * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
		 * ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null)
		{
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit()
	{
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null)
		{
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic()
	{
	}
}
