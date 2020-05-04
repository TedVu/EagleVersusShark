
package model.piece;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import model.board.Cell;
import model.contract.EngineInterface;
import model.contract.PieceInterface;
import model.engine.EngineImpl;
import model.enumtype.PieceAbility;
import model.piece.movement.DiagonalMove;

/**
 * @author sefira
 *
 */
public class VisionaryEagle extends AbstractPiece {


	EngineInterface engine;

	public VisionaryEagle(int x, int y, EngineInterface engine) {
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
	@Requires({ "getPosition() != null" })
	@Ensures("getPosition().get(\"x\") == x && getPosition().get(\"y\") == y")
	public void movePiece(int x, int y) {
		setPosition(x, y);

		// Chanboth (Remove these comments upon submission)
		// Integrate HealingShark's healing ability tracker
		EngineImpl.getSingletonInstance().pieceOperator().eagleCheckingHealingSharkAbility();
	}

	@Override
	public void useAbility(PieceAbility pieceAbility, PieceInterface piece, PieceInterface affectedPiece) {
		if (pieceAbility.equals(PieceAbility.SWAP)) {
			swap(piece, affectedPiece);
		} else {
			throw new IllegalArgumentException("Invalid ability");
		}
	}

	public void swap(PieceInterface piece, PieceInterface affectedPiece) {

		try {
			Map<String, Integer> position1 = new HashMap<String, Integer>();
			Map<String, Integer> position2 = new HashMap<String, Integer>();

			position1.putAll(piece.getPosition());
			position2.putAll(affectedPiece.getPosition());

			piece.setPosition(position2.get("x"), position2.get("y"));
			affectedPiece.setPosition(position1.get("x"), position1.get("y"));

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Set<Cell> abilityCells() {

		Set<Cell> swapPositions = new HashSet<>();
		List<PieceInterface> activeEagles = engine.pieceOperator().getActiveEagles();
		for (PieceInterface activeEagle : activeEagles) {
			if (!(activeEagle instanceof VisionaryEagle)) {

				int x = activeEagle.getPosition().get("x");
				int y = activeEagle.getPosition().get("y");

				swapPositions.add(new Cell(x, y));
			}
		}

		return swapPositions;
	}
	
	@Override
	public String toString() {
		return String.format("%s", "VisionaryEagle");
	}
	
	
}
