package models.pieces;

import java.util.Map;

public class AttackerEagle extends AbstractEagle{

	public AttackerEagle(int x, int y) {
		super(x, y);
	}
	
	/*
	 * 
	 */
	@Override
	public Map<String, Integer> move(int x, int y) {
		
//		insert code to check if the coordinate is valid for this piece type
		System.out.println("moving attacker eagle from AtatckerEagle class");
		
		setPosition(x, y);
		return getPosition();
	}



}
