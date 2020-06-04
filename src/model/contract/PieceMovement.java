package model.contract;

import java.util.Set;

import com.google.java.contract.Requires;

/**
 * @author sefira 
 *
 */

import model.board.Cell;

public interface PieceMovement {

	/**
	 * @param piece
	 *            selected piece
	 * @param distance
	 *            piece moving distance
	 * 
	 * @return the set of valid coordinate
	 */
	@Requires({ "piece != null", "distance>0" })
	public Set<Cell> getValidMove(Piece piece, int distance);

}
