package controller.abstractfactory;

import java.awt.event.ActionEvent;
import java.util.Set;

import model.board.Cell;
import model.enumtype.TeamType;

public class LeadershipEagleModeController extends AbstractModeController {

	private Set<Cell> posLeap;

	@Override
	public void setUpView() {
		try {
			posLeap = super.controllerModelFacade.updateBoardBeforeLeadershipUseMode();
			super.viewControllerFacade.updateBoardBeforeLeadershipUseMode(posLeap, this);
		} catch (RuntimeException e) {
			super.viewControllerFacade.updateBoardFailToUseLeadershipMode(e.getMessage());
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.viewControllerFacade.updateBoardAfterLeadershipUseMode(posLeap);
		for (Cell cell : posLeap) {
			super.controllerModelFacade.updateBoardAfterLeadershipUseMode(cell);
		}
		super.controllerModelFacade.updateModelStateForNextTurn(TeamType.SHARK);

	}

}
