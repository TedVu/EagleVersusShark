package models.pieces;

import java.util.Map;

public class AttackerEagle extends AbstractPiece{

	public AttackerEagle(int x, int y) {
		super(x, y);
	}
	
	/*
	 * 
	 */
	@Override
	public Map<String, Integer> move(int x, int y) {
		//board
		int row=5;
		int col=5;
		
		
		
//		insert code to check if the coordinate is valid for this piece type
		System.out.println("moving attacker eagle from AtatckerEagle class");
		
		setPosition(x, y);
		return getPosition();
	}
	
	



}
