package model.piece.commands;

import model.contract.CommandInterface;
import model.contract.EngineInterface;
import model.contract.PieceInterface;
import model.engine.EngineImpl;

/**
*
* @author Sefira
*
*/
public class MovePiece implements CommandInterface {
	
	private int newX, newY, oldX, oldY;
	private PieceInterface piece;
	private EngineInterface engine = EngineImpl.getSingletonInstance();
	private PieceOperator pieceOperator = engine.pieceOperator();
	
	public MovePiece(int newX, int newY, PieceInterface piece) {
		this.newX = newX;
		this.newY = newY;
		this.oldX = piece.getPosition().get("x");
		this.oldY = piece.getPosition().get("y");
		this.piece = piece;
	}

	@Override
	public void execute() {
		pieceOperator.movePiece(piece, newX, newY);
		pieceOperator.addEvt(this);
	}

	@Override
	public void undo() {

		System.out.println("moving piece to " +  oldX + " "+ oldY);
		pieceOperator.movePiece(piece, oldX, oldY);
		engine.getBoard().removePiece(newX, newY);
		engine.getBoard().addPiece(oldX, oldY);
	}

}
