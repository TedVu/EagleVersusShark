package controller.abstractfactory;

import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;

import model.enumtype.PieceType;
import model.enumtype.TeamType;

public class VisionaryEagleAbilityController extends AbstractAbilityController {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		buttonClicked = (AbstractButton) e.getSource();
		String buttonClickedStr = buttonClicked.getActionCommand();
		if (buttonClickedStr.equalsIgnoreCase(PieceType.ATTACKINGEAGLE.toString())
				|| buttonClickedStr.equalsIgnoreCase(PieceType.LEADERSHIPEAGLE.toString())) {
			viewControllerFacade.updateBoardAfterSwap(buttonClicked);
		}
		super.controllerModelFacade.updateModelStateSwapPiece(PieceType.parsePieceType(buttonClickedStr));
		super.controllerModelFacade.updateModelStateForNextTurn(TeamType.SHARK);
	}

}
