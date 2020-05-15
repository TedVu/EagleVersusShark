package controller.playinggamecontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

import model.engine.EngineImpl;
import view.configuration.SaveGameDialog;
import viewcontroller.contract.ViewControllerInterface;

public class SaveGameController implements ActionListener {
	private ViewControllerInterface viewControllerFacade;
	private AbstractButton resumeBtn;

	public SaveGameController(ViewControllerInterface viewControllerFacade, AbstractButton resumeBtn) {
		this.viewControllerFacade = viewControllerFacade;
		this.resumeBtn = resumeBtn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (EngineImpl.getSingletonInstance().gameTurn().getGameCurrentlyRunning()) {
			viewControllerFacade.undoMoveCancelTimer();
			EngineImpl.getSingletonInstance().gameTurn().cancelTimerPauseGame();
			resumeBtn.setEnabled(true);
		}
		new SaveGameDialog();

	}

}
