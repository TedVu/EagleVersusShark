package model.piece;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import model.contract.Piece;

/**
 * @author sefira & chanboth
 *
 */
public abstract class AbstractPiece implements Piece, Serializable {

	private static final long serialVersionUID = -564937938237634780L;

	private Map<String, Integer> position = new HashMap<String, Integer>();
	private boolean isActive = true;
	private boolean isImmune = false;
	private int modeUsageCount = 0;

	public AbstractPiece(int x, int y) {
		position.put("x", x);
		position.put("y", y);
		isActive = true;
	}

	@Override
	public void setImmune(boolean isImmune) {
		this.isImmune = isImmune;
	}

	@Override
	public boolean isImmune() {
		return this.isImmune;
	}

	@Override
	@Requires({ "position.get(\"x\") != null && position.get(\"y\") != null" })
	@Ensures("getPosition() != null")
	public Map<String, Integer> getPosition() {
		return this.position;
	}

	@Override
	@Requires({ "this.isActive  == true || this.isActive == false" })
	public boolean isActive() {
		return this.isActive;
	}

	@Override
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	@Requires({ "x>=0", "y>=0" })
	@Ensures({ "position.get(\"x\")==x", "position.get(\"y\")==y" })
	public void setPosition(int x, int y) {
		this.position.replace("x", x);
		this.position.replace("y", y);
	}

	public PieceMemento pieceMemento() {
		PieceMemento memento = new PieceMemento(isActive, isImmune, position.get("x"), position.get("y"),
				modeUsageCount);
		return memento.getState();
	}

	@Override
	public void modeUsed() {
		modeUsageCount += 1;
	}

	@Override
	public void setModeCount(int modeCount) {
		this.modeUsageCount = modeCount;
	}

	@Override
	public int getModeCount() {
		return modeUsageCount;
	}

}
