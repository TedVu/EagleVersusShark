package model.piece;

import java.util.Set;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import model.board.Cell;
import model.piece.movement.DiagonalMove;

/**
 * @author sefira
 *
 */
public class AttackingEagle extends AbstractPiece {

	public AttackingEagle(int x, int y) {
		super(x, y);
	}

	@Override
	@Requires({"getPosition() != null"})
	@Ensures("getValidMove() != null")
	public Set<Cell> getValidMove() {
		return new DiagonalMove().getValidMove(this, 1);
	}

	@Override
	@Requires({"getPosition() != null"})
	@Ensures("getPosition().get(\"x\") == x && getPosition().get(\"y\") == y")
	public void movePiece(int x, int y) {
		setPosition(x, y);
	}
}
