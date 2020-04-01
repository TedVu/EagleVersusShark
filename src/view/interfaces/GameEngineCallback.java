package view.interfaces;

import java.beans.PropertyChangeListener;

public interface GameEngineCallback {

	/**
	 * Will fire property change after timer goes off
	 * 
	 * @param playerType
	 * @param currentPlayerTurn
	 */
	public void timerNextMove(String playerType, String currentPlayerTurn);

	/**
	 * Will fire property change after making move
	 * 
	 * @param currentPlayerTurn
	 */
	public void nextMove(String currentPlayerTurn);

	public void addProperytChangeListener(PropertyChangeListener listener);

	public PropertyChangeListener[] getPropertyChangeListener();

}
