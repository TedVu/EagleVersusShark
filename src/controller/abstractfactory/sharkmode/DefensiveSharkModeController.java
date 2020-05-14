package controller.abstractfactory.sharkmode;

import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;

import controller.abstractfactory.AbstractModeController;
import model.board.Cell;
import model.enumtype.PieceType;
import model.enumtype.TeamType;

public class DefensiveSharkModeController extends AbstractModeController {

	@Override
	public void setUpViewForMode() {
		super.viewControllerFacade.updateBoardBeforeUseSpecialBehaviour(this, PieceType.DEFENSIVESHARK);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AbstractButton movedBtn = (AbstractButton) e.getSource();
		Cell newPos = new Cell(0, 0);
		super.viewControllerFacade.updateBoardAfterAggressiveSharkUseMode(movedBtn, newPos);
		super.controllerModelFacade.updateModelAfterAggressiveSharkUseMode(newPos);
		super.controllerModelFacade.updateModelStateForNextTurn(TeamType.EAGLE);
	}

}
