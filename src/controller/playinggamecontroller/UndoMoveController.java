package controller.playinggamecontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

import modelcontroller.contract.ControllerModelInterface;
import modelcontroller.facade.ControllerModelFacade;
import view.operationview.UndoMovePanel;
import viewcontroller.contract.ViewControllerInterface;

public class UndoMoveController implements ActionListener {

	private ViewControllerInterface viewControllerFacade;
	private ControllerModelInterface controllerModelFacade = new ControllerModelFacade();

	private AbstractButton resumeBtn;

	public UndoMoveController(ViewControllerInterface viewControllerFacade, AbstractButton resumeBtn) {
		this.viewControllerFacade = viewControllerFacade;
		this.resumeBtn = resumeBtn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (controllerModelFacade.getGameCurrentlyRunning()) {
			controllerModelFacade.cancelTimerPauseGame();
			viewControllerFacade.updateBoardPauseGame();
			AbstractButton undoBtn = (AbstractButton) e.getSource();

			new UndoMovePanel(viewControllerFacade, undoBtn);
			resumeBtn.setEnabled(true);
		} else {
			viewControllerFacade.updateBoardErrorAction("Game is not running or is paused");
		}

	}

}
