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
public class UseMode implements CommandInterface {
	
	private int newX, newY, oldX, oldY;
	private PieceInterface piece;
	private EngineInterface engine = EngineImpl.getSingletonInstance();
	private PieceOperator pieceOperator = engine.pieceOperator();
	
	public UseMode(int newX, int newY, PieceInterface piece) {
		this.newX = newX;
		this.newY = newY;
		this.oldX = piece.getPosition().get("x");
		this.oldY = piece.getPosition().get("y");
		this.piece = piece;
	}

	@Override
	public void execute() {
		pieceOperator.useMode(piece, newX, newY);
		pieceOperator.addEvt(this);
	}

	@Override
	public void undo() {

		pieceOperator.useMode(piece, oldX, oldY);
		engine.getBoard().removePiece(newX, newY);
		engine.getBoard().addPiece(oldX, oldY);
	}

}
