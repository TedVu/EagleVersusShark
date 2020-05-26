package modelcontroller.facade;

import java.util.List;
import java.util.Map;

import com.google.java.contract.Requires;

import model.board.Cell;
import model.contract.Engine;
import model.contract.Piece;
import model.contract.Player;
import model.engine.EngineImpl;
import model.enumtype.PieceAbility;
import model.enumtype.PieceType;
import model.enumtype.TeamType;
import model.piece.commands.CommandExecutor;
import model.piece.commands.MovePiece;
import model.piece.commands.UseAbility;
import modelcontroller.contract.ControllerModelInterface;

/**
 * @author ted &#38; kevin
 *
 */
public class ControllerModelFacadeImpl implements ControllerModelInterface {

	private Engine engine = EngineImpl.getSingletonInstance();
	private CommandExecutor commandExecutor = new CommandExecutor();

	@Override
	public void cancelTimerPauseGame() {
		engine.gameTurn().cancelTimerPauseGame();

	}

	@Override
	public boolean checkCorrectTurnOfSelectedPiece(PieceType pieceType) {
		return engine.pieceOperator().checkSelectPiece(pieceType);
	}

	@Override
	public List<Piece> getActiveSharks() {
		return engine.pieceOperator().getActiveSharks();
	}

	@Override
	public Player getCurrentActivePlayer() {
		return engine.gameTurn().getCurrentActivePlayer();
	}

	@Override
	public boolean getGameCurrentlyRunning() {
		return engine.gameTurn().getGameCurrentlyRunning();
	}

	@Override
	public Player getInitialActivePlayer() {
		return engine.gameTurn().getInitialPlayerActivePlayer();
	}

	@Override
	public int getNumPiece() {
		return engine.pieceOperator().getAllPieces().size();
	}

	@Override
	public void setAlreadyUseUndo() {
		engine.gameTurn().getCurrentActivePlayer().setAlreadyUndo();
	}

	@Override
	public void setResumeGame() {
		engine.gameTurn().setResumeGame();
	}

	@Override
	public void setTurnStartingGame(TeamType teamType) {
		engine.gameTurn().setActivePlayerTimer(teamType);
	}

	@Override
	public void updateModelAfterHealingSharkUseMode(PieceType affectedPieceEnum) {
		Piece eagle = engine.pieceOperator().getAllPieces().get(affectedPieceEnum);
		Cell eagleRandomTopCell = engine.gameBoard().getAvailableTopEagleSideCell();
		commandExecutor
				.executeCommand(new MovePiece(eagleRandomTopCell.getX(), eagleRandomTopCell.getY(), eagle, true));
	}

	@Override
	@Requires({ "pieceType!=null", "newPos!=null", "newPos.size()>0" })
	public void updateModelAfterMovingPiece(Map<String, Integer> newPos, PieceType pieceType) {
		Piece pieceMoved = engine.pieceOperator().getAllPieces().get(pieceType);
		commandExecutor.executeCommand(new MovePiece(newPos.get("x"), newPos.get("y"), pieceMoved, false));
	}

	@Override
	public void updateModelAttackingEagleCapture(PieceType affectedPieceEnum, boolean isMode) {
		Piece affectedPiece = engine.pieceOperator().getAllPieces().get(affectedPieceEnum);
		Piece attackingPiece = engine.pieceOperator().getAllPieces().get(PieceType.ATTACKINGEAGLE);
		commandExecutor.executeCommand(new UseAbility(PieceAbility.CAPTURE, attackingPiece, affectedPiece, isMode));

	}

	@Override
	public void updateModelStateAggressiveSharkCapture(PieceType affectedPieceEnum) {
		Piece affectedPiece = engine.pieceOperator().getAllPieces().get(affectedPieceEnum);
		Piece aggressivePiece = engine.pieceOperator().getAllPieces().get(PieceType.AGGRESSIVESHARK);
		commandExecutor.executeCommand(new UseAbility(PieceAbility.CAPTURE, aggressivePiece, affectedPiece, false));

	}

	@Override
	@Requires({ "teamName!=null" })
	public void updateModelStateForNextTurn(TeamType teamName) {
		if (!engine.endGame()) {
			engine.gameTurn().cancelTimer();
			engine.gameTurn().setActivePlayer(teamName, true);
		}
	}

	@Override
	public void updateModelStateHealingSharkRevive(PieceType affectedPieceEnum) {
		Piece healingPiece = engine.pieceOperator().getAllPieces().get(PieceType.HEALINGSHARK);
		Piece affectedPiece = engine.pieceOperator().getAllPieces().get(affectedPieceEnum);

		commandExecutor.executeCommand(new UseAbility(PieceAbility.HEAL, healingPiece, affectedPiece, false));
		commandExecutor.executeCommand(new MovePiece(affectedPiece.getPosition().get("x"),
				affectedPiece.getPosition().get("y"), affectedPiece, false));
	}

	@Override
	public void updateModelStateProtectPiece(PieceType affectedPieceEnum, PieceType pieceProtect) {
		Piece affectedPiece = engine.pieceOperator().getAllPieces().get(affectedPieceEnum);
		Piece leadershipPiece = engine.pieceOperator().getAllPieces().get(pieceProtect);
		commandExecutor.executeCommand(new UseAbility(PieceAbility.PROTECT, leadershipPiece, affectedPiece, false));
	}

	@Override
	public void updateModelStateSwapPiece(PieceType affectedPieceEnum) {
		Piece affectedPiece = engine.pieceOperator().getAllPieces().get(affectedPieceEnum);
		Piece visionaryPiece = engine.pieceOperator().getAllPieces().get(PieceType.VISIONARYEAGLE);
		commandExecutor.executeCommand(new UseAbility(PieceAbility.SWAP, visionaryPiece, affectedPiece, false));

	}
}