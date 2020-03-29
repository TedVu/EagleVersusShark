package view.operationview;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.Asset;
import controller.SelectPieceController;
import controller.TimerPropertyChangeListener;
import models.engine.EngineImpl;

/**
 * @author Ted
 * 
 *         NOTE: for 1-1 correspondence with model I use a list of list for
 *         button (this is a little bit model-ish)
 *
 */
public class BoardPanel extends JPanel {

	List<List<AbstractButton>> buttonList;
	private ButtonGroup group;

	/**
	 * Constructing the board panel,at the beginning the board is a hard-coded
	 * construction since we know exactly the beginning position of each piece
	 */
	public BoardPanel() {
		group = new ButtonGroup();
		int nRow = EngineImpl.getSingletonInstance().getBoard().getRow();
		int nCol = EngineImpl.getSingletonInstance().getBoard().getCol();
		setLayout(new GridLayout(nRow, nCol));
		buttonList = new ArrayList<>();

		// populate buttons here including register listener
		for (int row = 0; row < nRow; ++row) {
			buttonList.add(new ArrayList<AbstractButton>());
			for (int col = 0; col < nCol; ++col) {
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

		populate(Asset.attackingEagle, Asset.visionaryEagle, Asset.leadershipEagle);
		populate(Asset.aggressiveShark, Asset.defensiveShark, Asset.healingShark);

		PropertyChangeListener[] listeners = EngineImpl.getSingletonInstance().getPropertyChangeListener();
		for (PropertyChangeListener l : listeners) {
			if (l instanceof TimerPropertyChangeListener) {
				((TimerPropertyChangeListener) l).injectBoard(this);
			}
		}
	}

	private void populate(String pieceName1, String pieceName2, String pieceName3) {

		Map<String, Integer> posPiece1 = EngineImpl.getSingletonInstance().getAllPiecesTed().get(pieceName1)
				.getPosition();

		Map<String, Integer> posPiece2 = EngineImpl.getSingletonInstance().getAllPiecesTed().get(pieceName2)
				.getPosition();

		Map<String, Integer> posPiece3 = EngineImpl.getSingletonInstance().getAllPiecesTed().get(pieceName3)
				.getPosition();
		try {
			Image pieceImage1 = ImageIO.read(getClass().getResource(Asset.fileName.get(pieceName1)));
			Image pieceImage2 = ImageIO.read(getClass().getResource(Asset.fileName.get(pieceName2)));
			Image pieceImage3 = ImageIO.read(getClass().getResource(Asset.fileName.get(pieceName3)));

			placePieceOnBoardWhenStart(pieceName1, pieceName2, pieceName3, posPiece1, posPiece2, posPiece3, pieceImage1,
					pieceImage2, pieceImage3);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param pieceNamek
	 *            - name of piece k
	 * @param posPiecek
	 *            - initial position of piece k
	 * 
	 * @implNote This method will place piece on board in View
	 * @see EngineModelImpl for placing piece on board for Model
	 *
	 */
	private void placePieceOnBoardWhenStart(String pieceName1, String pieceName2, String pieceName3,
			Map<String, Integer> posPiece1, Map<String, Integer> posPiece2, Map<String, Integer> posPiece3,
			Image pieceImage1, Image pieceImage2, Image pieceImage3) {
		buttonList.get(posPiece1.get("y")).get(posPiece1.get("x")).setIcon(new ImageIcon(pieceImage1));
		buttonList.get(posPiece1.get("y")).get(posPiece1.get("x")).setActionCommand(pieceName1);

		buttonList.get(posPiece2.get("y")).get(posPiece2.get("x")).setIcon(new ImageIcon(pieceImage2));
		buttonList.get(posPiece2.get("y")).get(posPiece2.get("x")).setActionCommand(pieceName2);

		buttonList.get(posPiece3.get("y")).get(posPiece3.get("x")).setIcon(new ImageIcon(pieceImage3));
		buttonList.get(posPiece3.get("y")).get(posPiece3.get("x")).setActionCommand(pieceName3);
	}

	public List<List<AbstractButton>> getButtonList() {
		return buttonList;
	}
}
