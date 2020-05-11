package controller.abstractfactory;

import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;

import model.enumtype.PieceType;
import model.enumtype.TeamType;

public class VisionaryEagleModeController extends AbstractModeController {

	@Override
	public void actionPerformed(ActionEvent e) {
		buttonClicked = (AbstractButton) e.getSource();
		String buttonClickedStr = buttonClicked.getActionCommand();

		viewControllerFacade.updateBoardAfterSwap(buttonClicked);

		super.controllerModelFacade.updateModelStateSwapPiece(PieceType.parsePieceType(buttonClickedStr));
		super.controllerModelFacade.updateModelStateForNextTurn(TeamType.SHARK);
	}

	@Override
	public void setUpViewForMode() {
		super.viewControllerFacade.updateBoardBeforeVisionaryUseMode(this);
	}

}
