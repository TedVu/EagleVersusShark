package controller.playinggamecontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.contract.Player;
import model.enumtype.TeamType;
import modelcontroller.contract.ControllerModelInterface;
import modelcontroller.facade.ControllerModelFacadeImpl;
import view.operationview.ModePanel;
import view.operationview.StatusPanel;

/**
 * @author ted &#38; kevin
 *
 */
public class StartGameController implements ActionListener {

	private StatusPanel statusPanel;
	private ModePanel modePanel;

	private ControllerModelInterface controllerModelFacade = new ControllerModelFacadeImpl();

	/**
	 * @param statusPanel
	 */
	public StartGameController(StatusPanel statusPanel, ModePanel modePanel) {
		this.statusPanel = statusPanel;
		this.modePanel = modePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Player initialPlayer = controllerModelFacade.getInitialActivePlayer();
		statusPanel.updateTurnLabel(initialPlayer.getPlayerType());

		statusPanel.startCountDown();
		modePanel.updateAvailableMode(initialPlayer.getPlayerType());

		if (initialPlayer.getPlayerType() == TeamType.EAGLE) {
			controllerModelFacade.setTurnStartingGame(TeamType.SHARK);
		} else {
			controllerModelFacade.setTurnStartingGame(TeamType.EAGLE);
		}
		JButton startButton = (JButton) e.getSource();
		startButton.setEnabled(false);
	}

}
