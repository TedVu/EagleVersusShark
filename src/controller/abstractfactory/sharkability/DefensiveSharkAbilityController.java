package controller.abstractfactory.sharkability;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;

import controller.abstractfactory.AbstractAbilityController;
import model.enumtype.PieceType;
import model.enumtype.TeamType;

public class DefensiveSharkAbilityController extends AbstractAbilityController {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		AbstractButton btnClicked = (AbstractButton) e.getSource();

		if (btnClicked.getActionCommand().equalsIgnoreCase("NormalButton")) {
			Map<String, Integer> newPos = new HashMap<String, Integer>();
			super.viewControllerFacade.updateBoardAfterMovingPiece(btnClicked, PieceType.DEFENSIVESHARK);
			super.viewControllerFacade.locateNewPos(btnClicked, newPos);
			controllerModelFacade.updateModelAfterMovingPiece(newPos, PieceType.DEFENSIVESHARK);
			super.controllerModelFacade.updateModelStateForNextTurn(TeamType.EAGLE);
		} else {
			PieceType affectedPieceEnum = PieceType.parsePieceType(btnClicked.getActionCommand());
			super.controllerModelFacade.updateModelStateProtectPiece(affectedPieceEnum, PieceType.DEFENSIVESHARK);
			super.controllerModelFacade.updateModelStateForNextTurn(TeamType.EAGLE);
			super.viewControllerFacade.updateBoardAfterProtect();
		}
	}

}
