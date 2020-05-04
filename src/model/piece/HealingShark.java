package model.piece;

import java.util.Set;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import model.board.Cell;
import model.contract.EngineInterface;
import model.contract.PieceInterface;
import model.enumtype.PieceAbility;
import model.enumtype.PieceType;
import model.piece.movement.DiagonalMove;

/**
 * @author chanboth
 *
 */

public class HealingShark extends AbstractPiece {
	private final EngineInterface engine;

	public HealingShark(int x, int y, EngineInterface engine) {
		super(x, y);
		this.engine = engine;
	}

	@Override
	@Requires({ "getPosition() != null" })
	@Ensures("getValidMove() != null")
	public Set<Cell> getValidMove() {
		return new DiagonalMove().getValidMove(this, 1);

	}

	@Override
	@Requires({ "getPosition() != null" })
	@Ensures("getPosition().get(\"x\") == x && getPosition().get(\"y\") == y")
	public void movePiece(int x, int y) {
		setPosition(x, y);
	}

	@Override
	public void useAbility(PieceAbility pieceAbility, PieceInterface piece, PieceInterface affectedPiece) {
		if (pieceAbility.equals(PieceAbility.HEAL)) {
			if (engine.getHealingAbilityCounter() != 0) {
				throw new RuntimeException("You just used the ability last round!");
			} else {
				heal(affectedPiece);
			}
		} else {
			throw new IllegalArgumentException("Invalid ability");
		}
	}

	private void heal(PieceInterface affectedPiece) {
		try {
			// Move selected shark piece to its original cell (upon initialization) and set
			// it to active
			PieceType affectedPieceEnum = PieceType.parsePieceType(affectedPiece.toString());

			int boardSize = engine.getBoard().getSize();
			engine.pieceOperator().movePiece(affectedPiece, affectedPieceEnum.xCoordinate(boardSize),
					affectedPieceEnum.yCoordinate(boardSize));
			affectedPiece.setActive(true);
			engine.incrementHealingAbilityCounter();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public Set<Cell> abilityCells() {

		// refactor later on as not a good practice to return null
		return null;
	}

	@Override
	public String toString() {
		return String.format("%s", "HealingShark");
	}

}
