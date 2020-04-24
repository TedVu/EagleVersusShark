package model.player;

import model.enumtype.TeamType;

/**
 * @author sefira
 *
 */
public interface Player {

	/**
	 * @return whether it's currently the player's turn or not
	 */
	public boolean getActive();

	/**
	 * @return the type of player (eagle | shark)
	 */
	public TeamType getPlayerType();

	/**
	 * @param isActive
	 *            - set the player active status
	 */
	public void setActive(boolean isActive);
	
	
}
