package model.piece.movement;

import java.util.Set;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import model.board.Cell;
import model.contract.PieceInterface;

/**
 * @author sefira
 *
 */

public class BasicMove extends PieceMoveImpl {

	/*
	 * @param piece - selected piece
	 * 
	 * @param distance - piece moving distance
	 * 
	 * @return the set of valid coordinate
	 */
	@Requires({"piece.getPosition().get(\"x\") != null && piece.getPosition().get(\"y\") != null"})
	@Ensures("piece.getValidMove() != null")
	@Override
	public Set<Cell> getValidMove(PieceInterface piece, int distance) {
		return super.getValidMove(piece, distance);
	}

}
