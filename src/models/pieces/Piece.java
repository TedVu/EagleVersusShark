package models.pieces;

import java.util.Map;
import java.util.UUID;

public interface Piece {

	public void setPosition(int x, int y);

	public Map<String, Integer> getPosition();

	public void setActive(boolean isActive);

	public boolean isActive();

	public boolean movePiece(int newX, int newY);

}
