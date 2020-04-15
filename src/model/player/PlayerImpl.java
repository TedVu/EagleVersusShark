package model.player;

import asset.TeamType;

/**
 * @author sefira
 *
 */
public class PlayerImpl implements Player {

	private boolean isActive;
	private TeamType playerType;

	/**
	 * @param playerType
	 */
	public PlayerImpl(TeamType playerType) {
		this.playerType = playerType;
	}

	@Override
	public boolean getActive() {
		return isActive;
	}

	@Override
	public TeamType getPlayerType() {
		return playerType;
	}

	@Override
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
