package controller.abstractfactory;

import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;

import model.enumtype.PieceType;
import model.enumtype.TeamType;

public class LeadershipEagleAbilityController extends AbstractAbilityController {

	@Override
	public void actionPerformed(ActionEvent e) {
		AbstractButton btnClicked = (AbstractButton) e.getSource();
		PieceType affectedPieceEnum = PieceType.parsePieceType(btnClicked.getActionCommand());
		super.controllerModelFacade.updateModelStateProtectLeadership(affectedPieceEnum);

		super.controllerModelFacade.updateModelStateForNextTurn(TeamType.SHARK);
		viewControllerFacade.updateBoardAfterLeadershipProtect();

	}

	@Override
	public void setUpView() {
		super.viewControllerFacade.updateBoardBeforeLeadershipProtect(this);
	}
}
