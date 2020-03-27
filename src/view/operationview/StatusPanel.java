package view.operationview;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import controller.StartGameController;

public class StatusPanel extends JPanel {
	private static final int WIDTH_OF_PANEL = 230;
	private static final int HEIGHT_OF_PANEL = 0;
	private Button startButton = new Button("START");

	public StatusPanel() {
		setBorder(BorderFactory.createTitledBorder("Status Panel"));
		setPreferredSize(new Dimension(WIDTH_OF_PANEL, HEIGHT_OF_PANEL));
		setLayout(new BorderLayout());
		startButton.addActionListener(new StartGameController());
		this.add(startButton, BorderLayout.CENTER);
	}
}
