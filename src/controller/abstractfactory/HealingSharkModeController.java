package controller.abstractfactory;

import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;

import model.enumtype.PieceType;
import model.enumtype.TeamType;

public class HealingSharkModeController extends AbstractModeController {

	@Override
	public void setUpViewForMode() {
		super.viewControllerFacade.updateBoardBeforeHealingSharkUseMode(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AbstractButton affectedBtn = (AbstractButton) e.getSource();
		PieceType pieceTypeEnum = PieceType.parsePieceType(affectedBtn.getActionCommand());
		super.controllerModelFacade.updateModelAfterHealingSharkUseMode(pieceTypeEnum);
		super.viewControllerFacade.updateBoardAfterHealingSharkUseMode();
		super.controllerModelFacade.updateModelStateForNextTurn(TeamType.EAGLE);
	}

}
