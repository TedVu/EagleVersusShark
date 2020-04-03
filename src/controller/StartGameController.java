package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import models.engine.EngineImpl;
import models.enumeration.Player;
import view.operationview.StatusPanel;

public class StartGameController implements ActionListener {

	private StatusPanel statusPanel;

	public StartGameController(StatusPanel statusPanel) {
		this.statusPanel = statusPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Player initialPlayer = EngineImpl.getSingletonInstance().getInitialPlayerActivePlayer();
		if (initialPlayer == AssetHelper.sharkTeamName) {
			statusPanel.updateTurnLabel("Shark");
			statusPanel.startCountDown();
			EngineImpl.getSingletonInstance().setActivePlayerTimer(Player.EAGLE);

		} else if (initialPlayer == AssetHelper.eagleTeamName) {
			statusPanel.updateTurnLabel("Eagle");
			statusPanel.startCountDown();
			EngineImpl.getSingletonInstance().setActivePlayerTimer(Player.EAGLE);

		}

		JButton startButton = (JButton) e.getSource();
		startButton.setEnabled(false);
	}

}
