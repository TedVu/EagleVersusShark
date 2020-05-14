package controller.playinggamecontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.engine.EngineImpl;
import model.enumtype.TeamType;
import model.piece.commands.CommandExecutor;
import model.piece.commands.Undo;
import model.player.Player;
import view.operationview.UndoMovePanel;
import viewcontroller.contract.ViewControllerInterface;

public class ConfirmUndoController implements ActionListener {

	private ViewControllerInterface viewControllerFacade;
	private UndoMovePanel undoMovePanel;
	private CommandExecutor commandExecutor = new CommandExecutor();

	public ConfirmUndoController(ViewControllerInterface viewControllerFacade, UndoMovePanel undoMovePanel) {
		this.viewControllerFacade = viewControllerFacade;
		this.undoMovePanel = undoMovePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean undoSuccess = true;
		int numUndo = Integer.parseInt(undoMovePanel.getNumUndo());
		Player currentTeam = EngineImpl.getSingletonInstance().getCurrentActivePlayer();
		TeamType currentTeamEnum = TeamType.valueOf(currentTeam.toString());

		try {
			commandExecutor.executeCommand(new Undo(currentTeamEnum, numUndo));

		} catch (RuntimeException ex) {
			undoSuccess = false;
			viewControllerFacade.undoFail(ex.getMessage());
		}

		if (undoSuccess) {
			EngineImpl.getSingletonInstance().getCurrentActivePlayer().setAlreadyUndo();
			undoMovePanel.setVisible(false);
			viewControllerFacade.confirmUndoSuccessful();
		}
	}

}
