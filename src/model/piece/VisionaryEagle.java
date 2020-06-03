
package model.piece;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import model.board.Cell;
import model.contract.Engine;
import model.contract.Piece;
import model.enumtype.PieceAbility;
import model.piece.movement.BasicMove;
import model.piece.movement.DiagonalDecorator;

/**
 * @author sefira
 *
 */
public class VisionaryEagle extends AbstractPiece {

	private static final long serialVersionUID = -6390595106558376146L;

	private Engine engine;

	public VisionaryEagle(int x, int y, Engine engine) {
		super(x, y);
		this.engine = engine;
	}

	@Override
	@Requires("getPosition() != null")
	@Ensures("abilityCells() != null")
	public Set<Cell> abilityCells() {
		Set<Cell> swapPositions = new HashSet<>();
		List<Piece> activeEagles = engine.pieceOperator().getActiveEagles();
		for (Piece activeEagle : activeEagles) {
			if (!(activeEagle instanceof VisionaryEagle)) {

				int x = activeEagle.getPosition().get("x");
				int y = activeEagle.getPosition().get("y");

				swapPositions.add(new Cell(x, y));
			}
		}

		if (swapPositions.size() == 0) {
			throw new RuntimeException("No eagle alive to swap");
		}
		return swapPositions;
	}

	@Override
	@Requires("getPosition() != null")
	@Ensures("getValidMove() != null")
	public Set<Cell> getValidMove() {
		return new DiagonalDecorator(new BasicMove()).getValidMove(this, 2);
	}

	@Override
	@Requires("getPosition() != null")
	@Ensures("modeCells() != null")
	public Set<Cell> modeCells() {
		Set<Cell> swapPositions = new HashSet<>();
		List<Piece> activeSharks = engine.pieceOperator().getActiveSharks();
		for (Piece activeShark : activeSharks) {

			int x = activeShark.getPosition().get("x");
			int y = activeShark.getPosition().get("y");

			swapPositions.add(new Cell(x, y));
		}
		return swapPositions;
	}

	@Override
	@Requires("getPosition() != null")
	@Ensures("getPosition().get(\"x\") == x && getPosition().get(\"y\") == y")
	public void movePiece(int x, int y) {
		setPosition(x, y);
		engine.pieceOperator().eagleCheckingHealingSharkAbility();
	}

	@Requires({ "piece!=null", "affectedPiece!=null" })
	private void swap(Piece piece, Piece affectedPiece) {
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
	public String toString() {
		return String.format("%s", "VisionaryEagle");
	}

	@Override
	@Requires({ "pieceAbility!= null", "piece!= null", "affectedPiece!= null" })
	public void useAbility(PieceAbility pieceAbility, Piece piece, Piece affectedPiece) {
		if (pieceAbility.equals(PieceAbility.SWAP)) {
			swap(piece, affectedPiece);
		}

		else {
			throw new IllegalArgumentException("Invalid ability");
		}
	}

}
