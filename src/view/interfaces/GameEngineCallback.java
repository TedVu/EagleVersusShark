package view.interfaces;

import java.beans.PropertyChangeListener;

import models.enumeration.Player;

public interface GameEngineCallback {

	/**
	 * Will fire property change after timer goes off
	 * 
	 * @param playerType
	 * @param currentPlayerTurn
	 */
	public void timerNextMove(Player playerType, Player currentPlayerTurn);

	/**
	 * Will fire property change after making move
	 * 
	 * @param currentPlayerTurn
	 */
	public void nextMove(Player currentPlayerTurn);

	public void addProperytChangeListener(PropertyChangeListener listener);

	public PropertyChangeListener[] getPropertyChangeListener();

}
