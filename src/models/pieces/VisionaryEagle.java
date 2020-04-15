
package models.pieces;

public class VisionaryEagle extends AbstractPieceMoveDiagonal{

	/**
	 * @param x
	 * @param y
	 */
	public VisionaryEagle(int x, int y) {
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
