package view.messagedialog;

import javax.swing.JOptionPane;

import view.operationview.BoardPanel;

/**
 * @author Ted
 * @implNote Pure Fabrication class for logging message panel
 *	
 */
public class MessageDialog {

	public static void notifyStartGame(BoardPanel boardView) {
		JOptionPane.showMessageDialog(boardView, "You have not start the game yet");

	}

	public static void notifySelectWrongTeam(BoardPanel boardView) {
		JOptionPane.showMessageDialog(boardView, "You are selecting the wrong team");

	}
}
