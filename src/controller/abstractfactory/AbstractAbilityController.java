package controller.abstractfactory;

import javax.swing.AbstractButton;

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

}
