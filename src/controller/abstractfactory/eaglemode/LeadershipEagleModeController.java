package controller.abstractfactory.eaglemode;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;

import controller.abstractfactory.AbstractModeController;
import model.enumtype.PieceType;
import model.enumtype.TeamType;

public class LeadershipEagleModeController extends AbstractModeController {

	@Override
	public void actionPerformed(ActionEvent e) {
		AbstractButton btnClicked = (AbstractButton) e.getSource();
		Map<String, Integer> newPos = new HashMap<String, Integer>();

		super.viewControllerFacade.updateBoardAfterMovingPiece(btnClicked, PieceType.LEADERSHIPEAGLE);
		super.viewControllerFacade.locateNewPos(btnClicked, newPos);
		controllerModelFacade.updateModelAfterMovingPiece(newPos, PieceType.LEADERSHIPEAGLE);
		super.controllerModelFacade.updateModelStateForNextTurn(TeamType.SHARK);
	}

}
