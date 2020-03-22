package view.operationview;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class StatusPanel extends JPanel {
	private static final int WIDTH_OF_PANEL = 230;
	private static final int HEIGHT_OF_PANEL = 0;

	public StatusPanel() {
		setBorder(BorderFactory.createTitledBorder("Status Panel"));
		setPreferredSize(new Dimension(WIDTH_OF_PANEL, HEIGHT_OF_PANEL));
	}
}
