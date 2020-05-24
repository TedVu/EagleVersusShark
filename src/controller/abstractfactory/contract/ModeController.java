package controller.abstractfactory.contract;

import java.awt.event.ActionListener;

import model.enumtype.PieceType;
import viewcontroller.contract.ViewControllerInterface;

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
