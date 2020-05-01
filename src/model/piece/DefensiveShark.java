package model.piece;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	final int DISTANCE = 1;
	private EngineInterface engine;

	public DefensiveShark(int x, int y, EngineInterface engine) {
		super(x, y);
		this.engine = engine;
	}

	@Override
	public Set<Cell> getValidMove() {
		return new DiagonalMove().getValidMove(this, 2);

	}

	@Override
	public void movePiece(int x, int y) {
		setPosition(x, y);
	}

	@Override
	public void useAbility(PieceAbility pieceAbility, PieceInterface piece, PieceInterface affectedPiece) {
		if (pieceAbility.equals(PieceAbility.PROTECT)) {
			defend(affectedPiece);
		} else if (pieceAbility.equals(PieceAbility.QUICKMOVE)) {
			// QUICKMOVE = a normal move piece action see what needs to be updated when
			// moving a piece for more information, no affectedPiece required

		} else {
			throw new IllegalArgumentException("Invalid ability");
		}
	}

	private void defend(PieceInterface targetPiece) {
		try {
			if (targetPiece.isImmune())
				throw new IllegalArgumentException("The piece is immune");

			targetPiece.setImmune(true);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public Set<Cell> abilityCells() {
		// Return the unoccupied nearby cells of the other two active sharks
		// Return the cell of the other two active sharks
		// nearbyCell = 8 cells surrounding, this forms a square => similar to
		// aggressive shark can reference aggressiveShark for more information
		return null;
	}

	// private int neighbourCellDistance(int pieceX, int pieceY) {
	// List<PieceInterface> activeSharks = engine.pieceOperator().getActiveSharks();
	//
	// for (PieceInterface activePiece : activeSharks) {
	// int x = activePiece.getPosition().get("x");
	// int y = activePiece.getPosition().get("y");
	//
	// //TODO return a list of ALL possible moves surrounding all the other sharks?
	// }
	//
	// return 0;
	// }
	//
	// private boolean isSurrounding(int x1, int x2, int y1, int y2, int distance) {
	// if (x2 > x1 + distance || y2 > y1 + distance || x2 < x1 - distance || y2 < y1
	// - distance) {
	// return false;
	// }
	// return true;
	// }

	@Override
	public String toString() {
		return String.format("%s", "DefensiveShark");
	}

}