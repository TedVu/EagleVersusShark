package models.pieces;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import models.engine.EngineImpl;

public abstract class AbstractPieceMove extends AbstractPiece{

	protected int moveDistance;

	public AbstractPieceMove(int x, int y) {
		super(x, y);
		moveDistance = getDistance(this);
	}

	@Override
	public Set<List<Integer>> getValidMove(Piece piece) {
		Map<String, Integer> currentPosition = getPosition();
		int currentX = currentPosition.get("x");
		int currentY = currentPosition.get("y");
		Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
		validMoves.addAll(validMovesSouth(currentX, currentY, moveDistance));
		validMoves.addAll(validMovesNorth(currentX, currentY, moveDistance));
		validMoves.addAll(validMovesEast(currentX, currentY, moveDistance));
		validMoves.addAll(validMovesWest(currentX, currentY, moveDistance));
		return validMoves;
	}

	protected Set<List<Integer>> validMovesSouth(int x, int y, int cells) {
		Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
		for (int i = 1; i <= cells; i++) {
			List<Integer> validMove = new LinkedList<Integer>();
			if (y + i < EngineImpl.getSingletonInstance().getBoard().getRow()) {
				validMove.add(x);
				validMove.add(y + i);
				validMoves.add(validMove);
			} else {
				break;
			}
		}
		return validMoves;
	}

	protected Set<List<Integer>> validMovesNorth(int x, int y, int cells) {
		Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
		for (int i = 1; i <= cells; i++) {
			List<Integer> validMove = new LinkedList<Integer>();
			if (y - i >= 0) {
				validMove.add(x);
				validMove.add(y - i);
				validMoves.add(validMove);
			} else {
				break;
			}
		}

		return validMoves;
	}

	protected Set<List<Integer>> validMovesEast(int x, int y, int cells) {
		Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
		for (int i = 1; i <= cells; i++) {
			List<Integer> validMove = new LinkedList<Integer>();
			if (x + i < EngineImpl.getSingletonInstance().getBoard().getCol()) {
				validMove.add(x + i);
				validMove.add(y);
				validMoves.add(validMove);
			} else {
				break;
			}
		}

		return validMoves;
	}

	protected Set<List<Integer>> validMovesWest(int x, int y, int cells) {
		Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
		for (int i = 1; i <= cells; i++) {
			List<Integer> validMove = new LinkedList<Integer>();
			if (x - i >= 0) {
				validMove.add(x - i);
				validMove.add(y);
				validMoves.add(validMove);
			} else {
				break;
			}
		}

		return validMoves;
	}
	
	private int getDistance(Piece piece) {
		
		if(piece instanceof AggressiveShark || piece instanceof AttackerEagle || piece instanceof HealingShark) {
			return 1;
		}
			
		else if(piece instanceof LeadershipEagle || piece instanceof DefensiveShark || piece instanceof VisionaryEagle) {
			return 2;
		}
		else {
			throw new IllegalArgumentException("invalid piece type");
		}
		
	}

}