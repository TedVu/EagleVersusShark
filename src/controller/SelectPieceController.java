package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import javax.swing.AbstractButton;

import models.engine.EngineImpl;
import view.operationview.BoardPanel;

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
	public void actionPerformed(ActionEvent arg0) {
		List<List<AbstractButton>> buttons = boardView.getButtonList();

		for (int i = 0; i < buttons.size(); ++i) {
			for (int j = 0; j < buttons.get(0).size(); ++j) {
				if (EngineImpl.getSingletonInstance().checkSelectPiece(button.getActionCommand())
						&& buttons.get(i).get(j).getBackground().equals(Color.YELLOW)) {
					rollbackSelectedPieceStatus();
				}
			}
		}

		if (EngineImpl.getSingletonInstance().checkSelectPiece(button.getActionCommand())) {
			// old value is selected piece new value is about to select cell
			pcs.firePropertyChange("MovePiece", button.getActionCommand(), boardView);
		}
	}

	private void rollbackSelectedPieceStatus() {
		List<List<AbstractButton>> buttons = boardView.getButtonList();

		for (int i = 0; i < buttons.size(); ++i) {
			for (int j = 0; j < buttons.get(0).size(); ++j) {
				if (buttons.get(i).get(j).getBackground().equals(Color.YELLOW)) {
					buttons.get(i).get(j).setBackground(Color.WHITE);
					ActionListener[] movePieceListener = buttons.get(i).get(j).getActionListeners();
					buttons.get(i).get(j).removeActionListener(movePieceListener[0]);

					// add new listener
					buttons.get(i).get(j)
							.addActionListener(new SelectPieceController(buttons.get(i).get(j), boardView));
				}
			}
		}
	}

}
