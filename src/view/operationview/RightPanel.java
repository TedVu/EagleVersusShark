package view.operationview;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class RightPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2103383416089369712L;
	private StatusPanel statusPanel;
	private ModePanel modePanel;

	public RightPanel() {
		statusPanel = new StatusPanel();
		modePanel = new ModePanel();

		setLayout(new GridLayout(2, 1));

		add(statusPanel);
		add(modePanel);
	}
}
