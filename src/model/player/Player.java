package model.player;

import model.enumtype.TeamType;

/**
 * @author sefira
 *
 */
public interface Player {

	/**
	 * @return
	 */
	public boolean getActive();

	/**
	 * @return
	 */
	public TeamType getPlayerType();

	/**
	 * @param isActive
	 */
	public void setActive(boolean isActive);
}
