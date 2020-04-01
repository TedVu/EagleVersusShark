package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import view.operationview.BoardPanel;

public class TimerPropertyChangeListener implements PropertyChangeListener {

	private BoardPanel boardView;

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getPropertyName().equalsIgnoreCase("switchturn")) {
			boardView.updateBoardEndOfTimer();
		}

	}

	public void injectBoard(BoardPanel boardView) {
		this.boardView = boardView;
	}

}
