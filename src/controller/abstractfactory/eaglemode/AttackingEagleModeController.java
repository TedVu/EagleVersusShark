package controller.abstractfactory.eaglemode;

import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;

import controller.abstractfactory.AbstractModeController;
import model.enumtype.PieceType;
import model.enumtype.TeamType;

/**
 * @author Ted & Kevin
 *
 *         A concrete mode controller for attacking eagle
 */
public class AttackingEagleModeController extends AbstractModeController {

	@Override
	public void actionPerformed(ActionEvent e) {
		AbstractButton btnClicked = (AbstractButton) e.getSource();
		PieceType affectedPieceEnum = PieceType.parsePieceType(btnClicked.getActionCommand());

		try {
			super.controllerModelFacade.updateModelAttackingEagleCapture(affectedPieceEnum, true);
			super.viewControllerFacade.updateBoardAfterCapture(btnClicked, PieceType.ATTACKINGEAGLE);
			super.controllerModelFacade.updateModelStateForNextTurn(TeamType.SHARK);
		} catch (RuntimeException ex) {
			super.viewControllerFacade.updateBoardNotiDialog(ex.getMessage());
		}
	}

}
