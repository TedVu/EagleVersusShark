
package model.piece;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import model.board.Cell;
import model.contract.Engine;
import model.contract.Piece;
import model.engine.EngineImpl;
import model.enumtype.CellType;
import model.enumtype.PieceAbility;
import model.piece.movement.BasicMove;

/**
 * @author sefira & kevin
 *
 */
public class LeadershipEagle extends AbstractPiece {

	private static final long serialVersionUID = -6579315280240148680L;
	private Engine engine;

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
		EngineImpl.getSingletonInstance().pieceOperator().eagleCheckingHealingSharkAbility();
	}

	@Override
	public void useAbility(PieceAbility pieceAbility, Piece piece, Piece affectedPiece) {

		if (pieceAbility.equals(PieceAbility.PROTECT)) {
			protect(piece, affectedPiece);
		} else {
			throw new IllegalArgumentException("Invalid ability");
		}

	}

	@Override
	public Set<Cell> abilityCells() {
		engine = EngineImpl.getSingletonInstance();

		Set<Cell> otherEagleCells = new HashSet<Cell>();
		List<Piece> activeEagles = engine.pieceOperator().getActiveEagles();

		for (Piece activeEagle : activeEagles) {
			if (!(activeEagle instanceof LeadershipEagle)) {

				int x = activeEagle.getPosition().get("x");
				int y = activeEagle.getPosition().get("y");
				otherEagleCells.add(new Cell(x, y));
			}
		}
		Set<Cell> validCells = new HashSet<>();
		for (Cell cell : otherEagleCells) {
			int x = cell.getX();
			int y = cell.getY();
			// nearby cell
			if (Math.abs((double) (this.getPosition().get("x") - x)) < 2
					&& Math.abs((double) (this.getPosition().get("y") - y)) < 2) {
				validCells.add(cell);
			}
		}
		if (validCells.size() == 0) {
			throw new RuntimeException("No ally nearby to use this mode");
		}

		return validCells;

	}

	private void protect(Piece piece, Piece affectedPiece) {

		if (affectedPiece.isImmune())
			throw new IllegalArgumentException("Chosen piece is already immune");

		int pieceX = piece.getPosition().get("x");
		int pieceY = piece.getPosition().get("y");

		int affectedPieceX = affectedPiece.getPosition().get("x");
		int affectedPieceY = affectedPiece.getPosition().get("y");

		if (!isSurrounding(pieceX, affectedPieceX, pieceY, affectedPieceY, 1)) {
			throw new RuntimeException("Invalid position to protect");
		}

		affectedPiece.setImmune(true);

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
		engine = EngineImpl.getSingletonInstance();
		Set<Cell> finalMode = new HashSet<>();
		if (engine.gameBoard().getCell(this.getPosition().get("x"), this.getPosition().get("y")).getType() == CellType.WATER) {
			throw new IllegalArgumentException("Cannot use mode because currently on water cell");
		}
		int YPos = getPosition().get("y");

		if (YPos != engine.gameBoard().getSize() && YPos != 0) {
			boolean waterCellFrontStatus = engine.gameBoard().getCell(getPosition().get("x"), YPos + 1).getType() == CellType.WATER;
			boolean waterCellBackStatus = engine.gameBoard().getCell(getPosition().get("x"), YPos - 1).getType() == CellType.WATER;
			if (waterCellFrontStatus) {
				checkAbleToLeap(finalMode, YPos + 4);
			} else if (waterCellBackStatus) {
				checkAbleToLeap(finalMode, YPos - 4);
			} else {
				throw new RuntimeException("Not standing near to any water cell to use this mode");
			}

		} else {
			throw new RuntimeException("Cannot use mode at this position");
		}

		return finalMode;
	}

	private void checkAbleToLeap(Set<Cell> finalMode, int YPos) {
		boolean leapCellOccupyStatusFront = engine.gameBoard().getCell(getPosition().get("x"), YPos).getOccupied();
		if (leapCellOccupyStatusFront) {
			throw new RuntimeException("Cannot use this mode because the leap-to cell is occupied");
		}
		finalMode.add(engine.gameBoard().getCell(getPosition().get("x"), YPos));
	}

}
