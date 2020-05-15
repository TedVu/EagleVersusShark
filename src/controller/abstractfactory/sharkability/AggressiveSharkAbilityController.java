package controller.abstractfactory.sharkability;

import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;

import controller.abstractfactory.AbstractAbilityController;
import model.enumtype.PieceType;
import model.enumtype.TeamType;

public class AggressiveSharkAbilityController extends AbstractAbilityController {

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean captureSuccess = true;
		AbstractButton btnClicked = (AbstractButton) e.getSource();
		PieceType affectedPieceEnum = PieceType.parsePieceType(btnClicked.getActionCommand());

		try {
			super.controllerModelFacade.updateModelStateAggressiveSharkCapture(affectedPieceEnum);
		} catch (RuntimeException ex) {
			captureSuccess = false;
			super.viewControllerFacade.updateBoardErrorAction("This piece has immunity. Cannot capture");
		}
		if (captureSuccess) {
			super.viewControllerFacade.updateBoardAfterCapture(btnClicked, PieceType.AGGRESSIVESHARK);
			super.controllerModelFacade.updateModelStateForNextTurn(TeamType.EAGLE);

		}
	}

	@Override
	public void setUpViewForAbility() {
		try {
			super.viewControllerFacade.updateBoardBeforeCommitAction(this, PieceType.AGGRESSIVESHARK);
		} catch (RuntimeException ex) {
			super.viewControllerFacade.updateBoardErrorAction(ex.getMessage());
		}
	}
}
