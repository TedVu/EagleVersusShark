package controller.abstractfactory;

import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;

import model.enumtype.PieceType;
import model.enumtype.TeamType;

public class AttackingEagleModeController extends AbstractModeController {

	@Override
	public void setUpView() {
		try {
			super.viewControllerFacade.updateBoardBeforeAttackingEagleUseMode(this);

		} catch (RuntimeException e) {
			super.viewControllerFacade.updateBoardFailAttackingEagleUseMode(e.getMessage());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean captureSuccess = true;
		AbstractButton btnClicked = (AbstractButton) e.getSource();
		PieceType affectedPieceEnum = PieceType.parsePieceType(btnClicked.getActionCommand());

		try {
			// will throw exception if capture immunity piece
			super.controllerModelFacade.updateModelStateAttackingEagle(affectedPieceEnum);
		} catch (RuntimeException ex) {
			captureSuccess = false;
			super.viewControllerFacade.updateBoardFailToCaptureImmunity();
		}
		if (captureSuccess) {
			super.viewControllerFacade.updateBoardAfterAttackingEagleCapture(btnClicked);
			super.controllerModelFacade.updateModelStateForNextTurn(TeamType.SHARK);

		}
	}

}
