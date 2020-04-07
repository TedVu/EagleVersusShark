package view.configuration;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.InvokeConfigureGameDialogController;
import controller.InvokeLoadGameDialogController;

public class ConfigurationFrame extends JFrame {
	private static final int FRAME_WIDTH = 550;
	private static final int FRAME_HEIGHT = 300;

	private JButton startButton = new JButton("START GAME");
	private JButton loadButton = new JButton("LOAD GAME");
	private JButton exitButton = new JButton("EXIT");

	public ConfigurationFrame() {

		setLayout(new GridBagLayout());

		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
		gridBagConstraints.anchor = GridBagConstraints.NORTH;
		this.setTitle("Eagle versus Shark");
		add(new JLabel("<html><h1><strong><i>Eagle versus Shark Game</i></strong><hr></h1></html>"),
				gridBagConstraints);

		gridBagConstraints.anchor = GridBagConstraints.CENTER;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(0, 0, 10, 0);

		JPanel buttons = new JPanel(new GridBagLayout());
		buttons.add(startButton, gridBagConstraints);
		buttons.add(loadButton, gridBagConstraints);
		buttons.add(exitButton, gridBagConstraints);

		gridBagConstraints.weighty = 1;
		add(buttons, gridBagConstraints);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dimension.width / 2 - FRAME_WIDTH / 2, dimension.height / 2 - FRAME_HEIGHT / 2);
		setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setResizable(false);

		startButton.addActionListener(new InvokeConfigureGameDialogController(this));
		loadButton.addActionListener(new InvokeLoadGameDialogController(this));

		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}

		});

	}
}
