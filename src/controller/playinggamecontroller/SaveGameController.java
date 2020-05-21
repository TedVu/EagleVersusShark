package controller.playinggamecontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

import modelcontroller.contract.ControllerModelInterface;
import modelcontroller.facade.ControllerModelFacade;
import view.configuration.SaveGameDialog;
import viewcontroller.contract.ViewControllerInterface;

public class SaveGameController implements ActionListener {
	private ViewControllerInterface viewControllerFacade;
	private ControllerModelInterface controllerModelFacade = new ControllerModelFacade();
	private AbstractButton resumeBtn;

	public SaveGameController(ViewControllerInterface viewControllerFacade, AbstractButton resumeBtn) {
		this.viewControllerFacade = viewControllerFacade;
		this.resumeBtn = resumeBtn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (controllerModelFacade.getGameCurrentlyRunning()) {
			viewControllerFacade.updateBoardPauseGame();
			controllerModelFacade.cancelTimerPauseGame();
			resumeBtn.setEnabled(true);
		}
		new SaveGameDialog(viewControllerFacade);

	}

}
