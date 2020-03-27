package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import javax.swing.AbstractButton;

import models.engine.EngineImpl;
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

		// register a propertyChangeListener
		pcs.addPropertyChangeListener("MovePiece", new MovePieceController());
		this.boardView = boardView;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		List<List<AbstractButton>> buttons = boardView.getButtonList();

		// To check the case when user already select a piece then choose another piece,
		// to identify if already selected piece or not, based on color of cell (this
		// may
		// get changed)
		// in later release when taking shark into account
		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(0).size(); ++col) {
				if (EngineImpl.getSingletonInstance().checkSelectPiece(button.getActionCommand())
						&& buttons.get(row).get(col).getBackground().equals(Color.YELLOW)) {
					rollbackSelectedPieceStatus();
				}
			}
		}

		if (EngineImpl.getSingletonInstance().checkSelectPiece(button.getActionCommand())) {
			// old value = button clicked but on gettting the action command
			// new value = board
			pcs.firePropertyChange("MovePiece", button.getActionCommand(), boardView);
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
				if (buttons.get(row).get(col).getBackground().equals(Color.YELLOW)) {
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
