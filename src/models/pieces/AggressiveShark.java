package models.pieces;

public class AggressiveShark extends AbstractPieceMove{

	/**
	 * @param x
	 * @param y
	 */
	public AggressiveShark(int x, int y) {
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
