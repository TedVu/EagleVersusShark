package controller.abstractfactory.contract;

import java.awt.event.ActionListener;

import model.enumtype.PieceType;
import viewcontroller.contract.ViewControllerInterface;

/**
 * 
 * A contract defining what a mode controller should do
 * 
 * @author Ted & Kevin
 *
 */
public interface ModeController extends ActionListener {

	/**
	 * @param viewControllerFacade
	 */
	public void setModeState(ViewControllerInterface viewControllerFacade);

	/**
	 * @param pieceType
	 */
	public void setUpViewForMode(PieceType pieceType);

}
