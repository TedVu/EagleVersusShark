package controller.abstractfactory;

import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;

import model.enumtype.PieceType;

public class DefensiveSharkAbilityController extends AbstractAbilityController {
	@Override
	public void actionPerformed(ActionEvent e) {
		AbstractButton btnClicked = (AbstractButton) e.getSource();
		if (btnClicked.getActionCommand().equalsIgnoreCase("NormalButton")) {
			super.viewControllerFacade.updateBoardAfterDefensiveSharkMoveAbility(btnClicked);
			super.controllerModelFacade.updateModelStateDefensiveSharkMove();
		} else {

			PieceType affectedPieceEnum = PieceType.parsePieceType(btnClicked.getActionCommand());
			super.controllerModelFacade.updateModelStateDefensiveSharkProtect(affectedPieceEnum);
			super.viewControllerFacade.updateBoardAfterDefensiveSharkProtectAbility();

		}
	}

	@Override
	public void setUpView() {
		super.viewControllerFacade.updateBoardBeforeDefensiveSharkAbility(this);
	}
}
