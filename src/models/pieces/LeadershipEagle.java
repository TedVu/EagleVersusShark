
package models.pieces;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import models.engine.EngineImpl;

public class LeadershipEagle extends AbstractPiece {

	private boolean nextToLeadershipEagle;

	public LeadershipEagle(int x, int y) {
		super(x, y);
	}

	/*
	 * validate the new position and set it if it's valid
	 * 
	 * @param int newX - new x position
	 * 
	 * @param int newY - new y position
	 * 
	 * @return position valid based on rule ? true : false
	 */
	@Override
	public boolean movePiece(int newX, int newY) {
		setPosition(newX, newY);
		return true;
	}


	@Override
	public Set<List<Integer>> getValidMove() {
		Map<String, Integer> currentPosition = this.getPosition();
		int currentX = currentPosition.get("x");
		int currentY = currentPosition.get("y");
		Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
		validMoves.addAll(validMovesSouth(currentX, currentY, 2));
		validMoves.addAll(validMovesNorth(currentX, currentY, 2));
		validMoves.addAll(validMovesEast(currentX, currentY, 2));
		validMoves.addAll(validMovesWest(currentX, currentY, 2));
		return validMoves;
	}

	public Set<List<Integer>> validMovesSouth(int x, int y, int cells) {
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

	public Set<List<Integer>> validMovesNorth(int x, int y, int cells) {
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

	public Set<List<Integer>> validMovesEast(int x, int y, int cells) {
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

	public Set<List<Integer>> validMovesWest(int x, int y, int cells) {
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
	
	
	// usage: piece.useAbility("protect", piece, affectedPiece)
	@Override
	public boolean useAbility(String abilityName,Piece piece, Piece affectedPiece) {
		if(abilityName.equals("protect"))
			return giveProtection(piece, affectedPiece);
		
		return false;
	}
	
	
	private boolean giveProtection(Piece piece, Piece affectedPiece) {
		
		try {
			
			int pieceX =  piece.getPosition().get("x");
			int pieceY =  piece.getPosition().get("y");
			
			int affectedPieceX =  affectedPiece.getPosition().get("x");
			int affectedPieceY =  affectedPiece.getPosition().get("y");
			
			if(affectedPieceX > pieceX + 1 || affectedPieceY > pieceY + 1 || 
				affectedPieceX < pieceX - 1 || affectedPieceY < pieceY - 1) {
				return false;
			}
			
			affectedPiece.setImmune(true);
			
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}
	
	
}
