package models.pieces;


import java.util.Map;


public class VisionaryEagle extends AbstractPiece{
	
	private boolean canSwapPosition = true;

	public VisionaryEagle(int x, int y) {
		super(x, y);
	}
	
	public boolean isCanSwapPosition() {
		return canSwapPosition;
	}

	public void setCanSwapPosition(boolean canSwapPosition) {
		this.canSwapPosition = canSwapPosition;
	}
	
	/*
	 * 
	 */
	@Override
	public Map<String, Integer> move(int x, int y) {

//		insert code to check if the coordinate is valid for this piece type
		
		System.out.println("moving visionary eagle from VisionaryEagle class");
		
		int row=5;
		int col=5;
//		
//		if(x > row + 2 || y > col + 2 || x < row - 2 || y < col - 2) {
//			throw new IllegalArgumentException("visionary eagle can only move 2 blocks all around");
//		}
		
		setPosition(x, y);
		return getPosition();
	}
	
	



}
