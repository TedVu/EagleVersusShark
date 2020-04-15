package models.pieces;

public class DefensiveShark extends AbstractPieceMoveDiagonal {
	public DefensiveShark(int x, int y) {
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