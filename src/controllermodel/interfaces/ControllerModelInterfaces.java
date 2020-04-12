package controllermodel.interfaces;

import java.util.Map;

public interface ControllerModelInterfaces {

	void updateModelAfterMovingPiece(Map<String, Integer> newPos, String pieceType);

	void updateModelStateForNextTurn(String teamName);

}