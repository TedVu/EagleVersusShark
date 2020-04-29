package model.piece;

import java.util.Set;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import model.board.Cell;
import model.contract.PieceInterface;
import model.engine.EngineImpl;
import model.enumtype.PieceAbility;
import model.enumtype.PieceType;
import model.piece.movement.DiagonalMove;

/**
 * @author chanboth
 *
 */
public class HealingShark extends AbstractPiece {

	public HealingShark(int x, int y) {
		super(x, y);
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
			heal(affectedPiece);
		} else {
			throw new IllegalArgumentException("Invalid ability");
		}
	}

	private void heal(PieceInterface affectedPiece) {
		try {

			// Move selected shark piece to its original cell (upon initialization) and set
			// it to active
			movePiece(PieceType.HEALINGSHARK.xCoordinate(EngineImpl.getSingletonInstance().getBoard().getSize()),
					PieceType.HEALINGSHARK.yCoordinate(EngineImpl.getSingletonInstance().getBoard().getSize()));
			affectedPiece.setActive(true);

			// TODO set the healing shark to inactive for one turn

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public Set<Cell> abilityCells() {
		// Unsure with the purpose of this method since the shark can heal ANY shark
		// from ANYWHERE
		return null;

	}

	@Override
	public String toString() {
		return String.format("%s", "HealingShark");
	}

}
