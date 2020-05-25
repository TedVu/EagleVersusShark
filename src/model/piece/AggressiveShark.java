package model.piece;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.java.contract.Requires;

import model.board.Cell;
import model.board.GameBoard;
import model.contract.Engine;
import model.contract.Piece;
import model.enumtype.CellType;
import model.enumtype.PieceAbility;
import model.piece.movement.BasicMove;

/**
 * @author Chanboth
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
		Cell currentPos = engine.gameBoard().getCell(this.getPosition().get("x"), this.getPosition().get("y"));
		if (!(currentPos.getType() == CellType.WATER)) {
			return new BasicMove().getValidMove(this, 1);
		} else {
			return new BasicMove().getValidMove(this, 2);
		}
	}

	@Override
	@Requires({ "x>=0", "y>=0" })
	public void movePiece(int x, int y) {
		if (engine.gameBoard().getCell(x, y).getType() == CellType.WATER) {
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
			Cell currentPos = engine.gameBoard().getCell(piece.getPosition().get("x"), piece.getPosition().get("y"));
			Cell opponentPos = engine.gameBoard().getCell(affectedPiece.getPosition().get("x"),
					affectedPiece.getPosition().get("y"));
			if (currentPos.getType() == CellType.WATER) {
				if (!(opponentPos.getType() == CellType.WATER) && affectedPiece.isImmune()) {
					throw new IllegalArgumentException("The piece is immune");
				}
			} else if (!(currentPos.getType() == CellType.WATER)) {
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
		Cell currentPos = engine.gameBoard().getCell(this.getPosition().get("x"), this.getPosition().get("y"));
		if (!(currentPos.getType() == CellType.WATER)) {
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
		Set<Cell> returnCells = new HashSet<>();

		if (this.secondAbilityUnlock) {
			GameBoard gameBoard = engine.gameBoard();
			Cell currentCell = gameBoard.getCell(this.getPosition().get("x"), this.getPosition().get("y"));

			int[] offsets = { -2, -1, 1, 2 };
			for (int x : offsets) {
				for (int y : offsets) {
					if (Math.abs(x) != Math.abs(y)) {
						if (currentCell.getX() + x < gameBoard.getSize() && currentCell.getX() + x >= 0
								&& currentCell.getY() + y < gameBoard.getSize() && currentCell.getY() + y >= 0) {
							int xPos = currentCell.getX() + x;
							int yPos = currentCell.getY() + y;
							Cell targetCell = gameBoard.getCell(xPos, yPos);

							if (!gameBoard.getCell(xPos, yPos).getOccupied()
									&& !targetCell.equals(gameBoard.getEagleMasterCell()))
								returnCells.add(targetCell);
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
