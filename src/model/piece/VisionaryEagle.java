
package model.piece;

import java.util.Set;
import model.board.Cell;
import model.piece.movement.BasicMove;

/**
 * @author sefira
 *
 */
public class VisionaryEagle extends AbstractPiece {
	
	public VisionaryEagle(int x, int y) {
		super(x, y);
	}

	@Override
	public Set<Cell> getValidMove() {
		return new BasicMove().getValidMove(this, 2);
	}

	@Override
	public void movePiece(int x, int y) {
		setPosition(x, y);
	}
}
