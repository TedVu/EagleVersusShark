package model.piece.commands;

import java.io.Serializable;
import java.util.Stack;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import model.board.Board;
import model.contract.CommandInterface;
import model.contract.PieceInterface;
import model.engine.EngineImpl;
import model.enumtype.PieceAbility;
import model.enumtype.TeamType;
import model.piece.AttackingEagle;
import model.piece.HealingShark;
import model.piece.PieceMemento;

/**
 *
 * @author Sefira
 *
 */
public class PieceCommands implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1506663538384796618L;

	private EngineImpl engine;

	private Board board;

	private Stack<CommandInterface> commandHistory = new Stack<CommandInterface>();

	public PieceCommands(EngineImpl engine) {
		this.engine = engine;
		this.board = engine.getBoard();
	}

	protected void replacePieceVersion(PieceInterface piece, PieceMemento prevState) {
		piece.setActive(prevState.isActive());
		piece.setImmune(prevState.isImmune());
		piece.setPosition(prevState.getX(), prevState.getY());
		piece.setModeCount(prevState.getModeCount());

		board.removePiece(piece.getPosition().get("x"), piece.getPosition().get("y"));
		board.addPiece(prevState.getX(), prevState.getY());
	}

	/**
	 * @param piece
	 *            - the piece to be moved
	 * @param newX
	 *            - new x position
	 * @param newY
	 *            - new y position Generate the pieces and put them on the board
	 */
	@Requires({ "piece != null", "x>=0", "y>=0" })
	@Ensures({ "piece.getPosition().get(\"x\") != null && piece.getPosition().get(\"y\") != null" })
	public void movePiece(PieceInterface piece, int x, int y, boolean isMode) {
		if (isMode && piece instanceof HealingShark && piece.getModeCount() > 0) {
			throw new IllegalArgumentException("Mode is already used");
		}
		board.removePiece(piece.getPosition().get("x"), piece.getPosition().get("y"));
		board.addPiece(x, y);
		piece.movePiece(x, y);
		if (isMode)
			piece.modeUsed();
	}

	protected void useAbility(PieceAbility pieceAbility, PieceInterface piece, PieceInterface affectedPiece,
			boolean isMode) {
		if (isMode && piece instanceof AttackingEagle && piece.getModeCount() > 0) {
			throw new IllegalArgumentException("Mode is already used");
		}
		piece.useAbility(pieceAbility, piece, affectedPiece);
		if (isMode)
			piece.modeUsed();
	}

	protected void undo(int undoNum, TeamType teamType) {

		if (engine.ableToUndo(teamType)) {

			int availableUndo = commandHistory.size() / 2;
			if (availableUndo < 1)
				throw new RuntimeException("Nothing to undo");
			else if (availableUndo < undoNum) {
				throw new IllegalArgumentException("Only able to undo " + availableUndo + " time(s)");
			} else {
				for (int i = 0; i < undoNum * 2; i++) {
					commandHistory.peek().undo();
					commandHistory.pop();
				}
			}
			engine.incrementUndo(teamType);
		} else {
			throw new RuntimeException("You already used undo");
		}
	}

	protected void addEvt(CommandInterface command) {
		commandHistory.push(command);
	}

	public void setBoard(Board board) {
		this.board = board;
	}

}
