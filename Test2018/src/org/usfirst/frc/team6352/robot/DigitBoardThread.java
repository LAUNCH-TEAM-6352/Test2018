package org.usfirst.frc.team6352.robot;

//import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.hal.PowerJNI;

public class DigitBoardThread extends Thread {
	private REVDigitBoard digitBoard;
	
	public DigitBoardThread() {
		digitBoard = new REVDigitBoard();
	}

	@Override
	public  void run() {
		while (true) {
			//digitBoard.display(RobotController.getBatteryVoltage());
			//digitBoard.display(PowerJNI.getVinVoltage());
			digitBoard.display(digitBoard.getPot());
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		}
	}
}
