
package model.contract;

import java.util.Map;
import java.util.Set;

import model.board.Cell;
import model.enumtype.PieceAbility;
import model.piece.PieceMemento;

/**
 * @author sefira & chanboth
 *
 */
public interface Piece {

	/**
	 * Get the current position of the piece
	 * 
	 * @return {"x": .. , "y": ..}
	 */
	public Map<String, Integer> getPosition();

	/**
	 * @return the set of valid coordinates
	 */
	public Set<Cell> getValidMove();

	/**
	 * @return whether the piece is active or not
	 */
	public boolean isActive();

	/**
	 * @param x the new x position
	 * @param y the new y position
	 */
	public void movePiece(int x, int y);

	/**
	 * @param isActive - set the piece to active or not
	 */
	public void setActive(boolean isActive);

	/**
	 * Modify the position of the piece
	 * 
	 * @param x - new x position
	 * @param y - new y position
	 */
	public void setPosition(int x, int y);

	/**
	 * @param pieceAbility
	 * @param piece
	 * @param affectedPiece
	 */
	public void useAbility(PieceAbility pieceAbility, Piece piece, Piece affectedPiece);

	/**
	 * @return
	 */
	public Set<Cell> abilityCells();

	/**
	 * @param isImmune
	 */
	public void setImmune(boolean isImmune);

	/**
	 * @return
	 */
	public boolean isImmune();

	/**
	 * @return
	 */
	public PieceMemento pieceMemento();

	/**
	 * @return
	 */
	public Set<Cell> modeCells();

	/**
	 * 
	 */
	public void modeUsed();

	/**
	 * @param modeCount
	 */
	public void setModeCount(int modeCount);

	/**
	 * @return
	 */
	public int getModeCount();

}
