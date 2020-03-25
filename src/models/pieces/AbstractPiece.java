package models.pieces;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractPiece implements Piece {

	private Map<String, Integer> position = new HashMap<String, Integer>();
	private boolean isActive = true;

	/*
	 * contructor for initial eagle creation
	 * 
	 * @param int x - x coordinate
	 * 
	 * @param int y - y coordinate
	 */
	public AbstractPiece(int x, int y) {
		position.put("x", x);
		position.put("y", y);
	}

	/*
	 * modify the position of the piece
	 * 
	 * @param int x - x coordinate
	 * 
	 * @param int y - y coordinate
	 */
	@Override
	public void setPosition(int x, int y) {
		this.position.replace("x", x);
		this.position.replace("y", y);
	}

	/*
	 * get the current position of the piece
	 * 
	 * @return Map<String, Integer> - {"x" : , "y" : }
	 */
	@Override
	public Map<String, Integer> getPosition() {
		return this.position;

	}

	/*
	 * 
	 */
	@Override
	public void setActive(boolean isActive) {
		this.isActive = isActive;

	}

	/*
	 * 
	 */
	@Override
	public boolean isActive() {
		return this.isActive;
	}

}
