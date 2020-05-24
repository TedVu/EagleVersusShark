
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

	public void useAbility(PieceAbility pieceAbility, Piece piece, Piece affectedPiece);

	public Set<Cell> abilityCells();

	public void setImmune(boolean isImmune);

	public boolean isImmune();

	public PieceMemento pieceMemento();

	public Set<Cell> modeCells();

	public void modeUsed();

	public void setModeCount(int modeCount);

	public int getModeCount();

}
