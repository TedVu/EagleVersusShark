
package model.piece;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.management.RuntimeErrorException;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import model.board.Cell;
import model.contract.Engine;
import model.contract.Piece;
import model.engine.EngineImpl;
import model.enumtype.PieceAbility;
import model.piece.movement.BasicMove;

/**
 * @author sefira & kevin
 *
 */
public class LeadershipEagle extends AbstractPiece {

	private static final long serialVersionUID = -6579315280240148680L;
	Engine engine;

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
	public void useAbility(PieceAbility pieceAbility, Piece piece, Piece affectedPiece) {

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
		List<Piece> activeEagles = engine.pieceOperator().getActiveEagles();

		for (Piece activeEagle : activeEagles) {
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

	private void protect(Piece piece, Piece affectedPiece) {

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
		engine = EngineImpl.getSingletonInstance();
		Set<Cell> finalMode = new HashSet<>();

		try {
			if (engine.gameBoard().getCell(this.getPosition().get("x"), this.getPosition().get("y")).isWaterCell()) {
				throw new IllegalArgumentException("Cannot use mode because currently on water cell");
			}
			int YPos = getPosition().get("y");

			// check if stand in boundary
			if (YPos != engine.gameBoard().getSize() && YPos != 0) {
				boolean waterCellFrontStatus = engine.gameBoard().getCell(getPosition().get("x"), YPos + 1)
						.isWaterCell();
				boolean waterCellBackStatus = engine.gameBoard().getCell(getPosition().get("x"), YPos - 1).isWaterCell();
				if (waterCellFrontStatus) {
					boolean leapCellOccupyStatusFront = engine.gameBoard().getCell(getPosition().get("x"), YPos + 4)
							.getOccupied();
					if (leapCellOccupyStatusFront) {
						throw new IllegalArgumentException("Cannot use this mode because the leap-to cell is occupied");
					}
					finalMode.add(engine.gameBoard().getCell(getPosition().get("x"), YPos + 4));
				} else if (waterCellBackStatus) {
					boolean leapCellOccupyStatusBack = engine.gameBoard().getCell(getPosition().get("x"), YPos - 4)
							.getOccupied();
					if (leapCellOccupyStatusBack) {
						throw new IllegalArgumentException("Cannot use this mode because the leap-to cell is occupied");
					}
					finalMode.add(engine.gameBoard().getCell(getPosition().get("x"), YPos - 4));

				} else {
					throw new IllegalArgumentException("Not standing near to any water cell to use this mode");
				}

			} else {
				throw new IllegalArgumentException("Cannot use mode at this position");
			}

		} catch (Error e) {
			throw new RuntimeException(e);
		}
		return finalMode;
	}



}
