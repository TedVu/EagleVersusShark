package model.piece;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.java.contract.Requires;

import model.board.Cell;
import model.contract.Engine;
import model.contract.Piece;
import model.engine.EngineImpl;
import model.enumtype.PieceAbility;
import model.piece.movement.BasicMove;

/**
 * @author chanboth
 *
 */
public class AggressiveShark extends AbstractPiece {

	private static final long serialVersionUID = 7717531522291318350L;
	private final Engine engine;
	private boolean secondAbilityUnlock;

	public AggressiveShark(int x, int y, Engine engine) {
		super(x, y);
		this.engine = engine;
		secondAbilityUnlock = false;
	}

	public void setSecondAbilityUnlock() {
		this.secondAbilityUnlock = true;
	}

	@Override
	public Set<Cell> getValidMove() {
		Cell currentPos = engine.gameBoard().getCell(this.getPosition().get("x"),
				this.getPosition().get("y"));
		if (!currentPos.isWaterCell()) {
			return new BasicMove().getValidMove(this, 1);
		} else {
			return new BasicMove().getValidMove(this, 2);
		}
	}

	@Override
	@Requires({ "x>=0", "y>=0" })
	public void movePiece(int x, int y) {
		if (engine.gameBoard().getCell(x, y).isWaterCell()) {
			secondAbilityUnlock = true;
		}
		setPosition(x, y);
	}

	@Override
	public void useAbility(PieceAbility pieceAbility, Piece piece, Piece affectedPiece) {
		if (pieceAbility.equals(PieceAbility.CAPTURE)) {
			capture(piece, affectedPiece);
			if (engine.pieceOperator().getHealingAbilityCounter() == 2) {
				engine.pieceOperator().resetHealingAbilityCounter();
			}
		} else {
			throw new IllegalArgumentException("Invalid ability");
		}
	}

	private void capture(Piece piece, Piece affectedPiece) {
		try {
			Cell currentPos = engine.gameBoard().getCell(piece.getPosition().get("x"),
					piece.getPosition().get("y"));
			Cell opponentPos = engine.gameBoard()
					.getCell(affectedPiece.getPosition().get("x"), affectedPiece.getPosition().get("y"));
			if (currentPos.isWaterCell()) {
				if (!opponentPos.isWaterCell() && affectedPiece.isImmune()) {
					throw new IllegalArgumentException("The piece is immune");
				}
			} else if (!currentPos.isWaterCell()) {
				if (affectedPiece.isImmune()) {
					throw new IllegalArgumentException("The piece is immune");
				}
			}

			engine.gameBoard().removePiece(currentPos.getX(), currentPos.getY());

			movePiece(opponentPos.getX(), opponentPos.getY());
			affectedPiece.setActive(false);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public Set<Cell> abilityCells() {
		int distance = 0;
		Cell currentPos = engine.gameBoard().getCell(this.getPosition().get("x"),
				this.getPosition().get("y"));
		if (!currentPos.isWaterCell()) {
			distance = 1;
		} else {
			distance = 2;
		}
		List<Piece> activeEagles = engine.pieceOperator().getActiveEagles();
		Set<Cell> activeEaglePos = new HashSet<>();
		for (Piece activeEagle : activeEagles) {
			Cell cell = engine.gameBoard().getCell(activeEagle.getPosition().get("x"),
					activeEagle.getPosition().get("y"));
			activeEaglePos.add(cell);
		}
		Set<Cell> abilityCells = new HashSet<>();
		for (Cell cell : activeEaglePos) {
			// only capture in horizontal + vertically
			if (cell.getX() == currentPos.getX() || cell.getY() == currentPos.getY()) {
				if (cell.getX() == currentPos.getX() && Math.abs(cell.getY() - currentPos.getY()) <= distance) {
					abilityCells.add(cell);
				} else if (cell.getY() == currentPos.getY() && Math.abs(cell.getX() - currentPos.getX()) <= distance) {
					abilityCells.add(cell);
				}
			}
		}
		if (abilityCells.size() == 0) {
			throw new RuntimeException("No enemy nearby to capture");
		}

		return abilityCells;
	}

	@Override
	public String toString() {
		return String.format("%s", "AggressiveShark");
	}

	@Override
	public Set<Cell> modeCells() {
		// https://prnt.sc/sjd4oa
		Set<Cell> returnCells = new HashSet<>();

		if (this.secondAbilityUnlock) {
			Cell currentCell = engine.gameBoard().getCell(this.getPosition().get("x"), this.getPosition().get("y"));

			// Populate the cells that are like Chess Knight valid moves
			int[] offsets = { -2, -1, 1, 2 };
			for (int x : offsets) {
				for (int y : offsets) {
					if (Math.abs(x) != Math.abs(y)) {
						// Unfortunately there is no way around refactoring the below block of code as
						// you cannot make a
						// temporary cell before checking its validity

						// Check if the possible sell is within the board and occupation
						if (currentCell.getX() + x < engine.gameBoard().getSize() && currentCell.getX() + x >= 0
								&& currentCell.getY() + y < engine.gameBoard().getSize() && currentCell.getY() + y >= 0
								&& !engine.gameBoard().getCell(currentCell.getX() + x, currentCell.getY() + y)
										.getOccupied()) {
							// Before adding to the return list, check if the cell is the master cell
							Cell targetCell = engine.gameBoard().getCell(currentCell.getX() + x,
									currentCell.getY() + y);
							if (!targetCell.equals(engine.gameBoard().getEagleMasterCell()))
								returnCells.add(
										engine.gameBoard().getCell(currentCell.getX() + x, currentCell.getY() + y));
						}
					}
				}
			}
		} else
			throw new IllegalArgumentException("Aggressive Shark has never stepped on any water cell!\n"
					+ "You can activate it by stepping on a water cell once.");

		return returnCells;
	}

}
