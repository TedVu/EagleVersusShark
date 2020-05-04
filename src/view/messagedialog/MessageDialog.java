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
	public static void notifyGameNotRunning(BoardPanel boardView) {
		JOptionPane.showMessageDialog(boardView, "Game is not started or is paused");
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

	public static void notifyProtectSuccess(BoardPanel boardView) {
		JOptionPane.showMessageDialog(boardView, "Give protection successful");
	}

	public static void notifyNoPieceNearbyToCapture(BoardPanel boardView) {
		JOptionPane.showMessageDialog(boardView, "No enemy nearby to capture");
	}

	public static void notifyFailToCaptureAttacking(BoardPanel boardView) {
		JOptionPane.showMessageDialog(boardView, "This piece is immune you cannot capture");
	}

	public static void notifyUndoSuccessful(BoardPanel boardView) {
		JOptionPane.showMessageDialog(boardView, "Undo successful !!! please resume game");
	}

	public static void notifyUndoFail(BoardPanel boardView, String failMsg) {
		JOptionPane.showMessageDialog(boardView, failMsg);
	}

	public static void notifyNoSharkToRevive(BoardPanel boardView) {
		JOptionPane.showMessageDialog(boardView, "No shark to revive");
	}

	public static void notifyReviveSuccessful(BoardPanel boardView) {
		JOptionPane.showMessageDialog(boardView, "Revive successful");
	}

	public static void notifyNotCorrectTurnToRevive(BoardPanel boardView) {
		JOptionPane.showMessageDialog(boardView, "Eagle turn now, cannot revive");
	}

	public static void notifyReviveFailUsedAlready(BoardPanel boardView, String msg) {
		JOptionPane.showMessageDialog(boardView, msg);

	}

}
