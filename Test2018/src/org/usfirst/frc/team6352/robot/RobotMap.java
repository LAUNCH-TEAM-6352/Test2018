/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6352.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap
{
	// The following indicates if we are running the competition robot:
	public static final boolean isCompetitionRobot = false;

	// The following is used for testing the new CAN motor control library from CTR:
	public static final int canControlledMotorDeviceId = 9;

	// The following are used for testing the Nidec brushless motor:
	public static int nidecMotorPwmChannel = 9;
	public static int nidecMotorDioChannel = 9;
	public static int nidecMotorTacChannel = 8;
	public static int nidecMotorDirChannel = 7;

	// The PWM channels for the drive system:
	public static final int leftDrivePwmChannel = 0;
	public static final int rightDrivePwmChannel = 1;

	// The CAN device ids for the drive system:
	public static final int leftDriveCanDeviceId = 1;
	public static final int rightDriveCanDeviceId = 2;
	public static final int centerDriveCanDeviceId = 3;

	// The CAN device ids for the power cube intake:
	public static final int leftIntakeCanDeviceId = 4;
	public static final int rightIntakeCanDeviceId = 5;

	// The PWM channel for the power cube lift:
	public static final int powerCubeLiftPwmChannel = 8;
}
