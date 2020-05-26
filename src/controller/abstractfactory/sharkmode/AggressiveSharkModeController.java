package controller.abstractfactory.sharkmode;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;

import controller.abstractfactory.AbstractModeController;
import model.enumtype.PieceType;
import model.enumtype.TeamType;

/**
 * @author Ted & Kevin
 *
 *         A concrete controller for aggressive shark mode
 */
public class AggressiveSharkModeController extends AbstractModeController {

	@Override
	public void actionPerformed(ActionEvent e) {
		AbstractButton btnClicked = (AbstractButton) e.getSource();
		Map<String, Integer> newPos = new HashMap<String, Integer>();

		super.viewControllerFacade.updateBoardAfterMovingPiece(btnClicked, PieceType.AGGRESSIVESHARK);
		super.viewControllerFacade.locateNewPos(btnClicked, newPos);
		controllerModelFacade.updateModelAfterMovingPiece(newPos, PieceType.AGGRESSIVESHARK);
		super.controllerModelFacade.updateModelStateForNextTurn(TeamType.EAGLE);
	}

}
