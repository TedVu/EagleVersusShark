package controller.abstractfactory;

import java.awt.event.ActionListener;

import model.enumtype.PieceType;
import viewcontroller.contract.ViewControllerInterface;

public interface AbilityController extends ActionListener {
	public void setAbilityState(ViewControllerInterface viewControllerFacade);

	public void setUpViewForAbility(PieceType pieceType);
}
