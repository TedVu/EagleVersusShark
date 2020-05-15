package controller.playinggamecontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

import model.engine.EngineImpl;
import model.enumtype.TeamType;
import model.player.Player;
import viewcontroller.contract.ViewControllerInterface;

public class ResumeGameController implements ActionListener {

	private ViewControllerInterface viewControllerFacade;

	public ResumeGameController(ViewControllerInterface viewControllerFacade) {
		this.viewControllerFacade = viewControllerFacade;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Player currentPlayer = EngineImpl.getSingletonInstance().gameTurn().getCurrentActivePlayer();
		if (currentPlayer.getPlayerType() == TeamType.SHARK) {
			EngineImpl.getSingletonInstance().gameTurn().setActivePlayerTimer(TeamType.EAGLE);
		} else if (currentPlayer.getPlayerType() == TeamType.EAGLE) {
			EngineImpl.getSingletonInstance().gameTurn().setActivePlayerTimer(TeamType.SHARK);
		}
		EngineImpl.getSingletonInstance().gameTurn().setResumeGame();
		viewControllerFacade.resumeGame(currentPlayer.getPlayerType());
		AbstractButton buttonClicked = (AbstractButton) e.getSource();
		buttonClicked.setEnabled(false);
	}

}
