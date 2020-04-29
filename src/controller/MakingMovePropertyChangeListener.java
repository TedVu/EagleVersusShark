package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.google.java.contract.Requires;

import model.enumtype.TeamType;
import view.operationview.ModePanel;
import view.operationview.StatusPanel;

/**
 * This controller responsible for updating turn label and timer count down in
 * status panel.
 * <p>
 * It handle event when user makes move or the timer is over and switch turn.
 * 
 * @author ted &#38; kevin
 *
 */
public class MakingMovePropertyChangeListener implements PropertyChangeListener {

	private StatusPanel statusPanel;
	private ModePanel modePanel;

	/**
	 * @param statusPanel
	 *            Reference from view-status panel.
	 */
	@Requires("statusPanel != null")
	public void injectStatusPanel(StatusPanel statusPanel) {
		this.statusPanel = statusPanel;
	}

	public void injectModePanel(ModePanel modePanel) {
		this.modePanel = modePanel;
	}

	/**
	 * Check Player Making Move or Switch Turn to update the Turn label, and Start
	 * the count down of the timer again.
	 */
	@Override
	@Requires("evt != null")
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equalsIgnoreCase("MakingMove")
				|| evt.getPropertyName().equalsIgnoreCase("SwitchTurn")) {
			statusPanel.updateTurnLabel((TeamType) evt.getNewValue());
			statusPanel.startCountDown();
			modePanel.updateAvailableMode((TeamType) evt.getNewValue());
		}
	}
}
