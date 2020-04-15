package modelcontroller.facade;

import java.util.Map;

import asset.PieceType;
import asset.TeamType;
import model.engine.EngineImpl;
import modelcontroller.interfaces.ControllerModelInterfaces;

/**
 * @author ted &#38; kevin
 *
 */
public class ControllerModelFacade implements ControllerModelInterfaces {

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
