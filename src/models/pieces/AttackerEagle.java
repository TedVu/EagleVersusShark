package models.pieces;

import java.util.Map;

public class AttackerEagle extends AbstractEagle {

	public AttackerEagle(int x, int y) {
		super(x, y);
	}

	/*
	 * 
	 */
	@Override
	public Map<String, Integer> move(int x, int y) {

		// insert code to check if the coordinate is valid for this piece type
		System.out.println("moving attacker eagle from AttackerEagle class");

		setPosition(x, y);
		return getPosition();
	}

	// Get the Board and doing some Validation here
	@Override
	public boolean movePieceTed(int newX, int newY) {

		return true;
	}

}
