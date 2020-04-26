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
public abstract class AbstractPiece implements PieceInterface{

	private Map<String, Integer> position = new HashMap<String, Integer>();
	private boolean isActive = true;
	private boolean isImmune = false;

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
	@Requires({"position.get(\"x\") != null && position.get(\"y\") != null"})
	@Ensures("getPosition() != null")
	public Map<String, Integer> getPosition() {
		return this.position;
	}

	@Override
	@Requires({"this.isActive  == true || this.isActive == false"})
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

	public PieceMemento pieceMemento() {
		PieceMemento memento =  new PieceMemento(isActive, isImmune, position.get("x"), position.get("y"));
		return memento.getState();
	}

//	@Override
//	protected Object clone() throws CloneNotSupportedException {
//		PieceInterface clone = (PieceInterface)super.clone();
//		
//		Map<String, Integer> position = new HashMap<String, Integer>();
//		position.put("x", clone.getPosition().get("x"));
//		position.put("y", clone.getPosition().get("y"));
//		clone.setPosition(position.get("x"), position.get("y"));
//		return clone;
//	}
//	
	

}
