package models.pieces;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import models.engine.EngineImpl;

public abstract class AbstractPieceMoveDiagonal extends AbstractPieceMove {

	public AbstractPieceMoveDiagonal(int x, int y) {
		super(x, y);
		moveDistance =  getDistance(this);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Set<List<Integer>> getValidMove() {

		Map<String, Integer> currentPosition = getPosition();
		int currentX = currentPosition.get("x");
		int currentY = currentPosition.get("y");
		Set<List<Integer>> validMoves = super.getValidMove();
		validMoves.addAll(validDiaNorthEast(currentX, currentY, moveDistance));
		validMoves.addAll(validDiaSouthWest(currentX, currentY, moveDistance));
		validMoves.addAll(validDiaSouthEast(currentX, currentY, moveDistance));
		validMoves.addAll(validDiaNorthWest(currentX, currentY, moveDistance));
		return validMoves;
	}

	public Set<List<Integer>> validDiaNorthEast(int x, int y, int cells) {
		Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
		for (int i = 1; i <= cells; i++) {
			List<Integer> validMove = new LinkedList<Integer>();
			if (x + i < EngineImpl.getSingletonInstance().getBoard().getSize() && y - i >= 0) {
				validMove.add(x + i);
				validMove.add(y - i);
				validMoves.add(validMove);
			} else {
				break;
			}
		}

		return validMoves;
	}

	public Set<List<Integer>> validDiaSouthWest(int x, int y, int cells) {
		Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
		for (int i = 1; i <= cells; i++) {
			List<Integer> validMove = new LinkedList<Integer>();
			if (y + i < EngineImpl.getSingletonInstance().getBoard().getSize() && x - i >= 0) {
				validMove.add(x - i);
				validMove.add(y + i);
				validMoves.add(validMove);
			} else {
				break;
			}
		}

		return validMoves;
	}

	public Set<List<Integer>> validDiaSouthEast(int x, int y, int cells) {
		Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
		for (int i = 1; i <= cells; i++) {
			List<Integer> validMove = new LinkedList<Integer>();
			if (x + i < EngineImpl.getSingletonInstance().getBoard().getSize()
					&& y + i < EngineImpl.getSingletonInstance().getBoard().getSize()) {
				validMove.add(x + i);
				validMove.add(y + i);
				validMoves.add(validMove);
			} else {
				break;
			}
		}

		return validMoves;
	}

	public Set<List<Integer>> validDiaNorthWest(int x, int y, int cells) {
		Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
		for (int i = 1; i <= cells; i++) {
			List<Integer> validMove = new LinkedList<Integer>();
			if (x - i >= 0 && y - i >= 0) {
				validMove.add(x - i);
				validMove.add(y - i);
				validMoves.add(validMove);
			} else {
				break;
			}
		}

		return validMoves;
	}
	
	
	private int getDistance(Piece piece) {
		
		if(piece instanceof AggressiveShark || piece instanceof AttackingEagle || piece instanceof HealingShark) {
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
