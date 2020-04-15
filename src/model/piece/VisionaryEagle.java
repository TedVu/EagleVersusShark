
package model.piece;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.board.Cell;

/**
 * @author sefira & kevin
 *
 */
public class VisionaryEagle extends AbstractPiece {

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
	public Set<Cell> getValidMove() {
		Map<String, Integer> currentPosition = this.getPosition();
		int currentX = currentPosition.get("x");
		int currentY = currentPosition.get("y");
		Set<Cell> validMoves = new HashSet<Cell>();
		validMoves.addAll(validMovesSouth(currentX, currentY, 2));
		validMoves.addAll(validMovesNorth(currentX, currentY, 2));
		validMoves.addAll(validMovesEast(currentX, currentY, 2));
		validMoves.addAll(validMovesWest(currentX, currentY, 2));
		validMoves.addAll(validDiaNorthEast(currentX, currentY, 2));
		validMoves.addAll(validDiaSouthWest(currentX, currentY, 2));
		validMoves.addAll(validDiaSouthEast(currentX, currentY, 2));
		validMoves.addAll(validDiaNorthWest(currentX, currentY, 2));
		return validMoves;
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
