package controller;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.engine.EngineImpl;

public class StartGameController implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		EngineImpl.getSingletonInstance().getInitialPlayerActivePlayer();
		EngineImpl.getSingletonInstance().setActivePlayerTimer("eagle");

		Button button = (Button) e.getSource();
		button.setEnabled(false);
	}

}
