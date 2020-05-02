package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.google.java.contract.Requires;

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
	@Requires("boardView != null")
	public void injectBoard(BoardPanel boardView) {
		this.boardView = boardView;
	}

	/**
	 *
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equalsIgnoreCase("SwitchTurn")) {
			boardView.refreshBoardColorAndState();
		}
	}
}
