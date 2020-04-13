package view.interfaces;

import java.beans.PropertyChangeListener;

import asset.TeamType;

/**
 * @author ted &#38; kevin
 */
public interface GameEngineCallback {
	/**
	 * 
	 */
	public void addProperytChangeListener(PropertyChangeListener listener);

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
}
