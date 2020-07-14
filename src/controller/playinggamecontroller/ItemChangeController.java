package controller.playinggamecontroller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import viewcontroller.contract.ViewControllerInterface;

/**
 * @author ted & kevin
 * 
 *         When changing ability => will update board
 *
 */
public class ItemChangeController implements ItemListener {

	private ViewControllerInterface viewControllerFacade;

	public ItemChangeController(ViewControllerInterface viewControllerFacade) {
		this.viewControllerFacade = viewControllerFacade;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		viewControllerFacade.refreshBoard();
	}

}
