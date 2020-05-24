package model.piece.commands;

import java.io.Serializable;

import model.contract.Command;
import model.contract.Engine;
import model.engine.EngineImpl;
import model.engine.GamePiece;
import model.enumtype.TeamType;

/**
 *
 * @author Sefira
 *
 */
public class Undo implements Command, Serializable {

	private static final long serialVersionUID = -1824704997694754116L;
	private Engine engine = EngineImpl.getSingletonInstance();
	private PieceCommands pieceCommands = engine.getPieceCommands();
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
