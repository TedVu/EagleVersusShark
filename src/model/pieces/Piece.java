
package model.pieces;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author sefira & chanboth
 *
 */
public interface Piece {

	/**
	 * Get the current position of the piece
	 * 
	 * @return
	 */
	public Map<String, Integer> getPosition();

	/**
	 * @return
	 */
	public Set<List<Integer>> getValidMove();

	/**
	 * @return
	 */
	public boolean isActive();

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean movePiece(int x, int y);

	/**
	 * @param isActive
	 */
	public void setActive(boolean isActive);

	/**
	 * Modify the position of the piece
	 * 
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y);
}
