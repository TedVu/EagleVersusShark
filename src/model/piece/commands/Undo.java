package model.piece.commands;

import model.contract.CommandInterface;
import model.contract.EngineInterface;
import model.engine.EngineImpl;

/**
*
* @author Sefira
*
*/
public class Undo implements CommandInterface {
	

	private EngineInterface engine = EngineImpl.getSingletonInstance();
	private PieceOperator pieceOperator = engine.pieceOperator();
	
	public Undo() {
		
	}

	@Override
	public void execute() {
		pieceOperator.undo();
	}

	@Override
	public void undo() {
		throw new IllegalArgumentException("Unable to redo");
	}

}
