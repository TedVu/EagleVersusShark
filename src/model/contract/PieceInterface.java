
package model.contract;

import java.util.Map;
import java.util.Set;

import model.board.Cell;

/**
 * @author sefira & chanboth
 *
 */
public interface PieceInterface {

	/**
	 * Get the current position of the piece
	 * 
	 * @return
	 */
	public Map<String, Integer> getPosition();

	/**
	 * @return
	 */
	public Set<Cell> getValidMove();

	/**
	 * @return
	 */
	public boolean isActive();

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public void movePiece(int x, int y);

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
