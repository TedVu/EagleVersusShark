package controller.abstractfactory;

import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;

import model.board.Cell;
import model.enumtype.PieceType;
import model.enumtype.TeamType;

public class DefensiveSharkAbilityController extends AbstractAbilityController {
	@Override
	public void actionPerformed(ActionEvent e) { 
		AbstractButton btnClicked = (AbstractButton) e.getSource();

		if (btnClicked.getActionCommand().equalsIgnoreCase("NormalButton")) {
			Cell newPos = new Cell(0, 0);

			// manipulate cell
			super.viewControllerFacade.updateBoardAfterDefensiveSharkMoveAbility(btnClicked, newPos);
			super.controllerModelFacade.updateModelStateDefensiveSharkMove(newPos);
			super.controllerModelFacade.updateModelStateForNextTurn(TeamType.EAGLE);
		} else {

			PieceType affectedPieceEnum = PieceType.parsePieceType(btnClicked.getActionCommand());
			super.controllerModelFacade.updateModelStateDefensiveSharkProtect(affectedPieceEnum);
			super.controllerModelFacade.updateModelStateForNextTurn(TeamType.EAGLE);

			super.viewControllerFacade.updateBoardAfterDefensiveSharkProtectAbility();

		}
	}

	@Override
	public void setUpView() {
		super.viewControllerFacade.updateBoardBeforeDefensiveSharkAbility(this);
	}
}
