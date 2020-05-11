package controller.abstractfactory;

import java.awt.event.ActionListener;

import viewcontroller.contract.ViewControllerInterface;

public interface AbilityController extends ActionListener {
	public void setAbilityState(ViewControllerInterface viewControllerFacade);

	public void setUpViewForAbility();
}
