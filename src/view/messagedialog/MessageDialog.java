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
		JOptionPane.showMessageDialog(boardView, "You have not started the game yet");
	}

	/**
	 * @param boardView
	 */
	public static void notifySelectWrongTeam(BoardPanel boardView) {
		JOptionPane.showMessageDialog(boardView, "You are selecting the wrong team");
	}

	public static void notifyCannotUseAbilityLeadershipNearby(BoardPanel boardView) {
		JOptionPane.showMessageDialog(boardView, "Cannot use ability because there is no allies eagle around");
	}

	public static void notifyProtectSuccessLeadership(BoardPanel boardView) {
		JOptionPane.showMessageDialog(boardView, "Leadership give protection successfull");
	}

	public static void notifyNoPieceNearbyToCapture(BoardPanel boardView) {
		JOptionPane.showMessageDialog(boardView, "No shark nearby to capture");
	}

	public static void notifyFailToCaptureAttacking(BoardPanel boardView) {
		JOptionPane.showMessageDialog(boardView, "Shark is immune you cannot capture");
	}

	public static void notifyUndoSuccessful(BoardPanel boardView) {
		JOptionPane.showMessageDialog(boardView, "Undo successful !!! please resume game");

	}

}
