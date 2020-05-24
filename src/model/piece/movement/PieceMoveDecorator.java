package model.piece.movement;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import model.board.Cell;
import model.contract.Piece;
import model.contract.PieceMovement;
import model.engine.EngineImpl;

/**
 * @author sefira & kevin
 *
 */

public class PieceMoveDecorator implements PieceMovement {

	protected PieceMovement pieceMove;

	public PieceMoveDecorator(PieceMovement pieceMove) {
		this.pieceMove = pieceMove;
	}

	/*
	 * @param piece - selected piece
	 * 
	 * @param distance - piece moving distance
	 * 
	 * @return the set of valid coordinate
	 */
//	@Requires({ "piece != null", "distance == 1 || distance ==2" })
//	@Ensures("validMoves != null")
	@Override
	public Set<Cell> getValidMove(Piece piece, int distance) {
		return pieceMove.getValidMove(piece, distance);
	}

}
