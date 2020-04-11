package controllermodel.facade;

import java.util.Map;

import controllermodel.interfaces.ControllerModelInterfaces;
import models.engine.EngineImpl;

public class ControllerModelFacade implements ControllerModelInterfaces {

	@Override
	public void updateModelAfterMovingPiece(Map<String, Integer> newPos, String pieceType) {
		EngineImpl.getSingletonInstance().getAllPieces().get(pieceType.toString()).movePiece(newPos.get("x"),
				newPos.get("y"));
	}

	@Override
	public void updateModelStateForNextTurn(String teamName) {
		EngineImpl.getSingletonInstance().cancelTimer();
		EngineImpl.getSingletonInstance().setActivePlayer(teamName, true);
	}
}
