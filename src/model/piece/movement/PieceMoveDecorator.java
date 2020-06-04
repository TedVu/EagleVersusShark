package model.piece.movement;

import java.util.Set;

import model.board.Cell;
import model.contract.Piece;
import model.contract.PieceMovement;

/**
 * @author sefira & kevin
 *
 */

public class PieceMoveDecorator implements PieceMovement {

	protected PieceMovement pieceMove;

	public PieceMoveDecorator(PieceMovement pieceMove) {
		this.pieceMove = pieceMove;
	}

	/**
	 * @param piece    - selected piece
	 * @param distance - piece moving distance
	 * 
	 * @return the set of valid coordinate
	 */

	@Override
	public Set<Cell> getValidMove(Piece piece, int distance) {
		return pieceMove.getValidMove(piece, distance);
	}

}
