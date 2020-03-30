package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

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
		this.boardView = boardView;
	}

	@Override
	public void actionPerformed(ActionEvent src) {
		// only allow button clicked after start game
		if (EngineImpl.getSingletonInstance().getStartGame()) {
			List<List<AbstractButton>> buttons = boardView.getButtonList();

			// when user already selects a piece and change his/her mind
			// allow user pick another piece by roll back action
			findColoredCellsForRollback(buttons);

			if (EngineImpl.getSingletonInstance().checkSelectPiece(button.getActionCommand())) {

				if (EngineImpl.getSingletonInstance().getCurrentActivePlayer().getPlayerType()
						.equalsIgnoreCase(Asset.sharkTeamName)) {
					checkAllowTransitToMovePieceAction(Asset.aggressiveShark, Asset.defensiveShark, Asset.healingShark);
				} else {
					checkAllowTransitToMovePieceAction(Asset.attackingEagle, Asset.leadershipEagle,
							Asset.visionaryEagle);
				}
			}
		} else {
			MessageDialog.notifyStartGame(boardView);
		}
	}

	private void checkAllowTransitToMovePieceAction(String pieceName1, String pieceName2, String pieceName3) {
		if (button.getActionCommand().equalsIgnoreCase(pieceName1)
				|| button.getActionCommand().equalsIgnoreCase(pieceName2)
				|| button.getActionCommand().equalsIgnoreCase(pieceName3)) {
			pcs.firePropertyChange("MovePiece", button.getActionCommand(), boardView);
		} else {
			MessageDialog.notifySelectingWrongPiece(boardView);
		}
	}

	private void findColoredCellsForRollback(List<List<AbstractButton>> buttons) {
		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(0).size(); ++col) {
				if (EngineImpl.getSingletonInstance().checkSelectPiece(button.getActionCommand())
						&& (buttons.get(row).get(col).getBackground().equals(Color.YELLOW)
								|| buttons.get(row).get(col).getBackground().equals(Color.BLUE))) {
					rollbackSelectedPieceStatus();
				}
			}
		}
	}

	/**
	 * This method handles the special case of selected piece
	 * 
	 * @see actionPerform() above
	 */
	private void rollbackSelectedPieceStatus() {
		List<List<AbstractButton>> buttons = boardView.getButtonList();

		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(0).size(); ++col) {
				// yellow cell = cells previous select piece can move to
				// find all the yellow cell and turn into normal cell since we select another
				// piece to make a move
				if (buttons.get(row).get(col).getBackground().equals(Color.YELLOW)
						|| buttons.get(row).get(col).getBackground().equals(Color.BLUE)) {
					buttons.get(row).get(col).setBackground(Color.WHITE);
					// currently acttach MovePieceController from previous select piece
					// these line remove that controllers
					// Question : is there any other more "clean" way to remove controller ?
					ActionListener[] movePieceListener = buttons.get(row).get(col).getActionListeners();
					buttons.get(row).get(col).removeActionListener(movePieceListener[0]);
					// add a brand new selectPieceController
					buttons.get(row).get(col)
							.addActionListener(new SelectPieceController(buttons.get(row).get(col), boardView));
				}
			}
		}
	}

}
