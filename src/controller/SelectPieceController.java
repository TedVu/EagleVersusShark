package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeSupport;

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
		if (EngineImpl.getSingletonInstance().checkSelectPiece(button.getActionCommand())) {
			// old value is selected piece new value is about to select cell
			pcs.firePropertyChange("MovePiece", button.getActionCommand(), boardView);
		}
	}

}
