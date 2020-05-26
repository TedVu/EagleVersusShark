
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
	 * @return
	 */
	public Set<Cell> abilityCells();

	/**
	 * @return
	 */
	public int getModeCount();

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
	 * @return
	 */
	public boolean isImmune();

	/**
	 * @return
	 */
	public Set<Cell> modeCells();

	/**
	 * 
	 */
	public void modeUsed();

	/**
	 * @param x the new x position
	 * @param y the new y position
	 */
	public void movePiece(int x, int y);

	/**
	 * @return
	 */
	public PieceMemento pieceMemento();

	/**
	 * @param isActive - set the piece to active or not
	 */
	public void setActive(boolean isActive);

	/**
	 * @param isImmune
	 */
	public void setImmune(boolean isImmune);

	/**
	 * @param modeCount
	 */
	public void setModeCount(int modeCount);

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

}
