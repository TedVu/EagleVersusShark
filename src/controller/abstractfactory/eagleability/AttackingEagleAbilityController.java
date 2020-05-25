package controller.abstractfactory.eagleability;

import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;

import controller.abstractfactory.AbstractAbilityController;
import model.enumtype.PieceType;
import model.enumtype.TeamType;

public class AttackingEagleAbilityController extends AbstractAbilityController {

	@Override
	public void actionPerformed(ActionEvent e) {
		AbstractButton btnClicked = (AbstractButton) e.getSource();
		PieceType affectedPieceEnum = PieceType.parsePieceType(btnClicked.getActionCommand());
		try {
			super.controllerModelFacade.updateModelAttackingEagleCapture(affectedPieceEnum, false);
			super.viewControllerFacade.updateBoardAfterCapture(btnClicked, PieceType.ATTACKINGEAGLE);
			super.controllerModelFacade.updateModelStateForNextTurn(TeamType.SHARK);
		} catch (RuntimeException ex) {
			super.viewControllerFacade.updateBoardNotiDialog("This piece has immunity. Cannot capture");
		}
	}

}
