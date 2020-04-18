package view.operationview;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import controller.MakingMovePropertyChangeListener;
import controller.StartGameController;
import model.engine.EngineImpl;
import model.enumtype.TeamType;

/**
 * @author ted &#38; kevin
 */
public class StatusPanel extends JPanel  {
	/**
	 * @serial 8787252718705342879L
	 */
	private static final long serialVersionUID = 8787252718705342879L;
	private JButton startButton;
	private JLabel turnLabel;
	private JTextField turnTextField;
	private JLabel timerLabel;
	private JTextField timerTextField;

	private List<SwingWorker<Void, Void>> workerThreads;

	/**
	 * @see
	 */
	public StatusPanel() {
		startButton = new JButton("START");
		turnLabel = new JLabel("Turn:");
		turnTextField = new JTextField(10);
		turnTextField.setEditable(false);
		timerLabel = new JLabel("Timer:");
		timerTextField = new JTextField(10);
		timerTextField.setEditable(false);

		workerThreads = new ArrayList<SwingWorker<Void, Void>>();

		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Status Panel"));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		startButton.addActionListener(new StartGameController(this));

		JPanel turnLabelPanel = new JPanel();
		turnLabelPanel.add(turnLabel);
		turnLabelPanel.add(turnTextField);
		add(turnLabelPanel);

		JPanel timerPanel = new JPanel();
		timerPanel.add(timerLabel);
		timerPanel.add(timerTextField);
		add(timerPanel);

		JPanel startButtonPanel = new JPanel();
		startButtonPanel.add(startButton);
		add(startButtonPanel);

		PropertyChangeListener[] listeners = EngineImpl.getSingletonInstance().getGameEngineCallback()
				.getPropertyChangeListener();
		for (PropertyChangeListener listener : listeners) {
			if (listener instanceof MakingMovePropertyChangeListener) {
				((MakingMovePropertyChangeListener) listener).injectStatusPanel(this);
			}
		}
	}

	/**
	 * @return
	 */
	public void startCountDown() {
		for (SwingWorker<Void, Void> preWorker : workerThreads) {
			preWorker.cancel(true);
		}
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				for (int i = 10; i >= 0; --i) {
					timerTextField.setText(i + "");
					Thread.sleep(1000);
				}
				return null;
			}
		};
		workerThreads.add(worker);
		worker.execute();
	}

	/**
	 * @return
	 */
	public void updateTurnLabel(TeamType currentPlayer) {
		turnTextField.setText(currentPlayer.toString());
	}

	

}
