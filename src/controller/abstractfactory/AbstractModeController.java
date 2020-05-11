package controller.abstractfactory;

import javax.swing.AbstractButton;

import modelcontroller.contract.ControllerModelInterface;
import modelcontroller.facade.ControllerModelFacade;
import viewcontroller.contract.ViewControllerInterface;

public abstract class AbstractModeController implements ModeController {

	protected AbstractButton buttonClicked;
	protected ViewControllerInterface viewControllerFacade;
	protected ControllerModelInterface controllerModelFacade = new ControllerModelFacade();

	@Override
	public void setModeState(ViewControllerInterface viewControllerFacade) {
		this.viewControllerFacade = viewControllerFacade;
	}

}
