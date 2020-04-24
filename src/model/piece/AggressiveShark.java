package model.piece;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.java.contract.Requires;

import model.board.Cell;
import model.contract.PieceInterface;
import model.enumtype.PieceAbility;
import model.piece.movement.BasicMove;

/**
 * @author chanboth
 *
 */
public class AggressiveShark extends AbstractPiece  {

	public AggressiveShark(int x, int y) {
		super(x, y);
	}

	@Override
	public Set<Cell> getValidMove() {
		return new BasicMove().getValidMove(this, 1);
	}

	@Override
	@Requires({"x>=0", "y>=0"})
	public void movePiece(int x, int y) {
		setPosition(x, y);
	}

	@Override
	public void useAbility(PieceAbility pieceAbility, PieceInterface piece, PieceInterface affectedPiece) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Set<Cell> abilityCells() {
		return null;
		
	}


	

}
