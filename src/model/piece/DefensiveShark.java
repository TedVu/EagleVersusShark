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
import model.piece.movement.DiagonalDecorator;
import model.piece.movement.PieceMoveDecorator;

/**
 * @author chanboth
 *
 */
public class DefensiveShark extends AbstractPiece {

	private static final long serialVersionUID = -3824904265692727849L;

	final int NEIGHBOURING_DISTANCE = 1;
	private final Engine engine;

	public DefensiveShark(int x, int y, Engine engine) {
		super(x, y);
		this.engine = engine;
	}

	@Override
	@Ensures("abilityCells() != null")
	public Set<Cell> abilityCells() {
		Set<Cell> neighbourCells = new HashSet<>();
		Set<Cell> surroundingEightCells = new HashSet<>();
		List<Piece> activeSharks = engine.pieceOperator().getActiveSharks();

		for (Piece shark : activeSharks) {
			if (!(shark instanceof DefensiveShark)) {
				surroundingEightCells
						.addAll(new DiagonalDecorator(new BasicMove()).getValidMove(shark, NEIGHBOURING_DISTANCE));
				Cell sharkPosition = new Cell(shark.getPosition().get("x"), shark.getPosition().get("y"));
				surroundingEightCells.add(sharkPosition);
			}
		}

		surroundingEightCells.remove(EngineImpl.getSingletonInstance().gameBoard().getSharkMasterCell());
		for (Cell possibleCell : surroundingEightCells) {
			if (!possibleCell.getOccupied() && possibleCell.getY() < engine.gameBoard().getSize()
					&& possibleCell.getY() >= 0 && possibleCell.getX() < engine.gameBoard().getSize()
					&& possibleCell.getX() >= 0) {
				neighbourCells.add(possibleCell);
			}
		}
		return neighbourCells;
	}

	private void defend(Piece targetPiece) {
		targetPiece.setImmune(true);
	}

	@Override
	@Requires("getPosition() != null")
	@Ensures("getValidMove() != null")
	public Set<Cell> getValidMove() {
		return new PieceMoveDecorator(new DiagonalDecorator(new BasicMove())).getValidMove(this, 2);
	}

	@Override
	@Requires("getPosition() != null")
	@Ensures("modeCells() != null")
	public Set<Cell> modeCells() {
		Set<Cell> returnCells = new HashSet<>();

		Cell currentPos = EngineImpl.getSingletonInstance().gameBoard().getCell(this.getPosition().get("x"),
				this.getPosition().get("y"));

		if (currentPos.getType() == CellType.WATER) {
			Set<Cell> temp = new BasicMove().getValidMove(this, engine.gameBoard().getSize());
			Set<Cell> waterCells = engine.gameBoard().getWaterCells();
			for (Cell cell : temp) {
				if (waterCells.contains(cell))
					returnCells.add(cell);
			}
		} else
			throw new IllegalArgumentException(
					"Shark is currently not on water cell!\n" + "You must be on water cell to use the 2nd ability");

		return returnCells;
	}

	@Override
	@Requires({ "x>=0", "y>=0" })
	public void movePiece(int x, int y) {
		setPosition(x, y);
	}

	@Override
	public String toString() {
		return String.format("%s", "DefensiveShark");
	}

	@Override
	@Requires({ "pieceAbility!= null", "piece!= null", "affectedPiece!= null" })
	public void useAbility(PieceAbility pieceAbility, Piece piece, Piece affectedPiece) {
		if (pieceAbility.equals(PieceAbility.PROTECT)) {
			defend(affectedPiece);
			if (EngineImpl.getSingletonInstance().pieceOperator().getHealingAbilityCounter() == 2) {
				EngineImpl.getSingletonInstance().pieceOperator().resetHealingAbilityCounter();
			}
		} else {
			throw new IllegalArgumentException("Invalid ability");
		}
	}

}