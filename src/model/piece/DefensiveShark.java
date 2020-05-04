package model.piece;

import java.util.Set;

import model.board.Cell;
import model.contract.PieceInterface;
import model.enumtype.PieceAbility;
import model.piece.movement.DiagonalMove;

/**
 * @author chanboth
 *
 */
public class DefensiveShark extends AbstractPiece {

	private static final long serialVersionUID = -3824904265692727849L;

	public DefensiveShark(int x, int y) {
		super(x, y);
	}

	@Override
	public Set<Cell> getValidMove() {
		return new DiagonalMove().getValidMove(this, 2);

	}

	@Override
	public void movePiece(int x, int y) {
		setPosition(x, y);
	}

	@Override
	public void useAbility(PieceAbility pieceAbility, PieceInterface piece, PieceInterface affectedPiece) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Cell> abilityCells() {
		return null;

	}

	@Override
	public String toString() {
		return String.format("%s", "DefensiveShark");
	}

}