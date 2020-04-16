package model.piece.movement;

import java.util.Set;

import model.board.Cell;
import model.contract.PieceInterface;

/**
 * @author sefira 
 *
 */

public class BasicMove extends AbstractPieceMove{
	
	/*
	 * @param piece - selected piece
	 * @param distance - piece moving distance
	 * @return the set of valid coordinate
	 */
	@Override
	public Set<Cell> getValidMove(PieceInterface piece, int distance) {
		return super.getValidMove(piece, distance);
	}
	
}
