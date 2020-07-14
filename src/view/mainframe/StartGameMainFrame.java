package view.mainframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controller.startgamecontroller.InvokeGameSettingController;
import controller.startgamecontroller.InvokeLoadGameDialogController;
import controller.startgamecontroller.StartDefaultGameController;

/**
 * Welcome menu for the game
 * 
 * @author kevin & ted
 */
public class StartGameMainFrame extends JFrame {

	private static final long serialVersionUID = -8454047695594084099L;
	private static final int FRAME_WIDTH = 550;
	private static final int FRAME_HEIGHT = 250;

	private JButton startButton;
	private JButton configBtn;
	private JButton loadButton;
	private JButton exitButton;

	private StartGameMainFrame startGameFrame = this; // for exit action to reference

	/**
	 * Build components in this frame
	 * 
	 * @see
	 */
	public StartGameMainFrame() {
		startButton = new JButton("   START GAME   ");
		configBtn = new JButton("GAME SETTINGS");
		loadButton = new JButton("    LOAD GAME    ");
		exitButton = new JButton("           EXIT           ");

		startButton.setAlignmentX(CENTER_ALIGNMENT);
		configBtn.setAlignmentX(CENTER_ALIGNMENT);
		loadButton.setAlignmentX(CENTER_ALIGNMENT);
		exitButton.setAlignmentX(CENTER_ALIGNMENT);

		setTitle("Eagle versus Shark");

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dimension.width / 2 - FRAME_WIDTH / 2, dimension.height / 2 - FRAME_HEIGHT / 2);
		setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setResizable(false);

		JPanel headingPanel = new JPanel();
		headingPanel.add(new JLabel("<html><h1><strong><i>Eagle versus Shark</i></strong><hr></h1></html>"));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
		buttonPanel.add(startButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		buttonPanel.add(configBtn);
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		buttonPanel.add(loadButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		buttonPanel.add(exitButton);

		startButton.addActionListener(new StartDefaultGameController(this));
		configBtn.addActionListener(new InvokeGameSettingController(this));
		loadButton.addActionListener(new InvokeLoadGameDialogController(this));
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startGameFrame.dispose();
			}

		});

		getContentPane().add(buttonPanel, BorderLayout.CENTER);
		getContentPane().add(headingPanel, BorderLayout.NORTH);

		// for program termination when closing frame
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}
}