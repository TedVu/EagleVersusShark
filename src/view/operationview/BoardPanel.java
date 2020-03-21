package view.operationview;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import models.engine.EngineImpl;

//Board Panel is an observer of model
public class BoardPanel extends JPanel {
	private AbstractButton[] buttons = new AbstractButton[EngineImpl.BOARD_SIZE];
	private ButtonGroup group = new ButtonGroup();

	public BoardPanel() {

		setLayout(new GridLayout((int) Math.sqrt(EngineImpl.BOARD_SIZE), (int) Math.sqrt(EngineImpl.BOARD_SIZE)));
		Image img = null;
		try {
			img = ImageIO.read(getClass().getResource("/asset/AttackingEagle.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < EngineImpl.BOARD_SIZE; i++) {
			buttons[i] = new JToggleButton();
			buttons[i].setBackground(Color.WHITE);
			buttons[i].setBorder(BorderFactory.createRaisedBevelBorder());
			add(buttons[i]);
			group.add(buttons[i]);
		}

		buttons[0].setIcon(new ImageIcon(img));

	}
}
