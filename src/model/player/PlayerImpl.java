package model.player;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

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
	@Requires({"this.isActive ==true || this.isActive ==false"})
	public boolean getActive() {
		return isActive;
	}

	@Override
	@Requires({"playerType != null"})
	public TeamType getPlayerType() {
		return playerType;
	}

	@Override
	@Requires({"isActive ==true || isActive ==false"})
	@Ensures({"this.isActive == isActive"})
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
