package model.piece;

import java.util.*;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;
import model.board.Cell;
import model.contract.EngineInterface;
import model.contract.PieceInterface;
import model.engine.EngineImpl;
import model.enumtype.PieceAbility;
import model.piece.movement.DiagonalMove;

/**
 * @author chanboth
 *
 */
public class DefensiveShark extends AbstractPiece {

	final int NEIGHBOURING_DISTANCE = 1;
	private final EngineInterface engine;

	public DefensiveShark(int x, int y, EngineInterface engine) {
		super(x, y);
		this.engine = engine;
	}

	@Override
	@Requires({ "getPosition() != null" })
	@Ensures("getValidMove() != null")
	public Set<Cell> getValidMove() {
		return new DiagonalMove().getValidMove(this, 2);
	}

	@Override
	@Requires({ "x>=0", "y>=0" })
	public void movePiece(int x, int y) {
		setPosition(x, y);
	}

	@Override
	public void useAbility(PieceAbility pieceAbility, PieceInterface piece, PieceInterface affectedPiece) {
		if (pieceAbility.equals(PieceAbility.PROTECT)) {
			defend(affectedPiece);
		} else {
			throw new IllegalArgumentException("Invalid ability");
		}
	}

	// Overload method for second ability
	// public void useAbility(PieceAbility pieceAbility, PieceInterface piece, Cell
	// surroundingCell){
	// if(pieceAbility.equals(PieceAbility.QUICKMOVE)){
	// quickMove(piece,surroundingCell);
	// } else {
	// throw new IllegalArgumentException("Invalid ability");
	// }
	// }
	//
	// private void quickMove(PieceInterface piece, Cell surroundingCell){
	// Set<Cell> neighbourCells = abilityCells();
	// if(neighbourCells.contains(surroundingCell)){
	// piece.movePiece(surroundingCell.getX(),surroundingCell.getY());
	// } else {
	// throw new IllegalArgumentException("Invalid move");
	// }
	// }

	private void defend(PieceInterface targetPiece) {
		try {
			targetPiece.setImmune(true);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	@Ensures("abilityCells() != null")
	public Set<Cell> abilityCells() {
		Set<Cell> neighbourCells = new HashSet<>();
		Set<Cell> surroundingEightCells = new HashSet<>();
		List<PieceInterface> activeSharks = engine.pieceOperator().getActiveSharks();

		// Return all the neighbouring cells around other sharks
		for (PieceInterface shark : activeSharks) {
			if (!(shark instanceof DefensiveShark)) {
				int x = shark.getPosition().get("x");
				int y = shark.getPosition().get("y");

				// All the neighbour cells around a shark - to be traversed
				surroundingEightCells.addAll(new DiagonalMove().getValidMove(shark, NEIGHBOURING_DISTANCE));
			}
		}

		/*
		 * Add the cell to the return list if the following are true: Cell is not
		 * occupied Cell is not the master cell Cell is within the board
		 */
		for (Cell possibleCell : surroundingEightCells) {
			if (!possibleCell.getOccupied() && !possibleCell.getIsMasterCell()
					&& possibleCell.getY() < engine.getBoard().getSize() - 1 && possibleCell.getY() >= 0
					&& possibleCell.getX() < engine.getBoard().getSize() - 1 && possibleCell.getX() >= 0) {
				neighbourCells.add(possibleCell);
			}
		}

		return neighbourCells;
	}

	@Override
	public String toString() {
		return String.format("%s", "DefensiveShark");
	}

}