package controller;

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
		// allow cancel timer depending on current player
		EngineImpl.getSingletonInstance().cancelTimerPauseGame();
		viewControllerFacade.undoMoveCancelTimer();
		new UndoMovePanel(viewControllerFacade);
		resumeBtn.setEnabled(true);

		// else err msg

	}

}
