package controller.abstractfactory;

import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;

import model.board.Cell;
import model.enumtype.TeamType;

public class AggressiveSharkModeController extends AbstractModeController {

	@Override
	public void setUpViewForMode() {
		super.viewControllerFacade.updateBoardBeforeAggressiveSharkUseMode(this);

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
