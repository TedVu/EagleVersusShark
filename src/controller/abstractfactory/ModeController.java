package controller.abstractfactory;

import java.awt.event.ActionListener;

import viewcontroller.contract.ViewControllerInterface;

public interface ModeController extends ActionListener {
	public void setState(ViewControllerInterface viewControllerFacade);

	public void setUpView();

}
