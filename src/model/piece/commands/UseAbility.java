package model.piece.commands;

import model.contract.CommandInterface;
import model.contract.EngineInterface;
import model.contract.PieceInterface;
import model.engine.EngineImpl;
import model.enumtype.PieceAbility;

/**
*
* @author Sefira
*
*/
public class UseAbility implements CommandInterface {
	
	PieceAbility pieceAbility;
	private PieceInterface piece, affectedPiece;
	private EngineInterface engine = EngineImpl.getSingletonInstance();
	private PieceOperator pieceOperator = engine.pieceOperator();
	
	public UseAbility(PieceAbility pieceAbility, PieceInterface piece, PieceInterface affectedPiece) {
		this.affectedPiece = affectedPiece;
		this.piece = piece;
		this.pieceAbility = pieceAbility;
		
	}

	@Override
	public void execute() {
		pieceOperator.useAbility(pieceAbility, piece, affectedPiece);;
		pieceOperator.addEvt(this);
	}

	@Override
	public void undo() {
		// TODO
	}

}
