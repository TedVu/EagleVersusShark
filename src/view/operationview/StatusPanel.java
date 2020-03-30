package view.operationview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import controller.MakingMovePropertyChangeListener;
import controller.StartGameController;
import models.engine.EngineImpl;

public class StatusPanel extends JPanel {
	private static final int WIDTH_OF_PANEL = 230;
	private static final int HEIGHT_OF_PANEL = 0;
	private JButton startButton = new JButton("START");
	private JLabel turnLabel = new JLabel("Turn:");
	private JLabel timerLabel = new JLabel("Time making move left:");

	public StatusPanel() {
		setBorder(BorderFactory.createTitledBorder("Status Panel"));
		setPreferredSize(new Dimension(WIDTH_OF_PANEL, HEIGHT_OF_PANEL));
		setLayout(new BorderLayout());
		startButton.addActionListener(new StartGameController(this));
		this.add(turnLabel, BorderLayout.NORTH);
		this.add(timerLabel, BorderLayout.CENTER);

		this.add(startButton, BorderLayout.SOUTH);
		PropertyChangeListener[] listeners = EngineImpl.getSingletonInstance().getPropertyChangeListener();
		for (PropertyChangeListener l : listeners) {
			if (l instanceof MakingMovePropertyChangeListener) {
				((MakingMovePropertyChangeListener) l).injectStatusPanel(this);
			}
		}

	}

	public void updateTurnLabel(String currentPlayerType) {
		turnLabel.setText("Turn: " + currentPlayerType);
	}

	List<SwingWorker<Void, Void>> threadPool = new ArrayList<SwingWorker<Void, Void>>();

	public void startCountDown() {

		for (SwingWorker<Void, Void> preWorker : threadPool) {
			preWorker.cancel(true);
		}
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				// Simulate doing something useful.
				for (int i = 5; i >= 0; --i) {
					timerLabel.setText("Time making move left: " + i);

					Thread.sleep(1000);
				}
				return null;
			}
		};
		threadPool.add(worker);
		worker.execute();

	}

}
