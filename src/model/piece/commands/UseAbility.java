package model.piece.commands;

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
public class UseAbility implements CommandInterface {
	
	PieceAbility pieceAbility;
	private PieceInterface piece, affectedPiece, prevPiece, prevAffectedPiece;
	private EngineInterface engine = EngineImpl.getSingletonInstance();
	private PieceOperator pieceOperator = engine.pieceOperator();
	private PieceMemento pieceMemento, affectedPieceMemento;
	
	public UseAbility(PieceAbility pieceAbility, PieceInterface piece, PieceInterface affectedPiece) {
		this.affectedPiece = affectedPiece;
		this.piece = piece;
		this.pieceMemento = piece.pieceMemento();
		this.affectedPieceMemento = affectedPiece.pieceMemento();
//		this.prevPiece = piece.clonePiece();
		this.pieceAbility = pieceAbility;
		
		
		
	}

	@Override
	public void execute() {
		pieceOperator.addEvt(this);
		pieceOperator.useAbility(pieceAbility, piece, affectedPiece);
		

	}

	@Override
	public void undo() {

		pieceOperator.replacePieceVersion(piece, pieceMemento);
		pieceOperator.replacePieceVersion(affectedPiece, affectedPieceMemento);
	}

}
