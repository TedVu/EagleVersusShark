package controller.abstractfactory;

import java.awt.event.ActionListener;

import controller.SelectPieceController;
import viewcontroller.contract.ViewControllerInterface;

public interface AbilityController extends ActionListener {
	public void setState(ViewControllerInterface viewControllerFacade);
	public void setUpView();
}
