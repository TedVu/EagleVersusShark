package models.player;

public class PlayerImpl implements Player {

	private boolean isActive;
	private String playerType;

	public PlayerImpl(String playerType) {
		this.playerType = playerType;
	}

	@Override
	public boolean getActive() {
		return isActive;
	}

	@Override
	public String getPlayerType() {
		return this.playerType;
	}

	@Override
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public void setPlayerType(String playerType) {
		this.playerType = playerType;

	}

}
