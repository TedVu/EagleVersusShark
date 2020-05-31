package model.piece.commands;

import java.io.Serializable;

import com.google.java.contract.Requires;

import model.contract.Command;
import model.contract.Engine;
import model.contract.Piece;
import model.engine.EngineImpl;
import model.enumtype.PieceAbility;
import model.piece.PieceMemento;

/**
 *
 * @author Sefira
 *
 */
public class UseAbility implements Command, Serializable {

	private static final long serialVersionUID = 6907153654562703481L;

	PieceAbility pieceAbility;
	private Piece piece, affectedPiece;
	private Engine engine = EngineImpl.getSingletonInstance();
	private PieceCommands pieceCommands = engine.getPieceCommands();
	private PieceMemento pieceMemento, affectedPieceMemento;
	private boolean isMode;

	@Requires({ "pieceAbility!=null", "piece!=null", "affectedPiece!=null" })
	public UseAbility(PieceAbility pieceAbility, Piece piece, Piece affectedPiece, boolean isMode) {
		this.affectedPiece = affectedPiece;
		this.piece = piece;
		this.pieceMemento = piece.pieceMemento();
		this.affectedPieceMemento = affectedPiece.pieceMemento();
		this.pieceAbility = pieceAbility;
		this.isMode = isMode;
	}

	@Override
	public void execute() {
		pieceCommands.useAbility(pieceAbility, piece, affectedPiece, isMode);
		pieceCommands.addEvt(this);
	}

	@Override
	public void undo() {
		pieceCommands.replacePieceVersion(piece, pieceMemento);
		pieceCommands.replacePieceVersion(affectedPiece, affectedPieceMemento);
	}

}
