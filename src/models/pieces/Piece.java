package models.pieces;

import java.util.Map;
import java.util.UUID;

public interface Piece {

	public UUID getId();
	public boolean isActive();
	
	public void setActive(boolean isActive);

	public void setPosition(int x, int y);
	public Map<String, Integer> getPosition();
	public Map<String, Integer> move(int x, int y);
	
	public String getType();
	
}
