package model.piece;

import java.util.HashMap;
import java.util.Map;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import model.contract.PieceInterface;

/**
 * @author sefira & chanboth
 *
 */
public abstract class AbstractPiece implements PieceInterface {

	private Map<String, Integer> position = new HashMap<String, Integer>();
	private boolean isActive;

	public AbstractPiece(int x, int y) {
		position.put("x", x);
		position.put("y", y);
		isActive = true;
	}

	@Override
	public Map<String, Integer> getPosition() {
		return this.position;
	}

	@Override
	public boolean isActive() {
		return this.isActive;
	}

	@Override
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	@Requires({"x>=0", "y>=0"})
	@Ensures({"position.get(\"x\")==x", "position.get(\"y\")==y"})
	public void setPosition(int x, int y) {
		this.position.replace("x", x);
		this.position.replace("y", y);
	}

}
