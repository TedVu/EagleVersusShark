package controller.abstractfactory;

import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;

import model.enumtype.PieceType;
import model.enumtype.TeamType;

public class AttackingEagleAbilityController extends AbstractAbilityController {

	@Override
	public void actionPerformed(ActionEvent e) {
		AbstractButton btnClicked = (AbstractButton) e.getSource();
		PieceType affectedPieceEnum = PieceType.parsePieceType(btnClicked.getActionCommand());

		try {
			// will throw exception if capture immunity piece
			super.controllerModelFacade.updateModelAttackingEagleCapture(affectedPieceEnum, false);
			super.viewControllerFacade.updateBoardAfterAttackingEagleCapture(btnClicked);
			super.controllerModelFacade.updateModelStateForNextTurn(TeamType.SHARK);
		} catch (RuntimeException ex) {
			super.viewControllerFacade.updateBoardFailToCaptureImmunity();
		}

	}

	@Override
	public void setUpViewForAbility() {
		super.viewControllerFacade.updateBoardBeforeAttackingEagleCapture(this);
	}
}
