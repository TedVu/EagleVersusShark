package view.contract;

import java.beans.PropertyChangeListener;

import model.enumtype.TeamType;

/**
 * @author ted &#38; kevin
 */
public interface GameEngineCallbackInterface {
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

	public void endGame(TeamType teamWin);
}
