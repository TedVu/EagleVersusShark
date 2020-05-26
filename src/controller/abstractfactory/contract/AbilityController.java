package controller.abstractfactory.contract;

import java.awt.event.ActionListener;

import model.enumtype.PieceType;
import viewcontroller.contract.ViewControllerInterface;

/**
 * A contract defining what an ability controller should do
 * 
 * @author Ted & Kevin
 *
 */
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
