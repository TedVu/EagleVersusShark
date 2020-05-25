package view.contract;

import java.beans.PropertyChangeListener;

import model.enumtype.TeamType;

/**
 * @author ted &#38; kevin
 */
public interface GameEngineCallbackInterface {

	/**
	 * Attach property change listener to listener for any event triggered
	 * 
	 * @param listener
	 */
	public void addProperytChangeListener(PropertyChangeListener listener);

	/**
	 * Get all the property
	 * 
	 * @return PropertyChangeListener[]
	 */
	public PropertyChangeListener[] getPropertyChangeListener();

	/**
	 * Will fire property change after making move
	 * 
	 * @param currentPlayerTurn
	 * @return
	 */
	public void nextMove(TeamType currentPlayerTurn);

	/**
	 * Will fire property change after timer goes off
	 * 
	 * @param playerType
	 * @param currentPlayerTurn
	 * @return
	 */
	public void timerNextMove(TeamType playerType, TeamType currentPlayerTurn);

	/**
	 * Will fire property change if winning condition is satisfied
	 * 
	 * @param teamWin
	 */
	public void endGame(TeamType teamWin);
}
