package model.piece;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.java.contract.Requires;

import model.board.Cell;
import model.contract.PieceInterface;
import model.engine.EngineImpl;
import model.enumtype.PieceAbility;
import model.piece.movement.BasicMove;
import model.piece.movement.DiagonalDecorator;
import model.piece.movement.PieceMoveDecorator;

/**
 * @author chanboth
 *
 */
public class AggressiveShark extends AbstractPiece {

	private static final long serialVersionUID = 7717531522291318350L;

	public AggressiveShark(int x, int y) {
		super(x, y);
	}

	@Override
	public Set<Cell> getValidMove() {
		Cell currentPos = EngineImpl.getSingletonInstance().getBoard().getCell(this.getPosition().get("x"),
				this.getPosition().get("y"));
		if (!currentPos.isWaterCell()) {
			return new DiagonalDecorator(new BasicMove()).getValidMove(this, 1);
		} else {
			return new DiagonalDecorator(new BasicMove()).getValidMove(this, 2);
		}
	}

	@Override
	@Requires({ "x>=0", "y>=0" })
	public void movePiece(int x, int y) {
		setPosition(x, y);
	}

	@Override
	public void useAbility(PieceAbility pieceAbility, PieceInterface piece, PieceInterface affectedPiece) {
		if (pieceAbility.equals(PieceAbility.CAPTURE)) {
			capture(piece, affectedPiece);
			if (EngineImpl.getSingletonInstance().pieceOperator().getHealingAbilityCounter() == 2) {
				EngineImpl.getSingletonInstance().pieceOperator().resetHealingAbilityCounter();
			}
		} else {
			throw new IllegalArgumentException("Invalid ability");
		}
	}

	private void capture(PieceInterface piece, PieceInterface affectedPiece) {
		try {
			Cell currentPos = EngineImpl.getSingletonInstance().getBoard().getCell(piece.getPosition().get("x"),
					piece.getPosition().get("y"));
			Cell opponentPos = EngineImpl.getSingletonInstance().getBoard()
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

			EngineImpl.getSingletonInstance().getBoard().removePiece(currentPos.getX(), currentPos.getY());

			movePiece(opponentPos.getX(), opponentPos.getY());
			affectedPiece.setActive(false);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public Set<Cell> abilityCells() {
		int distance = 0;
		Cell currentPos = EngineImpl.getSingletonInstance().getBoard().getCell(this.getPosition().get("x"),
				this.getPosition().get("y"));
		if (!currentPos.isWaterCell()) {
			distance = 1;
		} else {
			distance = 2;
		}
		List<PieceInterface> activeEagles = EngineImpl.getSingletonInstance().pieceOperator().getActiveEagles();
		Set<Cell> activeEaglePos = new HashSet<>();
		for (PieceInterface activeEagle : activeEagles) {
			Cell cell = EngineImpl.getSingletonInstance().getBoard().getCell(activeEagle.getPosition().get("x"),
					activeEagle.getPosition().get("y"));
			activeEaglePos.add(cell);
		}
		Set<Cell> abilityCell = new HashSet<>();
		for (Cell cell : activeEaglePos) {
			// only capture in horizontal + vertically
			if (cell.getX() == currentPos.getX() || cell.getY() == currentPos.getY()) {
				if (cell.getX() == currentPos.getX() && Math.abs(cell.getY() - currentPos.getY()) <= distance) {
					abilityCell.add(cell);
				} else if (cell.getY() == currentPos.getY() && Math.abs(cell.getX() - currentPos.getX()) <= distance) {
					abilityCell.add(cell);
				}
			}
		}

		return abilityCell;
	}

	@Override
	public String toString() {
		return String.format("%s", "AggressiveShark");
	}

	@Override
	public Set<Cell> modeCells() {
		// return a random cell
		return null;
	}


}
