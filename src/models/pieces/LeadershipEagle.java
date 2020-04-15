
package models.pieces;

public class LeadershipEagle extends AbstractPieceMove {

	public LeadershipEagle(int x, int y) {
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
