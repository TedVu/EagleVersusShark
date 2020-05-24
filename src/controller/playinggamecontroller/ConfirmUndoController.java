package controller.playinggamecontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.contract.Player;
import model.enumtype.TeamType;
import model.piece.commands.CommandExecutor;
import model.piece.commands.Undo;
import modelcontroller.contract.ControllerModelInterface;
import modelcontroller.facade.ControllerModelFacadeImpl;
import view.operationview.UndoMovePanel;
import viewcontroller.contract.ViewControllerInterface;

/**
 * @author ted & kevin
 * 
 *         Controller for undo action
 * 
 */
public class ConfirmUndoController implements ActionListener {

	private ViewControllerInterface viewControllerFacade;
	private ControllerModelInterface controllerModelFacade = new ControllerModelFacadeImpl();

	private UndoMovePanel undoMovePanel;
	private CommandExecutor commandExecutor = new CommandExecutor();

	public ConfirmUndoController(ViewControllerInterface viewControllerFacade, UndoMovePanel undoMovePanel) {
		this.viewControllerFacade = viewControllerFacade;
		this.undoMovePanel = undoMovePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int numUndo = Integer.parseInt(undoMovePanel.getNumUndo());
		Player currentTeam = controllerModelFacade.getCurrentActivePlayer();
		TeamType currentTeamEnum = currentTeam.getPlayerType();

		try {
			commandExecutor.executeCommand(new Undo(currentTeamEnum, numUndo));
			controllerModelFacade.setAlreadyUseUndo();
			undoMovePanel.setVisible(false);
			viewControllerFacade.confirmUndoSuccessful();

		} catch (RuntimeException ex) {
			viewControllerFacade.updateBoardNotiDialog(ex.getMessage());
		}

	}

}
