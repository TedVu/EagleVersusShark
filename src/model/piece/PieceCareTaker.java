package model.piece;

import java.util.ArrayList;
import java.util.List;

public class PieceCareTaker {
	
		private List<PieceMemento> mementoList = new ArrayList<PieceMemento>();
		private int i = 0;

	   public int add(PieceMemento pieceMemento){
	      mementoList.add(pieceMemento);
	      i++;
	      return i - 1 ;
	   }

	   public PieceMemento get(int index){
	      return mementoList.get(index);
	   }

}
