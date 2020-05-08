package model.piece.commands;

import java.io.Serializable;

import model.contract.CommandInterface;
import model.contract.EngineInterface;
import model.contract.PieceInterface;
import model.engine.EngineImpl;
import model.enumtype.PieceAbility;
import model.piece.PieceMemento;

/**
 *
 * @author Sefira
 *
 */
public class UseAbility implements CommandInterface, Serializable {

	private static final long serialVersionUID = 6907153654562703481L;
	PieceAbility pieceAbility;
	private PieceInterface piece, affectedPiece;
	private EngineInterface engine = EngineImpl.getSingletonInstance();
	private PieceOperator pieceOperator = engine.pieceOperator();
	private PieceMemento pieceMemento, affectedPieceMemento;
	private boolean isMode;

	public UseAbility(PieceAbility pieceAbility, PieceInterface piece, PieceInterface affectedPiece, boolean isMode) {
		this.affectedPiece = affectedPiece;
		this.piece = piece;
		this.pieceMemento = piece.pieceMemento();
		this.affectedPieceMemento = affectedPiece.pieceMemento();
		this.pieceAbility = pieceAbility;
		this.isMode = isMode;
	}

	@Override
	public void execute() {
		pieceOperator.addEvt(this);
		pieceOperator.useAbility(pieceAbility, piece, affectedPiece, isMode);

	}

	@Override
	public void undo() {

		pieceOperator.replacePieceVersion(piece, pieceMemento);
		pieceOperator.replacePieceVersion(affectedPiece, affectedPieceMemento);
		engine.getBoard().removePiece(piece.getPosition().get("x"), piece.getPosition().get("y"));
		engine.getBoard().addPiece(pieceMemento.getX(), pieceMemento.getY());
		engine.getBoard().removePiece(affectedPiece.getPosition().get("x"), affectedPiece.getPosition().get("y"));
		engine.getBoard().addPiece(affectedPieceMemento.getX(), affectedPieceMemento.getY());
	}

}
