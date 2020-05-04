package model.piece;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import model.board.Cell;
import model.contract.EngineInterface;
import model.contract.PieceInterface;
import model.engine.EngineImpl;
import model.enumtype.PieceAbility;
import model.piece.movement.DiagonalMove;

/**
 * @author chanboth
 *
 */
public class DefensiveShark extends AbstractShark {

	final int NEIGHBOURING_DISTANCE = 1;
	private final EngineInterface engine;

	private static final long serialVersionUID = -3824904265692727849L;

	public DefensiveShark(int x, int y) {
		super(x, y);
		this.engine = engine;
	}

	@Override
	@Requires({ "getPosition() != null" })
	@Ensures("getValidMove() != null")
	public Set<Cell> getValidMove() {
		return new DiagonalMove().getValidMove(this, 2);
	}

	@Override
	@Requires({ "x>=0", "y>=0" })
	public void movePiece(int x, int y) {
		setPosition(x, y);
	}

	@Override
	public void useAbility(PieceAbility pieceAbility, PieceInterface piece, PieceInterface affectedPiece) {
		if (pieceAbility.equals(PieceAbility.PROTECT)) {
			defend(affectedPiece);
			if (EngineImpl.getSingletonInstance().pieceOperator().getHealingAbilityCounter() == 2) {
				EngineImpl.getSingletonInstance().pieceOperator().resetHealingAbilityCounter();
			}
		} else {
			throw new IllegalArgumentException("Invalid ability");
		}
	}

	private void defend(PieceInterface targetPiece) {
		targetPiece.setImmune(true);

	}

	@Override
	@Ensures("abilityCells() != null")
	public Set<Cell> abilityCells() {
		Set<Cell> neighbourCells = new HashSet<>();
		Set<Cell> surroundingEightCells = new HashSet<>();
		List<PieceInterface> activeSharks = engine.pieceOperator().getActiveSharks();

		// Return all the neighbouring cells around other sharks
		for (PieceInterface shark : activeSharks) {
			if (!(shark instanceof DefensiveShark)) {

				// All the neighbour cells around a shark - to be traversed

				surroundingEightCells.addAll(new DiagonalMove().getValidMove(shark, NEIGHBOURING_DISTANCE));
				Cell sharkPosition = new Cell(shark.getPosition().get("x"), shark.getPosition().get("y"));
				surroundingEightCells.add(sharkPosition);
			}
		}
		// Remove master cell
		surroundingEightCells.remove(EngineImpl.getSingletonInstance().getBoard().getSharkMasterCell());

		// * Add the cell to the return list if the following are true:
		// * Cell is not occupied
		// * Cell is within the board
		// *
		for (Cell possibleCell : surroundingEightCells) {
			if (!possibleCell.getOccupied() && possibleCell.getY() < engine.getBoard().getSize()
					&& possibleCell.getY() >= 0 && possibleCell.getX() < engine.getBoard().getSize()
					&& possibleCell.getX() >= 0) {
				neighbourCells.add(possibleCell);
			}
		}

		return neighbourCells;
	}

	@Override
	public String toString() {
		return String.format("%s", "DefensiveShark");
	}

}