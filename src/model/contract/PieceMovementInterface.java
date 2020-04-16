package model.contract;

import java.util.Set;

/**
 * @author sefira 
 *
 */

import model.board.Cell;

public interface PieceMovementInterface {
	
	/*
	 * @param piece - selected piece
	 * @param distance - piece moving distance
	 * @return the set of valid coordinate
	 */
	public Set<Cell> getValidMove(PieceInterface piece, int distance);

}
