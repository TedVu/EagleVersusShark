package model.piece;

import java.util.*;

import com.google.java.contract.Requires;

import model.board.Cell;
import model.contract.EngineInterface;
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
	private final EngineInterface engine;

	public AggressiveShark(int x, int y, EngineInterface engine) {
		super(x, y);
		this.engine = engine;
	}

	@Override
	public Set<Cell> getValidMove() {
		Cell currentPos = EngineImpl.getSingletonInstance().getBoard().getCell(this.getPosition().get("x"),
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
		Cell currentCell = new Cell(this.getPosition().get("x"),this.getPosition().get("y"));

		Set<Cell> candidateCells = new HashSet<>();
		int[] offsets = {-2, -1, 1, 2};
		for (int x : offsets) {
			for (int y : offsets) {
				if (Math.abs(x) != Math.abs(y)) {
					candidateCells.add(new Cell(currentCell.getX()+x,currentCell.getY()+y));
				}
			}
		}

		Set<Cell> allySharkCells = new HashSet<>();
		List<PieceInterface> activeSharks = engine.pieceOperator().getActiveSharks();
		for (PieceInterface activeShark : activeSharks) {
			int x = activeShark.getPosition().get("x");
			int y = activeShark.getPosition().get("y");
			allySharkCells.add(new Cell(x, y));
		}

		for(Cell cell : candidateCells)
			if(!cell.getOccupied() && !allySharkCells.contains(cell)
					&& cell.getX() < engine.getBoard().getSize()
					&& cell.getX() >= 0
					&& cell.getY() < engine.getBoard().getSize()
					&& cell.getY() >= 0)
				returnCells.add(cell);

		return returnCells;
	}

}
