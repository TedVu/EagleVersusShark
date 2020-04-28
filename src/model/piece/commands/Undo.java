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

	public Undo(TeamType teamType) {
		this.teamType = teamType;
	}

	@Override
	public void execute() {

		if (engine.ableToUndo(teamType)) {
			try {
				pieceOperator.undo();
				// rollback the board based on activeSharks/activeEagles,position of each piece
				// so that view can use that information to reconstruct the board view

			} catch (Exception e) {
				throw e;
			}
			engine.incrementUndo(teamType);
		} else {
			throw new RuntimeException("Unable to undo");
		}
	}

	@Override
	public void undo() {
		throw new RuntimeException("unable to redo");
	}

}
