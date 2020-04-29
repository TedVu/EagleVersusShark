package view.configuration;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.startgamecontroller.LoadGameController;

/**
 * @author kevin & ted
 */
public class LoadGameDialog extends JDialog {
	/**
	 * @serial -391556514040071948L
	 */
	private static final long serialVersionUID = -391556514040071948L;
	private static final int FRAME_WIDTH = 350;
	private static final int FRAME_HEIGHT = 100;

	private JTextField fileNameField;
 
	private JButton loadGameButton;

	/**
	 * @see
	 */
	public LoadGameDialog(StartingGameFrame startFrame) {
		fileNameField = new JTextField(15); 
		loadGameButton = new JButton("Load"); 

		setTitle("Load Game");

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dimension.width / 2 - FRAME_WIDTH / 2, dimension.height / 2 - FRAME_HEIGHT / 2);
		setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setResizable(false);

		 loadGameButton.addActionListener(new LoadGameController(startFrame, this));

		JPanel textfiledPanel = new JPanel();
		textfiledPanel.add(new JLabel("File name:"));
		textfiledPanel.add(fileNameField);
		add(textfiledPanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(loadGameButton);
		add(buttonPanel, BorderLayout.SOUTH);

		this.setModal(true);
		setVisible(true);
	}

	/**
	 * @return
	 */
	public String getFileNameInput() {
		return fileNameField.getText();
	}

}