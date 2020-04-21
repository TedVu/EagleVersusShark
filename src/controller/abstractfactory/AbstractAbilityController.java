package controller.abstractfactory;

import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;

import modelcontroller.contract.ControllerModelInterface;
import modelcontroller.facade.ControllerModelFacade;
import viewcontroller.contract.ViewControllerInterface;

public class AbstractAbilityController implements AbilityController {

	protected AbstractButton buttonClicked;
	protected ViewControllerInterface viewControllerFacade;
	private ControllerModelInterface controllerModelFacade = new ControllerModelFacade();

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setState(ViewControllerInterface viewControllerFacade) {
		// TODO Auto-generated method stub
		this.viewControllerFacade = viewControllerFacade;

	}

	@Override
	public void setUpView() {
		// TODO Auto-generated method stub
		viewControllerFacade.updateBoardBeforeSwap(this);

	}

}
