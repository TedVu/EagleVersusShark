package view.operationview;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class RightPanel extends JPanel {

	private StatusPanel statusPanel;
	private ModePanel modePanel;

	public RightPanel() {
		statusPanel = new StatusPanel();
		modePanel = new ModePanel();
		
		
		setLayout(new GridLayout(2, 1));
		this.add(statusPanel);
		this.add(modePanel);
	}
}
