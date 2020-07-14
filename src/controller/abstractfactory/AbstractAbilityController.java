package controller.abstractfactory;

import javax.swing.AbstractButton;

import controller.abstractfactory.contract.AbilityController;
import model.enumtype.PieceType;
import modelcontroller.contract.ControllerModelInterface;
import modelcontroller.facade.ControllerModelFacadeImpl;
import viewcontroller.contract.ViewControllerInterface;

/**
 * @author Ted & Kevin
 *
 *         An abstract ability controller to provide some default code
 */
public abstract class AbstractAbilityController implements AbilityController {

	protected AbstractButton buttonClicked;
	protected ViewControllerInterface viewControllerFacade;
	protected ControllerModelInterface controllerModelFacade = new ControllerModelFacadeImpl();

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
