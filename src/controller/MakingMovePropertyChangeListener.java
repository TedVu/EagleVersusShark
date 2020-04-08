package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import view.operationview.StatusPanel;

/**
 * 
 * @author kevin & ted
 * 
 */
public class MakingMovePropertyChangeListener implements PropertyChangeListener {

	private StatusPanel statusPanel;

	/**
	 * 
	 * @see
	 * 
	 */
	public void injectStatusPanel(StatusPanel statusPanel) {
		this.statusPanel = statusPanel;
	}

	/**
	 * 
	 * @see
	 * 
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equalsIgnoreCase("makingmove")
				|| evt.getPropertyName().equalsIgnoreCase("switchturn")) {
			statusPanel.updateTurnLabel((String) evt.getNewValue());
			statusPanel.startCountDown();
		}
	}
}
