package view.messagedialog;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import view.operationview.BoardPanel;

/**
 * <b>Note:</b> Pure Fabrication class for logging message panel
 * 
 * @author Ted
 * 
 */
public class MessageDialog {

	public static void notifyInputFileNotExist(JFrame startFrame) {
		JOptionPane.showMessageDialog(startFrame, "File request not exist in system");
	}

	public static void notifyInputWrongFileFormat(JFrame startFrame) {
		JOptionPane.showMessageDialog(startFrame, "Wrong file input format please check again");
	}

	public static void notifySelectWrongTeam(BoardPanel boardView) {
		JOptionPane.showMessageDialog(boardView, "You are selecting the wrong team");
	}

	public static void notifyStartGame(BoardPanel boardView) {
		JOptionPane.showMessageDialog(boardView, "You have not start the game yet");

	}
}
