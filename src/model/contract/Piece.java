
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
	 * @return the mode usage number of the piece
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
	 * @return whether the piece is immune or not
	 */
	public boolean isImmune();

	/**
	 * @return the cells that could be affected by the mode use
	 */
	public Set<Cell> modeCells();

	/**
	 * increment the mode usage of the piece
	 */
	public void modeUsed();

	/**
	 * move the position of the piece
	 * 
	 * @param x the new x position
	 * @param y the new y position
	 */
	public void movePiece(int x, int y);

	/**
	 * @return a memento obj of the piece
	 */
	public PieceMemento pieceMemento();

	/**
	 * @param isActive - set the piece to active or not
	 */
	public void setActive(boolean isActive);

	/**
	 * set immunity of the piece
	 */
	public void setImmune(boolean isImmune);

	/**
	 * increment the mode count of the piece
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
	 * executes individual abilities of the pieces
	 */
	public void useAbility(PieceAbility pieceAbility, Piece piece, Piece affectedPiece);

}
