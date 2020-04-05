package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import asset.PieceType;
import models.engine.EngineImpl;
import models.player.Player;
import view.operationview.StatusPanel;

public class StartGameController implements ActionListener {

	private StatusPanel statusPanel;
 
	public StartGameController(StatusPanel statusPanel) {
		this.statusPanel = statusPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!EngineImpl.getSingletonInstance().getLoadGame()) {
			Player initialPlayer = EngineImpl.getSingletonInstance().getInitialPlayerActivePlayer();
			if (initialPlayer.getPlayerType().equalsIgnoreCase(PieceType.getSharkTeamName())) {
				statusPanel.updateTurnLabel("Shark");
				statusPanel.startCountDown();
				EngineImpl.getSingletonInstance().setActivePlayerTimer("eagle");

			} else if (initialPlayer.getPlayerType().equalsIgnoreCase(PieceType.getEagleTeamName())) {
				statusPanel.updateTurnLabel("Eagle");
				statusPanel.startCountDown();
				EngineImpl.getSingletonInstance().setActivePlayerTimer("");

			}
		} else {
			EngineImpl.getSingletonInstance().setStartGame();
			if (EngineImpl.getSingletonInstance().getCurrentActivePlayer().getPlayerType()
					.equalsIgnoreCase("eagleplayer")) {
				statusPanel.updateTurnLabel("Eagle");
				statusPanel.startCountDown();
				EngineImpl.getSingletonInstance().setActivePlayerTimer("shark");
			} else {
				statusPanel.updateTurnLabel("Shark");
				statusPanel.startCountDown();
				EngineImpl.getSingletonInstance().setActivePlayerTimer("eagle");
			}
		}

		JButton startButton = (JButton) e.getSource();
		startButton.setEnabled(false);
	}

}
