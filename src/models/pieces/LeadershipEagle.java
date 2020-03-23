
package models.pieces;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class LeadershipEagle extends AbstractPiece{

	public LeadershipEagle(int x, int y) {
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
		
		int currX = currentPosition.get("x");
		int currY = currentPosition.get("y");
		
		if(newX > currentPosition.get("x") + 2 || newY > currentPosition.get("y") + 2 || 
				newX < currentPosition.get("x") - 2 || newY < currentPosition.get("y") - 2) {
			return false;
		}
		else if((newX == currX +1 || newX == currX +2 || newX == currX - 1 || newX == currX - 2) &&
				newY != currY) {
			return false;
		}
		else {
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


