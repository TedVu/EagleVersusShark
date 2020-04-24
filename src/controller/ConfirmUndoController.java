package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.piece.commands.CommandExecutor;
import view.operationview.UndoMovePanel;
import viewcontroller.contract.ViewControllerInterface;

public class ConfirmUndoController implements ActionListener {

	private ViewControllerInterface viewControllerFacade;
	private UndoMovePanel undoMovePanel;
	private CommandExecutor command = new CommandExecutor();

	public ConfirmUndoController(ViewControllerInterface viewControllerFacade, UndoMovePanel undoMovePanel) {
		this.viewControllerFacade = viewControllerFacade;
		this.undoMovePanel = undoMovePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int numUndo = Integer.parseInt(undoMovePanel.getNumUndo());

		undoMovePanel.setVisible(false);
		viewControllerFacade.confirmUndoSuccessful();
	}

}
