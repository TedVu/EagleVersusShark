
package models.pieces;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import models.engine.EngineImpl;

public class VisionaryEagle extends AbstractPieceMoveDiagonal{

	public VisionaryEagle(int x, int y) {
		super(x, y);
	}

	private boolean canSwapPosition = true;

	public boolean isCanSwapPosition() {
		// TODO Auto-generated constructor stub
		return canSwapPosition;
	}

	public void setCanSwapPosition(boolean canSwapPosition) {
		// TODO Auto-generated constructor stub
		this.canSwapPosition = canSwapPosition;
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

		setPosition(newX, newY);
		return true;

	}

	// usage: piece.useAbility("swap", piece, affectedPiece)
	@Override
	public boolean useAbility(String abilityName, Piece piece, Piece affectedPiece) {
		if (abilityName.equals("swap"))
			return swap(piece, affectedPiece);

		return false;
	}

	public boolean swap(Piece piece, Piece affectedPiece) {

		try {
			Map<String, Integer> position1 = new HashMap<String, Integer>();
			Map<String, Integer> position2 = new HashMap<String, Integer>();

			position1.putAll(piece.getPosition());
			position2.putAll(affectedPiece.getPosition());

			piece.setPosition(position2.get("x"), position2.get("y"));
			affectedPiece.setPosition(position1.get("x"), position1.get("y"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean capture(Piece capturingPiece, Piece capturedPiece) {
		throw new IllegalArgumentException("this piece can't capture opponent!");
	}

}
