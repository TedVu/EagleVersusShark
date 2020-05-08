package model.piece.commands;

import java.io.Serializable;

import model.contract.CommandInterface;
import model.contract.EngineInterface;
import model.contract.PieceInterface;
import model.engine.EngineImpl;

/**
 *
 * @author Sefira
 *
 */
public class MovePiece implements CommandInterface, Serializable {

	private static final long serialVersionUID = -5608224361103079900L;
	private int newX, newY, oldX, oldY;
	private PieceInterface piece;
	private EngineInterface engine = EngineImpl.getSingletonInstance();
	private PieceOperator pieceOperator = engine.pieceOperator();
	private boolean isMode;

	public MovePiece(int newX, int newY, PieceInterface piece, boolean isMode) {
		this.newX = newX;
		this.newY = newY;
		this.oldX = piece.getPosition().get("x");
		this.oldY = piece.getPosition().get("y");
		this.piece = piece;
		this.isMode = isMode;
	}

	@Override
	public void execute() {
		pieceOperator.movePiece(piece, newX, newY, isMode);
		pieceOperator.addEvt(this);
	}

	@Override
	public void undo() {

		pieceOperator.movePiece(piece, oldX, oldY, isMode);
		engine.getBoard().removePiece(newX, newY);
		engine.getBoard().addPiece(oldX, oldY);
	}

}
