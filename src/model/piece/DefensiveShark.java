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
public class DefensiveShark extends AbstractPiece {

	final int DISTANCE = 1;
	private EngineInterface engine;

	public DefensiveShark(int x, int y, EngineInterface engine) {
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
		} else if (pieceAbility.equals(PieceAbility.QUICKMOVE)){
			//TODO quick move to neighbouring cell of selected shark
		} else{
			throw new IllegalArgumentException("Invalid ability");
		}
	}

	private void defend(PieceInterface targetPiece) {
		try {
			if (targetPiece.isImmune())
				throw new IllegalArgumentException("The piece is immune");

			targetPiece.setImmune(true);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	@Ensures("abilityCells() != null")
	public Set<Cell> abilityCells() {
		Set<Cell> neighbourCells = new HashSet<>();
		List<PieceInterface> activeSharks = engine.pieceOperator().getActiveSharks();

		// Return all the neighbouring cells around other sharks
		for (PieceInterface shark : activeSharks) {
			if(!(shark instanceof DefensiveShark)) {
				int x = shark.getPosition().get("x");
				int y = shark.getPosition().get("y");

				// All the neighbour cells around a shark
				// Initial implementation only, MUST undergo code review and refactor
				Cell topLeft = new Cell(x - 1, y - 1);
				Cell top = new Cell(x, y - 1);
				Cell topRight = new Cell(x + 1, y - 1);
				Cell right = new Cell(x + 1, y);
				Cell bottomRight = new Cell(x + 1, y + 1);
				Cell bottom = new Cell(x, y + 1);
				Cell bottomLeft = new Cell(x - 1, y + 1);

				neighbourCells.add(topLeft);
				neighbourCells.add(top);
				neighbourCells.add(topRight);
				neighbourCells.add(right);
				neighbourCells.add(bottomRight);
				neighbourCells.add(bottom);
				neighbourCells.add(bottomLeft);
			}
		}

		return neighbourCells;
	}
	
	@Override
	public String toString() {
		return String.format("%s", "DefensiveShark");
	}
	
	
}