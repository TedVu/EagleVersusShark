package model.piece;

import java.util.Set;

import model.board.Cell;

public abstract class AbstractEagle extends AbstractPiece {

	public AbstractEagle(int x, int y) {
		super(x, y);
	}
	
	@Override
	public Set<Cell> modeCells() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void useMode(int x, int y) {
		// TODO Auto-generated method stub
		
		// validation, then call move piece
		
	}

}
