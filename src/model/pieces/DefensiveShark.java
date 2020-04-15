package model.pieces;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author chanboth
 *
 */
public class DefensiveShark extends AbstractPiece {
	/**
	 * @param x
	 * @param y
	 */
	public DefensiveShark(int x, int y) {
		super(x, y);
	}

	/**
	 *
	 */
	@Override
	public Set<List<Integer>> getValidMove() {
		Map<String, Integer> currentPosition = this.getPosition();
		int currentX = currentPosition.get("x");
		int currentY = currentPosition.get("y");
		Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
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