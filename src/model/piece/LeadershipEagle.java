
package model.piece;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.management.RuntimeErrorException;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import model.board.Cell;
import model.contract.EngineInterface;
import model.contract.PieceInterface;
import model.engine.EngineImpl;
import model.enumtype.PieceAbility;
import model.piece.movement.BasicMove;

/**
 * @author sefira & kevin
 *
 */
public class LeadershipEagle extends AbstractPiece {

	private static final long serialVersionUID = -6579315280240148680L;
	EngineInterface engine;

	public LeadershipEagle(int x, int y) {
		super(x, y);
	}

	@Override
	@Requires({ "getPosition() != null" })
	@Ensures("getValidMove() != null")
	public Set<Cell> getValidMove() {
		return new BasicMove().getValidMove(this, 2);
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

		if (pieceAbility.equals(PieceAbility.PROTECT)) {
			protect(piece, affectedPiece);
		} else {
			throw new IllegalArgumentException("Invalid ability");
		}

	}

	@Override
	public Set<Cell> abilityCells() {
		Set<Cell> otherEagleCells = new HashSet<Cell>();
		engine = EngineImpl.getSingletonInstance();
		List<PieceInterface> activeEagles = engine.pieceOperator().getActiveEagles();

		for (PieceInterface activeEagle : activeEagles) {
			if (!(activeEagle instanceof LeadershipEagle)) {

				int x = activeEagle.getPosition().get("x");
				int y = activeEagle.getPosition().get("y");
				otherEagleCells.add(new Cell(x, y));
			}
		}
		Set<Cell> validCells = new HashSet<>();
		for (Cell c : otherEagleCells) {
			int x = c.getX();
			int y = c.getY();
			// nearby cell
			if (Math.abs((double) (this.getPosition().get("x") - x)) < 2
					&& Math.abs((double) (this.getPosition().get("y") - y)) < 2) {
				validCells.add(c);
			}
		}

		return validCells;

	}

	private void protect(PieceInterface piece, PieceInterface affectedPiece) {

		try {

			if (affectedPiece.isImmune())
				throw new IllegalArgumentException("Chosen piece is already immune");

			int pieceX = piece.getPosition().get("x");
			int pieceY = piece.getPosition().get("y");

			int affectedPieceX = affectedPiece.getPosition().get("x");
			int affectedPieceY = affectedPiece.getPosition().get("y");

			if (!isSurrounding(pieceX, affectedPieceX, pieceY, affectedPieceY, 1)) {
				throw new IllegalArgumentException("Invalid position to protect");
			}

			affectedPiece.setImmune(true);

		} catch (Error e) {
			throw new RuntimeErrorException(e);
		}

	}

	private boolean isSurrounding(int x1, int x2, int y1, int y2, int distance) {
		if (x2 > x1 + distance || y2 > y1 + distance || x2 < x1 - distance || y2 < y1 - distance) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s", "LeadershipEagle");
	}

	@Override
	public Set<Cell> modeCells() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void useMode(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
