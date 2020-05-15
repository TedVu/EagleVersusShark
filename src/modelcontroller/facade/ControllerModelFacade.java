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
		EngineImpl.getSingletonInstance().gameTurn().cancelTimer();
		EngineImpl.getSingletonInstance().gameTurn().setActivePlayer(teamName, true);
	}

	@Override
	public void updateModelStateSwapPiece(PieceType affectedPieceEnum) {
		Piece affectedPiece = EngineImpl.getSingletonInstance().pieceOperator().getAllPieces()
				.get(affectedPieceEnum);
		Piece visionaryPiece = EngineImpl.getSingletonInstance().pieceOperator().getAllPieces()
				.get(PieceType.VISIONARYEAGLE);
		commandExecutor.executeCommand(new UseAbility(PieceAbility.SWAP, visionaryPiece, affectedPiece, false));

	}

	@Override
	public void updateModelStateProtectPiece(PieceType affectedPieceEnum, PieceType pieceProtect) {
		Piece affectedPiece = EngineImpl.getSingletonInstance().pieceOperator().getAllPieces()
				.get(affectedPieceEnum);
		Piece leadershipPiece = EngineImpl.getSingletonInstance().pieceOperator().getAllPieces()
				.get(pieceProtect);
		commandExecutor.executeCommand(new UseAbility(PieceAbility.PROTECT, leadershipPiece, affectedPiece, false));
	}

	@Override
	public void updateModelAttackingEagleCapture(PieceType affectedPieceEnum, boolean isMode) {
		Piece affectedPiece = EngineImpl.getSingletonInstance().pieceOperator().getAllPieces()
				.get(affectedPieceEnum);
		Piece attackingPiece = EngineImpl.getSingletonInstance().pieceOperator().getAllPieces()
				.get(PieceType.ATTACKINGEAGLE);
		commandExecutor.executeCommand(new UseAbility(PieceAbility.CAPTURE, attackingPiece, affectedPiece, isMode));

	}

	@Override
	public void updateModelStateAggressiveSharkCapture(PieceType affectedPieceEnum) {
		Piece affectedPiece = EngineImpl.getSingletonInstance().pieceOperator().getAllPieces()
				.get(affectedPieceEnum);
		Piece aggressivePiece = EngineImpl.getSingletonInstance().pieceOperator().getAllPieces()
				.get(PieceType.AGGRESSIVESHARK);
		commandExecutor.executeCommand(new UseAbility(PieceAbility.CAPTURE, aggressivePiece, affectedPiece, false));

	}

	@Override
	public void updateModelStateHealingSharkRevive(PieceType affectedPieceEnum) {
		Piece healingPiece = EngineImpl.getSingletonInstance().pieceOperator().getAllPieces()
				.get(PieceType.HEALINGSHARK);
		Piece affectedPiece = EngineImpl.getSingletonInstance().pieceOperator().getAllPieces()
				.get(affectedPieceEnum);

		commandExecutor.executeCommand(new UseAbility(PieceAbility.HEAL, healingPiece, affectedPiece, false));
		commandExecutor.executeCommand(new MovePiece(affectedPiece.getPosition().get("x"),
				affectedPiece.getPosition().get("y"), affectedPiece, false));
	}

	@Override
	public void updateModelAfterHealingSharkUseMode(PieceType affectedPieceEnum) {
		Piece eagle = EngineImpl.getSingletonInstance().pieceOperator().getAllPieces().get(affectedPieceEnum);
		Cell eagleMasterCell = EngineImpl.getSingletonInstance().gameBoard().getCell(4, 0);
		commandExecutor.executeCommand(new MovePiece(eagleMasterCell.getX(), eagleMasterCell.getY(), eagle, true));

	}
}