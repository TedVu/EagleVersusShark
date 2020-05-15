package controller.abstractfactory.sharkmode;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;

import controller.abstractfactory.AbstractModeController;
import model.enumtype.PieceType;
import model.enumtype.TeamType;
 
public class DefensiveSharkModeController extends AbstractModeController {

	@Override
	public void setUpViewForMode() {
		super.viewControllerFacade.updateBoardBeforeCommitAction(this, PieceType.DEFENSIVESHARK);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AbstractButton btnClicked = (AbstractButton) e.getSource();
		Map<String, Integer> newPos = new HashMap<String, Integer>();

		super.viewControllerFacade.updateBoardAfterMovingPiece(btnClicked, PieceType.DEFENSIVESHARK);
		super.viewControllerFacade.locateNewPos(btnClicked, newPos);
		controllerModelFacade.updateModelAfterMovingPiece(newPos, PieceType.DEFENSIVESHARK);
		super.controllerModelFacade.updateModelStateForNextTurn(TeamType.EAGLE);
	}

}
