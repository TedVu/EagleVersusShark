
package model.piece;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.board.Cell;

/**
 * @author sefira & kevin
 *
 */
public class LeadershipEagle extends AbstractPiece {
	/**
	 * @param x
	 * @param y
	 */
	public LeadershipEagle(int x, int y) {
		super(x, y);
	}

	/**
	 *
	 */
	@Override
	public Set<Cell> getValidMove() {
		Map<String, Integer> currentPosition = this.getPosition();
		int currentX = currentPosition.get("x");
		int currentY = currentPosition.get("y");
		Set<Cell> validMoves = new HashSet<Cell>();
		validMoves.addAll(validMovesSouth(currentX, currentY, 2));
		validMoves.addAll(validMovesNorth(currentX, currentY, 2));
		validMoves.addAll(validMovesEast(currentX, currentY, 2));
		validMoves.addAll(validMovesWest(currentX, currentY, 2));
		return validMoves;
	}

	/**
	 *
	 */
	@Override
	public void movePiece(int x, int y) {
		setPosition(x, y);
	}
}
