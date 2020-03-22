package models.pieces;

import java.util.Map;

public class AttackerEagle extends AbstractPiece{


	public AttackerEagle(int x, int y) {
		super(x, y);
	}

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

		Map<String, Integer> currentPosition= this.getPosition();
		
		if(newX > currentPosition.get("x") + 1 || newY > currentPosition.get("y") + 1 || 
				newX < currentPosition.get("x") - 1 || newY < currentPosition.get("y") - 1) {
			return false;
		}
		else {
			setPosition(newX, newY);
			return true;
		}
	}
}
