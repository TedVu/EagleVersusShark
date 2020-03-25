package view.operationview;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.Asset;
import controller.SelectPieceController;
import models.engine.EngineImpl;

/**
 * @author Ted
 * 
 *         NOTE: for 1-1 correspondence with model I use a list of list (this is
 *         a little bit model-ish)
 *
 */
public class BoardPanel extends JPanel {

	List<List<AbstractButton>> buttonList;
	private ButtonGroup group;

	/**
	 * Constructing the board panel initially is a hard-coded construction since we
	 * know exactly the beginning position of each piece
	 */
	public BoardPanel() {
		group = new ButtonGroup();
		setLayout(new GridLayout(EngineImpl.getSingletonInstance().getBoard().getRow(),
				EngineImpl.getSingletonInstance().getBoard().getCol()));
		buttonList = new ArrayList<>();

		// populate buttons here including register listener
		for (int row = 0; row < EngineImpl.getSingletonInstance().getBoard().getRow(); ++row) {
			buttonList.add(new ArrayList<AbstractButton>());
			for (int col = 0; col < EngineImpl.getSingletonInstance().getBoard().getCol(); ++col) {
				buttonList.get(row).add(new JButton());
				buttonList.get(row).get(col).setBackground(Color.WHITE);
				buttonList.get(row).get(col).setBorder(BorderFactory.createRaisedBevelBorder());
				buttonList.get(row).get(col).setActionCommand("NormalButton");
				buttonList.get(row).get(col)
						.addActionListener(new SelectPieceController(buttonList.get(row).get(col), this));
				add(buttonList.get(row).get(col));
				group.add(buttonList.get(row).get(col));
			}
		}

		populateEagleIcon();
	}

	private void populateEagleIcon() {
		try {
			Image attackingEagle = ImageIO.read(getClass().getResource(Asset.fileName.get("AttackingEagle")));
			Image visionaryEagle = ImageIO.read(getClass().getResource(Asset.fileName.get("VisionaryEagle")));
			Image leadershipEagle = ImageIO.read(getClass().getResource(Asset.fileName.get("LeadershipEagle")));

			// Hard code initial position
			buttonList.get(0).get(3).setIcon(new ImageIcon(attackingEagle));
			buttonList.get(0).get(3).setActionCommand("AttackingEagle");
			buttonList.get(1).get(4).setIcon(new ImageIcon(leadershipEagle));
			buttonList.get(1).get(4).setActionCommand("LeadershipEagle");
			buttonList.get(0).get(5).setIcon(new ImageIcon(visionaryEagle));
			buttonList.get(0).get(5).setActionCommand("VisionaryEagle");

			// set black background for master cell
			buttonList.get(0).get(4).setBackground(Color.BLACK);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<List<AbstractButton>> getButtonList() {
		return buttonList;
	}
}
