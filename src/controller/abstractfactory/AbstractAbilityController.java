package controller.abstractfactory;

import javax.swing.AbstractButton;

import model.enumtype.PieceType;
import modelcontroller.contract.ControllerModelInterface;
import modelcontroller.facade.ControllerModelFacade;
import viewcontroller.contract.ViewControllerInterface;

public abstract class AbstractAbilityController implements AbilityController {

	protected AbstractButton buttonClicked;
	protected ViewControllerInterface viewControllerFacade;
	protected ControllerModelInterface controllerModelFacade = new ControllerModelFacade();

	@Override
	public void setAbilityState(ViewControllerInterface viewControllerFacade) {
		this.viewControllerFacade = viewControllerFacade;
	}

	@Override
	public void setUpViewForAbility(PieceType pieceType) {
		try {
			this.viewControllerFacade.updateBoardBeforeCommitAction(this, pieceType);
		} catch (RuntimeException ex) {
			this.viewControllerFacade.updateBoardNotiDialog(ex.getMessage());
		}
	}

}
