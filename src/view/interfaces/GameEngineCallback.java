package view.interfaces;

import java.beans.PropertyChangeListener;

/**
 * @author kevin & ted
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
	public void nextMove(String currentPlayerTurn);

	/**
	 * Will fire property change after timer goes off
	 * 
	 * @param playerType
	 * @param currentPlayerTurn
	 * @return
	 */
	public void timerNextMove(String playerType, String currentPlayerTurn);
}
