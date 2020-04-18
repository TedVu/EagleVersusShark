package model.player;

import model.enumtype.TeamType;

/**
 * @author sefira
 * 
 *         class to control the activation of each team in the future this class
 *         will also control whether the player can undo
 *
 */
public class PlayerImpl implements Player {

	private boolean isActive;
	private TeamType playerType;

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
