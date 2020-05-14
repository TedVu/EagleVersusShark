 package model.piece.commands;

import java.io.Serializable;

import model.contract.CommandInterface;
import model.contract.EngineInterface;
import model.engine.EngineImpl;
import model.enumtype.TeamType;
import model.piece.PieceOperator;

/**
 *
 * @author Sefira
 *
 */
public class Undo implements CommandInterface, Serializable  {

	private static final long serialVersionUID = -1824704997694754116L;
	private EngineInterface engine = EngineImpl.getSingletonInstance();
	private PieceCommands pieceCommands = engine.pieceCommands();
	private TeamType teamType;
	private int undoNum;

	public Undo(TeamType teamType, int undoNum) {
		this.teamType = teamType;
		this.undoNum = undoNum;
	}

	@Override
	public void execute() {

		try {
			pieceCommands.undo(undoNum, teamType);

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void undo() {
		throw new RuntimeException("unable to redo");
	}

}
