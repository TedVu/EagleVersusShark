package controller.abstractfactory;

import java.awt.event.ActionEvent;

import controller.SelectPieceController;
import modelcontroller.contract.ControllerModelInterface;
import modelcontroller.facade.ControllerModelFacade;
import viewcontroller.contract.ViewControllerInterface;

public class VisionaryEagleAbilityController implements AbilityController {

	private ViewControllerInterface facade;
	private SelectPieceController selectController;
	private ControllerModelInterface controllerModelFacade = new ControllerModelFacade();


	@Override
	public void setState(ViewControllerInterface viewControllerFacade, SelectPieceController selectController) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUpView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
