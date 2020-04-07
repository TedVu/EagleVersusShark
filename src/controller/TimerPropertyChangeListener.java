package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import view.operationview.BoardPanel;

public class TimerPropertyChangeListener implements PropertyChangeListener {

	private BoardPanel boardView;

	public void injectBoard(BoardPanel boardView) {
		this.boardView = boardView;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equalsIgnoreCase("switchturn")) {
			boardView.updateBoardEndOfTimer();
		}

	}

}
