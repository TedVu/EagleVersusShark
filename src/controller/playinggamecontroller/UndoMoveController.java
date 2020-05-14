package controller.playinggamecontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

import model.engine.EngineImpl;
import view.operationview.UndoMovePanel;
import viewcontroller.contract.ViewControllerInterface;

public class UndoMoveController implements ActionListener {

	private ViewControllerInterface viewControllerFacade;
	private AbstractButton resumeBtn;

	public UndoMoveController(ViewControllerInterface viewControllerFacade, AbstractButton resumeBtn) {
		this.viewControllerFacade = viewControllerFacade;
		this.resumeBtn = resumeBtn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (EngineImpl.getSingletonInstance().getGameCurrentlyRunning()) {
			EngineImpl.getSingletonInstance().cancelTimerPauseGame();
			viewControllerFacade.undoMoveCancelTimer();
			AbstractButton undoBtn = (AbstractButton) e.getSource();

			new UndoMovePanel(viewControllerFacade, undoBtn);
			resumeBtn.setEnabled(true);
		} else {
			viewControllerFacade.notifyGameNotRunning();
		}

	}

}
