package controller.abstractfactory.eaglemode;

import java.awt.event.ActionEvent;

import controller.abstractfactory.AbstractModeController;
import model.enumtype.PieceType;
import model.enumtype.TeamType;

public class LeadershipEagleModeController extends AbstractModeController {

	@Override
	public void setUpViewForMode() {
		try {
			super.viewControllerFacade.updateBoardBeforeUseSpecialBehaviour(this, PieceType.LEADERSHIPEAGLE);
		} catch (RuntimeException e) {
			super.viewControllerFacade.updateBoardErrorAction(e.getMessage());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.viewControllerFacade.updateBoardAfterLeadershipUseMode();
		super.controllerModelFacade.updateModelAfterLeadershipUseMode();

		super.controllerModelFacade.updateModelStateForNextTurn(TeamType.SHARK);

	}

}
