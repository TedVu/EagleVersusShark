package model.piece.commands;

import model.contract.CommandInterface;
import model.contract.EngineInterface;
import model.engine.EngineImpl;
import model.enumtype.TeamType;

/**
 *
 * @author Sefira
 *
 */
public class Undo implements CommandInterface {

	private EngineInterface engine = EngineImpl.getSingletonInstance();
	private PieceOperator pieceOperator = engine.pieceOperator();
	private TeamType teamType;
	private int undoNum;

	public Undo(TeamType teamType, int undoNum) {
		this.teamType = teamType;
		this.undoNum = undoNum;
	}

	@Override
	public void execute() {

		// team type to check undoability
		if (engine.ableToUndo(teamType)) {
			try {
				pieceOperator.undo(undoNum);	
				engine.incrementUndo(teamType);
				
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new RuntimeException("You already used undo");
		}
	}

	@Override
	public void undo() {
		throw new RuntimeException("unable to redo");
	}

}
