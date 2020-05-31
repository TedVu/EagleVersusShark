package model.piece.commands;

import java.io.Serializable;

import com.google.java.contract.Requires;

import model.contract.Command;
import model.contract.Engine;
import model.contract.Piece;
import model.engine.EngineImpl;

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
	@Requires({ "piece!=null", "newX>=0", "newY>=0" })
	public void execute() {
		pieceCommands.movePiece(piece, newX, newY, isMode);
		pieceCommands.addEvt(this);
	}

	@Override
	public void undo() {
		pieceCommands.movePiece(piece, oldX, oldY, isMode);
	}

}
