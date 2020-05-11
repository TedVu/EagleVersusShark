package controller.abstractfactory;

import java.awt.event.ActionListener;

import viewcontroller.contract.ViewControllerInterface;

public interface ModeController extends ActionListener {
	public void setModeState(ViewControllerInterface viewControllerFacade);

	public void setUpViewForMode();

}
