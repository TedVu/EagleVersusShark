package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import view.operationview.StatusPanel;

public class MakingMovePropertyChangeListener implements PropertyChangeListener {

	private StatusPanel statusPanel;

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getPropertyName().equalsIgnoreCase("makingmove")
				|| evt.getPropertyName().equalsIgnoreCase("switchturn")) {
			statusPanel.updateTurnLabel((String) evt.getNewValue());
			statusPanel.startCountDown();
		}
	}

	public void injectStatusPanel(StatusPanel statusPanel) {
		this.statusPanel = statusPanel;
	}
}
