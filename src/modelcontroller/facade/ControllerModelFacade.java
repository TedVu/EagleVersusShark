package modelcontroller.facade;

import java.util.Map;

import com.google.java.contract.Requires;

import model.board.Cell;
import model.contract.Engine;
import model.contract.Piece;
import model.engine.EngineImpl;
import model.enumtype.PieceAbility;
import model.enumtype.PieceType;
import model.enumtype.TeamType;
import model.piece.commands.CommandExecutor;
import model.piece.commands.MovePiece;
import model.piece.commands.UseAbility;
import model.player.Player;
import modelcontroller.contract.ControllerModelInterface;

/**
 * @author ted &#38; kevin
 *
 */
public class ControllerModelFacade implements ControllerModelInterface {

	private Engine engine = EngineImpl.getSingletonInstance();
	private CommandExecutor commandExecutor = new CommandExecutor();

	@Override
	@Requires({ "pieceType!=null", "newPos!=null", "newPos.size()>0" })
	public void updateModelAfterMovingPiece(Map<String, Integer> newPos, PieceType pieceType) {
		Piece pieceMoved = engine.pieceOperator().getAllPieces().get(pieceType);
		commandExecutor.executeCommand(new MovePiece(newPos.get("x"), newPos.get("y"), pieceMoved, false));
	}

	@Override
	@Requires({ "teamName!=null" })
	public void updateModelStateForNextTurn(TeamType teamName) {
		engine.gameTurn().cancelTimer();
		engine.gameTurn().setActivePlayer(teamName, true);
	}

	@Override
	public void updateModelStateSwapPiece(PieceType affectedPieceEnum) {
		Piece affectedPiece = engine.pieceOperator().getAllPieces().get(affectedPieceEnum);
		Piece visionaryPiece = engine.pieceOperator().getAllPieces().get(PieceType.VISIONARYEAGLE);
		commandExecutor.executeCommand(new UseAbility(PieceAbility.SWAP, visionaryPiece, affectedPiece, false));

	}

	@Override
	public void updateModelStateProtectPiece(PieceType affectedPieceEnum, PieceType pieceProtect) {
		Piece affectedPiece = engine.pieceOperator().getAllPieces().get(affectedPieceEnum);
		Piece leadershipPiece = engine.pieceOperator().getAllPieces().get(pieceProtect);
		commandExecutor.executeCommand(new UseAbility(PieceAbility.PROTECT, leadershipPiece, affectedPiece, false));
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
	public void updateModelStateHealingSharkRevive(PieceType affectedPieceEnum) {
		Piece healingPiece = engine.pieceOperator().getAllPieces().get(PieceType.HEALINGSHARK);
		Piece affectedPiece = engine.pieceOperator().getAllPieces().get(affectedPieceEnum);

		commandExecutor.executeCommand(new UseAbility(PieceAbility.HEAL, healingPiece, affectedPiece, false));
		commandExecutor.executeCommand(new MovePiece(affectedPiece.getPosition().get("x"),
				affectedPiece.getPosition().get("y"), affectedPiece, false));
	}

	@Override
	public void updateModelAfterHealingSharkUseMode(PieceType affectedPieceEnum) {
		Piece eagle = engine.pieceOperator().getAllPieces().get(affectedPieceEnum);
		Cell eagleMasterCell = engine.gameBoard().getCell(4, 0);
		commandExecutor.executeCommand(new MovePiece(eagleMasterCell.getX(), eagleMasterCell.getY(), eagle, true));

	}

	@Override
	public boolean checkCorrectTurnOfSelectedPiece(PieceType pieceType) {
		return engine.pieceOperator().checkSelectPiece(pieceType);
	}

	@Override
	public void setTurnStartingGame(TeamType teamType) {
		engine.gameTurn().setActivePlayerTimer(teamType);
	}

	@Override
	public Player getInitialActivePlayer() {
		return engine.gameTurn().getInitialPlayerActivePlayer();
	}

	@Override
	public Player getCurrentActivePlayer() {
		return engine.gameTurn().getCurrentActivePlayer();
	}

	@Override
	public void setAlreadyUseUndo() {
		engine.gameTurn().getCurrentActivePlayer().setAlreadyUndo();
	}

	@Override
	public boolean getGameCurrentlyRunning() {
		return engine.gameTurn().getGameCurrentlyRunning();
	}

	@Override
	public void cancelTimerPauseGame() {
		engine.gameTurn().cancelTimerPauseGame();

	}

	@Override
	public void setResumeGame() {
		engine.gameTurn().setResumeGame();
	}
}