package controller.playinggamecontroller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.google.java.contract.Requires;

import model.enumtype.TeamType;
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
		} else if (evt.getPropertyName().equalsIgnoreCase("EndGame")) {
			boardView.refreshBoardColorAndState();
			String congratsMsg = null;
			TeamType teamWin = (TeamType) evt.getNewValue();
			congratsMsg = (teamWin == TeamType.EAGLE) ? "Congrats !!! Eagle wins thank you for playing"
					: " Congrats !!! Shark wins thank you for playing";
			boardView.endGame(congratsMsg);
		}
	}
}
