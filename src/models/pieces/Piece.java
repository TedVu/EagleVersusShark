
package models.pieces;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Piece {

	public void setPosition(int x, int y);

	public Map<String, Integer> getPosition();

	public void setActive(boolean isActive);

	public boolean isActive();

	public boolean movePiece(int newX, int newY);

	// Ted
	// the order matters for coordinate (hence use a list), convention is (x,y)
	// NOTE: this is a little bit confused when plugging into the board which is
	// [row][col]=[y][x]
	public Set<List<Integer>> getValidMove();
}
