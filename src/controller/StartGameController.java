package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.engine.EngineImpl;
import model.enumtype.TeamType;
import model.player.Player;
import view.operationview.StatusPanel;

/**
 * @author ted &#38; kevin
 *
 */
public class StartGameController implements ActionListener {

	private StatusPanel statusPanel;

	/**
	 * @param statusPanel
	 */
	public StartGameController(StatusPanel statusPanel) {
		this.statusPanel = statusPanel;
	}

	/**
	 *
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Player initialPlayer = EngineImpl.getSingletonInstance()
				.getInitialPlayerActivePlayer();
		statusPanel.updateTurnLabel(initialPlayer.getPlayerType());
		statusPanel.startCountDown();

		if (initialPlayer.getPlayerType() == TeamType.SHARK) {
			EngineImpl.getSingletonInstance()
					.setActivePlayerTimer(TeamType.EAGLE);
		} else if (initialPlayer.getPlayerType() == TeamType.EAGLE) {
			EngineImpl.getSingletonInstance()
					.setActivePlayerTimer(TeamType.SHARK);
		}
		JButton startButton = (JButton) e.getSource();
		startButton.setEnabled(false);
	}

}
