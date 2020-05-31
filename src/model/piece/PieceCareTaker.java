package model.piece;

import java.util.ArrayList;
import java.util.List;

import com.google.java.contract.Requires;

public class PieceCareTaker {

	private List<PieceMemento> mementoList = new ArrayList<PieceMemento>();
	private int i = 0;
	
	@Requires({ "pieceMemento!= null" })
	public int add(PieceMemento pieceMemento) {
		mementoList.add(pieceMemento);
		i++;
		return i - 1;
	}

	@Requires({ "index>= 0" })
	public PieceMemento get(int index) {
		return mementoList.get(index);
	}

}
