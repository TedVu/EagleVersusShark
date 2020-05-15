package controller.playinggamecontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.engine.EngineImpl;
import model.enumtype.TeamType;
import model.player.Player;
import view.operationview.ModePanel;
import view.operationview.StatusPanel;

/**
 * @author ted &#38; kevin
 *
 */
public class StartGameController implements ActionListener {

	private StatusPanel statusPanel;
	private ModePanel modePanel;

	/**
	 * @param statusPanel
	 */
	public StartGameController(StatusPanel statusPanel, ModePanel modePanel) {
		this.statusPanel = statusPanel;
		this.modePanel = modePanel;
	}

	/**
	 *
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Player initialPlayer = EngineImpl.getSingletonInstance().gameTurn().getInitialPlayerActivePlayer();
		statusPanel.updateTurnLabel(initialPlayer.getPlayerType());

		statusPanel.startCountDown();
		modePanel.updateAvailableMode(initialPlayer.getPlayerType());

		if (initialPlayer.getPlayerType() == TeamType.SHARK) {
			EngineImpl.getSingletonInstance().gameTurn().setActivePlayerTimer(TeamType.EAGLE);
		} else if (initialPlayer.getPlayerType() == TeamType.EAGLE) {
			EngineImpl.getSingletonInstance().gameTurn().setActivePlayerTimer(TeamType.SHARK);
		}
		JButton startButton = (JButton) e.getSource();
		startButton.setEnabled(false);
	}

}
