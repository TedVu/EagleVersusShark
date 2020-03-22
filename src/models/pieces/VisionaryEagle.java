package models.pieces;

import java.util.Map;

public class VisionaryEagle extends AbstractEagle {

	public VisionaryEagle(int x, int y) {
		super(x, y);
	}

	/*
	 * 
	 */
	@Override
	public Map<String, Integer> move(int x, int y) {

		// insert code to check if the coordinate is valid for this piece type
		System.out.println("moving visionary eagle from VisionaryEagle class");

		setPosition(x, y);
		return getPosition();
	}

	// Get the Board and doing some Validation here
	@Override
	public boolean movePieceTed(int newX, int newY) {
		// TODO Auto-generated method stub
		return true;
	}

}
