
package model.piece;

import java.util.Set;

import javax.management.RuntimeErrorException;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import model.board.Cell;
import model.contract.PieceInterface;
import model.enumtype.PieceAbility;
import model.piece.movement.BasicMove;

/**
 * @author sefira & kevin
 *
 */
public class LeadershipEagle extends AbstractPiece {

	public LeadershipEagle(int x, int y) {
		super(x, y);
	}

	@Override
	@Requires({"getPosition() != null"})
	@Ensures("getValidMove() != null")
	public Set<Cell> getValidMove() {
		return new BasicMove().getValidMove(this, 2);
	}

	@Override
	@Requires({"getPosition() != null"})
	@Ensures("getPosition().get(\"x\") == x && getPosition().get(\"y\") == y")
	public void movePiece(int x, int y) {
		setPosition(x, y);
	}

	@Override
	public void useAbility(PieceAbility pieceAbility, PieceInterface piece, PieceInterface affectedPiece) {
		
		if (pieceAbility.equals(PieceAbility.PROTECT))
			protect(piece, affectedPiece);

		else {
			throw new IllegalArgumentException("Invalid ability");
		}
		
	}
	
	@Override
	public Set<Cell> abilityCells() {
		return null;
		
	}
	
	private void protect(PieceInterface piece, PieceInterface affectedPiece) {
		
		try {
			
			if(affectedPiece.isImmune())
				throw new IllegalArgumentException("Chosen piece is already immune");

			int pieceX = piece.getPosition().get("x");
			int pieceY = piece.getPosition().get("y");

			int affectedPieceX = affectedPiece.getPosition().get("x");
			int affectedPieceY = affectedPiece.getPosition().get("y");

			if(!isSurrounding(pieceX, affectedPieceX, pieceY, affectedPieceY, 1)) {
				throw new IllegalArgumentException("Invalid position to protect");
			}

			affectedPiece.setImmune(true);

		} catch (Error e) {
			throw new RuntimeErrorException(e);
		}
		
	}
	
	private boolean isSurrounding(int x1, int x2, int y1, int y2, int distance) {
		if (x2 > x1 + distance || y2 > y1 + distance || x2 < x1 - distance || y2 < y1 - distance) {
			return false;
		}
		return true;
	}
}
