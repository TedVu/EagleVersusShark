package controller.abstractfactory;

import java.awt.event.ActionEvent;

import model.enumtype.TeamType;

public class LeadershipEagleModeController extends AbstractModeController {

	@Override
	public void setUpViewForMode() {
		try {
			super.viewControllerFacade.updateBoardBeforeLeadershipUseMode(this);
		} catch (RuntimeException e) {
			super.viewControllerFacade.updateBoardFailToUseLeadershipMode(e.getMessage());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.viewControllerFacade.updateBoardAfterLeadershipUseMode();
		super.controllerModelFacade.updateModelAfterLeadershipUseMode();

		super.controllerModelFacade.updateModelStateForNextTurn(TeamType.SHARK);

	}

}
