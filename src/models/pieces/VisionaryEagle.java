
package models.pieces;

import java.util.List;
import java.util.Map;
import java.util.Set;

import models.board.Board;
import models.engine.EngineImpl;

public class VisionaryEagle extends AbstractPiece {

	public VisionaryEagle(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	private boolean canSwapPosition = true;

	// not finish yet
	// public boolean isCanSwapPosition() {
	// return canSwapPosition;
	// }
	//
	// public void setCanSwapPosition(boolean canSwapPosition) {
	// this.canSwapPosition = canSwapPosition;
	// }

	/*
	 * validate the new position and set it if it's valid
	 * 
	 * @param int newX - new x position
	 * 
	 * @param int newY - new y position
	 * 
	 * @return position valid based on rule ? true : false
	 */
	@Override
	public boolean movePiece(int newX, int newY) {

		Map<String, Integer> currentPosition = this.getPosition();

		if (newX > currentPosition.get("x") + 2 || newY > currentPosition.get("y") + 2
				|| newX < currentPosition.get("x") - 2 || newY < currentPosition.get("y") - 2) {
			return false;
		} else {
			setPosition(newX, newY);
			return true;
		}

	}

	@Override
	public Set<List<Integer>> getValidMove() {
		// TODO Auto-generated method stub
		return null;
	}

}
