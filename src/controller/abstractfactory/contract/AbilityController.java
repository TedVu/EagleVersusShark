package controller.abstractfactory.contract;

import java.awt.event.ActionListener;

import model.enumtype.PieceType;
import viewcontroller.contract.ViewControllerInterface;

public interface AbilityController extends ActionListener {
	
	/**
	 * @param viewControllerFacade
	 */
	public void setAbilityState(ViewControllerInterface viewControllerFacade);

	/**
	 * @param pieceType
	 */
	public void setUpViewForAbility(PieceType pieceType);

}
