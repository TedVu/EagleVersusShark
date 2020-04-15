package models.pieces;

/**
 * @author sefira & kevin
 *
 */
public class AttackingEagle extends AbstractPieceMoveDiagonal {

	/**
	 * @param x
	 * @param y
	 */
	public AttackingEagle(int x, int y) {
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
