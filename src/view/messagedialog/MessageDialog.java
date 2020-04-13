package view.messagedialog;

import javax.swing.JOptionPane;

import view.operationview.BoardPanel;

/**
 * <b>Note:</b> Pure Fabrication class for logging message panel
 * 
 * @author ted &#38; kevin
 * 
 */
public class MessageDialog {
	/**
	 * @param boardView
	 */
	public static void notifyNotStartGame(BoardPanel boardView) {
		JOptionPane.showMessageDialog(boardView, "You have not start the game yet");
	}

	/**
	 * @param boardView
	 */
	public static void notifySelectWrongTeam(BoardPanel boardView) {
		JOptionPane.showMessageDialog(boardView, "You are selecting the wrong team");
	}
}
