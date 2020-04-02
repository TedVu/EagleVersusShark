package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeSupport;

import javax.swing.AbstractButton;

import models.engine.EngineImpl;
import view.messagedialog.MessageDialog;
import view.operationview.BoardPanel;

/**
 * @author Ted
 * @implNote Invoked when user choose a piece before making a move.<br>
 *           NOTE: we separate into two actions Select a Piece and Make a Move
 * 
 * @see documents on ggDrive for flow of events
 */
public class SelectPieceController implements ActionListener {

	private AbstractButton button;
	private BoardPanel boardView;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public SelectPieceController(AbstractButton button, BoardPanel boardView) {
		this.button = button;
		pcs.addPropertyChangeListener("MovePiece", new MovePieceController());
		pcs.addPropertyChangeListener("RollbackSelectedPiece", new RollbackController());
		this.boardView = boardView;
	}

	@Override
	public void actionPerformed(ActionEvent src) {
		// only allow button clicked after start game
		if (EngineImpl.getSingletonInstance().getStartGame()) {

			pcs.firePropertyChange("RollbackSelectedPiece", boardView, button);

			verifyPieceSelectedAndForwardCall();
		} else {
			MessageDialog.notifyStartGame(boardView);
		}
	}

	private void verifyPieceSelectedAndForwardCall() {
		if (EngineImpl.getSingletonInstance().checkSelectPiece(button.getActionCommand())) {

			if (EngineImpl.getSingletonInstance().getCurrentActivePlayer().getPlayerType()
					.equalsIgnoreCase(AssetHelper.sharkTeamName)) {
				checkAllowTransitToMovePieceAction(AssetHelper.aggressiveShark, AssetHelper.defensiveShark, AssetHelper.healingShark);
			} else {
				checkAllowTransitToMovePieceAction(AssetHelper.attackingEagle, AssetHelper.leadershipEagle,
						AssetHelper.visionaryEagle);
			}
		}
	}

	private void checkAllowTransitToMovePieceAction(String pieceName1, String pieceName2, String pieceName3) {
		if (button.getActionCommand().equalsIgnoreCase(pieceName1)
				|| button.getActionCommand().equalsIgnoreCase(pieceName2)
				|| button.getActionCommand().equalsIgnoreCase(pieceName3)) {
			pcs.firePropertyChange("MovePiece", button.getActionCommand(), boardView);
		} else {
			MessageDialog.notifySelectWrongTeam(boardView);
		}
	}

}
