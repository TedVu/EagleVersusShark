package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import models.engine.EngineImpl;
import view.operationview.BoardPanel;

/**
 * @author ted &#38; kevin
 *
 */
public class TimerPropertyChangeListener implements PropertyChangeListener {

	private BoardPanel boardView;

	/**
	 * @param boardView
	 */
	public void injectBoard(BoardPanel boardView) {
		this.boardView = boardView;
	}

	/**
	 *
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equalsIgnoreCase("SwitchTurn")) {
			boardView.updateBoardEndOfTimer(EngineImpl.getSingletonInstance().getBoard().getSize());
		}
	}
}
