package controller.abstractfactory;

import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;

import model.enumtype.PieceType;
import model.enumtype.TeamType;

public class AttackingEagleModeController extends AbstractModeController {

	@Override
	public void setUpViewForMode() {
		try {
			super.viewControllerFacade.updateBoardBeforeAttackingEagleUseMode(this);

		} catch (RuntimeException e) {
			super.viewControllerFacade.updateBoardFailAttackingEagleUseMode(e.getMessage());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AbstractButton btnClicked = (AbstractButton) e.getSource();
		PieceType affectedPieceEnum = PieceType.parsePieceType(btnClicked.getActionCommand());

		try {
			// will throw exception if capture immunity piece
			super.controllerModelFacade.updateModelAttackingEagleCapture(affectedPieceEnum, true);
			super.viewControllerFacade.updateBoardAfterAttackingEagleCapture(btnClicked);
			super.controllerModelFacade.updateModelStateForNextTurn(TeamType.SHARK);
		} catch (RuntimeException ex) {
			super.viewControllerFacade.updateBoardFailToCaptureImmunity();
		}

	}

}
