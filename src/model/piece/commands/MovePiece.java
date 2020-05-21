package model.piece.commands;

import java.io.Serializable;

import model.contract.Command;
import model.contract.Engine;
import model.contract.Piece;
import model.engine.EngineImpl;
import model.piece.GamePiece;

/**
 *
 * @author Sefira
 *
 */
public class MovePiece implements Command, Serializable {

	private static final long serialVersionUID = -5608224361103079900L;
	private int newX, newY, oldX, oldY;
	private Piece piece;
	private Engine engine = EngineImpl.getSingletonInstance();
	private PieceCommands pieceCommands = engine.getPieceCommands();
	private boolean isMode; 

	public MovePiece(int newX, int newY, Piece piece, boolean isMode) {
		this.newX = newX;
		this.newY = newY;
		this.oldX = piece.getPosition().get("x");
		this.oldY = piece.getPosition().get("y");
		this.piece = piece;
		this.isMode = isMode;
	}

	@Override
	public void execute() {
		pieceCommands.movePiece(piece, newX, newY, isMode);
		pieceCommands.addEvt(this);
	}

	@Override
	public void undo() {

		pieceCommands.movePiece(piece, oldX, oldY, isMode);
//		engine.getBoard().removePiece(newX, newY);
//		engine.getBoard().addPiece(oldX, oldY);
	}

}
