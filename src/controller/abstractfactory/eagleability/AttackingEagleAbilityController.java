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
			// will throw exception if capture immunity piece
			super.controllerModelFacade.updateModelAttackingEagleCapture(affectedPieceEnum, false);
			super.viewControllerFacade.updateBoardAfterCapture(btnClicked, PieceType.ATTACKINGEAGLE);
			super.controllerModelFacade.updateModelStateForNextTurn(TeamType.SHARK);
		} catch (RuntimeException ex) {
			super.viewControllerFacade.updateBoardErrorAction("This piece has immunity. Cannot capture");
		}

	}

	@Override
	public void setUpViewForAbility() {
		try {
			super.viewControllerFacade.updateBoardBeforeUseSpecialBehaviour(this, PieceType.ATTACKINGEAGLE);
		} catch (RuntimeException ex) {
			super.viewControllerFacade.updateBoardErrorAction(ex.getMessage());
		}
	}
}
