package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import view.operationview.BoardPanel;

/**
 * 
 * @author kevin & ted
 * 
 */
public class TimerPropertyChangeListener implements PropertyChangeListener {

	private BoardPanel boardView;

	/**
	 * 
	 * @see
	 * 
	 */
	public void injectBoard(BoardPanel boardView) {
		this.boardView = boardView;
	}

	/**
	 * 
	 * @see
	 * 
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equalsIgnoreCase("switchturn")) {
			boardView.updateBoardEndOfTimer();
		}

	}

}
