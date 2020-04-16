package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.google.java.contract.Ensures;

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
		Player initialPlayer = EngineImpl.getSingletonInstance().getInitialPlayerActivePlayer();
		if (initialPlayer.getPlayerType() == TeamType.SHARK) {
			statusPanel.updateTurnLabel(TeamType.SHARK);
			statusPanel.startCountDown();
			EngineImpl.getSingletonInstance().setActivePlayerTimer(TeamType.EAGLE);

		} else if (initialPlayer.getPlayerType() == TeamType.EAGLE) {
			statusPanel.updateTurnLabel(TeamType.EAGLE);
			statusPanel.startCountDown();
			EngineImpl.getSingletonInstance().setActivePlayerTimer(TeamType.SHARK);
		}
		JButton startButton = (JButton) e.getSource();
		startButton.setEnabled(false);
	}

}
