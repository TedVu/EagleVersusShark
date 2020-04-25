package controller.abstractfactory;

import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;

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
			super.viewControllerFacade.updateBoardFailToCaptureImmunity();
		}
		if (captureSuccess) {
			super.viewControllerFacade.updateBoardAfterAggressiveSharkCapture(btnClicked);
			super.controllerModelFacade.updateModelStateForNextTurn(TeamType.EAGLE);

		}
	}

	@Override
	public void setUpView() {
		super.viewControllerFacade.updateBoardBeforeAggressiveSharkCapture(this);
	}
}
