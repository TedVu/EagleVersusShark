package model.piece;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import model.board.Cell;
import model.contract.Engine;
import model.contract.Piece;
import model.enumtype.PieceAbility;
import model.enumtype.PieceType;
import model.piece.movement.BasicMove;
import model.piece.movement.DiagonalDecorator;
import model.piece.movement.PieceMoveDecorator;

/**
 * @author chanboth
 *
 */
public class HealingShark extends AbstractPiece {

	private static final long serialVersionUID = -7746905541941458353L;

	private final Engine engine;

	public HealingShark(int x, int y, Engine engine) {
		super(x, y);
		this.engine = engine;
	}

	@Override
	@Requires({ "getPosition() != null" })
	@Ensures("getValidMove() != null")
	public Set<Cell> getValidMove() {
		return new PieceMoveDecorator(new DiagonalDecorator(new BasicMove())).getValidMove(this, 1);

	}

	@Override
	@Requires({ "getPosition() != null" })
	@Ensures("getPosition().get(\"x\") == x && getPosition().get(\"y\") == y")
	public void movePiece(int x, int y) {
		setPosition(x, y);
	}

	@Override
	public void useAbility(PieceAbility pieceAbility, Piece piece, Piece affectedPiece) {
		if (pieceAbility.equals(PieceAbility.HEAL)) {

			int counter = engine.pieceOperator().getHealingAbilityCounter();
			System.out.println(counter);
			if (counter != 0) {
				if (counter == 2) {
					engine.pieceOperator().resetHealingAbilityCounter();
				}
				throw new RuntimeException("You just used the ability last round!");
			} else {
				heal(affectedPiece);
			}
		} else {
			throw new IllegalArgumentException("Invalid ability");
		}
	}

	private void heal(Piece affectedPiece) {
		try {

			// Move selected shark piece to its original cell (upon initialization) and set
			// it to active
			int initialX = 0;
			int initialY = 0;
			if (affectedPiece instanceof AggressiveShark) {
				initialX = PieceType.AGGRESSIVESHARK.xCoordinate(engine.gameBoard().getSize());
				initialY = PieceType.AGGRESSIVESHARK.yCoordinate(engine.gameBoard().getSize());

			} else if (affectedPiece instanceof DefensiveShark) {
				initialX = PieceType.DEFENSIVESHARK.xCoordinate(engine.gameBoard().getSize());
				initialY = PieceType.DEFENSIVESHARK.yCoordinate(engine.gameBoard().getSize());
			}

			Cell initialCell = engine.gameBoard().getCell(initialX, initialY);
			boolean cellOccupied = false;

			// Will keep changing initial cell until the cell is found != occupied
			while (!cellOccupied) {
				if (initialCell.getOccupied()) {
					// AggressiveShark will move 1 unit north-west
					if (affectedPiece instanceof AggressiveShark) {
						--initialX;
						--initialY;
					}
					// DefensiveShark will move 1 unit up
					if (affectedPiece instanceof DefensiveShark)
						--initialY;
					if (!engine.gameBoard().getCell(initialX, initialY).getOccupied())
						cellOccupied = true;
				} else {
					cellOccupied = true;
				}
			}
			affectedPiece.setPosition(initialX, initialY);
			engine.pieceOperator().setPieceActiveStatus(affectedPiece, true);
			engine.pieceOperator().incrementHealingAbilityCounter();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public String toString() {
		return String.format("%s", "HealingShark");
	}

	@Override
	public Set<Cell> modeCells() {

		Set<Cell> currentEaglePositions = new HashSet<>();
		List<Piece> activeEagles = engine.pieceOperator().getActiveEagles();
		for (Piece activeEagle : activeEagles) {
			int x = activeEagle.getPosition().get("x");
			int y = activeEagle.getPosition().get("y");
			if (y != 0) {
				currentEaglePositions.add(new Cell(x, y));
			}
		}
		if (currentEaglePositions.size() > 0) {
			return currentEaglePositions;
		} else
			throw new IllegalArgumentException("All eagle is at the top eagle side now cannot use this mode");

	}

	@Override
	public Set<Cell> abilityCells() {
		return null;
	}

}
