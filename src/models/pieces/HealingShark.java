package models.pieces;

public class HealingShark extends AbstractPieceMoveDiagonal {

	public HealingShark(int x, int y) {
		super(x, y);
	}

	/**
	 *
	 */
	@Override
	public boolean movePiece(int x, int y) {
		setPosition(x, y);
		return true;
	}

}
