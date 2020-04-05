package view.configuration;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.SaveGameController;

public class SaveGameDialog extends JDialog {
	private static final int FRAME_WIDTH = 350;
	private static final int FRAME_HEIGHT = 200;

	private JFrame startFrame;
	private JTextField fileNameField = new JTextField(15);


	private JButton saveGameButton = new JButton("Save");

	public SaveGameDialog(JFrame startFrame) {
		this.startFrame = startFrame;
		saveGameButton.addActionListener(new SaveGameController(this));
		
		setTitle("Save Game");

		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		// add components to the panel
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.add(new JLabel("File name:"), constraints);

		constraints.gridx = 1;
		this.add(fileNameField, constraints);

		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.WEST;
		this.add(saveGameButton, constraints);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dimension.width / 2 - FRAME_WIDTH / 2, dimension.height / 2 - FRAME_HEIGHT / 2);
		setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		this.setResizable(false);
		this.setVisible(true);
	}

	public String getFileNameInput() {
		return fileNameField.getText();
	}

}
