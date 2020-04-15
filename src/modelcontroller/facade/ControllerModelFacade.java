package modelcontroller.facade;

import java.util.Map;

import model.engine.EngineImpl;
import model.enumtype.PieceType;
import model.enumtype.TeamType;
import modelcontroller.contract.ControllerModelInterface;

/**
 * @author ted &#38; kevin
 *
 */
public class ControllerModelFacade implements ControllerModelInterface {

	@Override
	public void updateModelAfterMovingPiece(Map<String, Integer> newPos,
			PieceType pieceType) {
		EngineImpl.getSingletonInstance().getAllPieces().get(pieceType)
				.movePiece(newPos.get("x"), newPos.get("y"));
	}

	@Override
	public void updateModelStateForNextTurn(TeamType teamName) {
		EngineImpl.getSingletonInstance().cancelTimer();
		EngineImpl.getSingletonInstance().setActivePlayer(teamName, true);
	}
}
