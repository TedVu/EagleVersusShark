package view.operationview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class StatusPanel extends JPanel {
	private static final int WIDTH_OF_PANEL = 230;
	private static final int HEIGHT_OF_PANEL = 0;
	private JMenuBar titleBar;
	private JLabel titleLabel;

	private JPanel contentPanel;

	public StatusPanel() {

		titleBar = new JMenuBar();
		titleLabel = new JLabel("Status Panel");
		titleBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		titleBar.setBorder(BorderFactory.createEtchedBorder());
		titleBar.setBackground(new Color(173, 202, 230));
		titleBar.add(titleLabel);

		setLayout(new BorderLayout());
		add(titleBar, BorderLayout.NORTH);
		setBorder(BorderFactory.createEtchedBorder());
		this.setPreferredSize(new Dimension(WIDTH_OF_PANEL, HEIGHT_OF_PANEL));

	}
}
