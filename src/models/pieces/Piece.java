package models.pieces;

import java.util.Map;
import java.util.UUID;

public interface Piece {
	
	public void setPosition(int x, int y);
	public Map<String, Integer> getPosition();
	public void setActive(boolean isActive);
	public Map<String, Integer> move(int x, int y);
	public boolean isActive();
	public UUID getId();

}
