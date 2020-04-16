package model.piece;

import java.util.Set;
import model.board.Cell;
import model.piece.movement.BasicMove;

/**
 * @author chanboth
 *
 */
public class AggressiveShark extends AbstractPiece{
	

	public AggressiveShark(int x, int y) {
		super(x, y);
	}

	@Override
	public void movePiece(int x, int y) {
		setPosition(x, y);
	}
	
	@Override
	public Set<Cell> getValidMove() {
		return  new BasicMove().getValidMove(this, 1);
	}


}
