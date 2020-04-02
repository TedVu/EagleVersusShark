package view.operationview;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.AssetHelper;
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

	private List<List<AbstractButton>> buttons;
	private ButtonGroup group;

	/**
	 * Constructing the board panel,at the beginning, the board is a hard-coded
	 * construction since we know exactly the beginning position of each piece
	 */
	public BoardPanel() {
		group = new ButtonGroup();
		int nRow = EngineImpl.getSingletonInstance().getBoard().getRow();
		int nCol = EngineImpl.getSingletonInstance().getBoard().getCol();
		setLayout(new GridLayout(nRow, nCol));
		buttons = new ArrayList<>();

		for (int row = 0; row < nRow; ++row) {
			buttons.add(new ArrayList<AbstractButton>());
			for (int col = 0; col < nCol; ++col) {
				buttons.get(row).add(new JButton());
				buttons.get(row).get(col).setBackground(Color.WHITE);
				buttons.get(row).get(col).setBorder(BorderFactory.createRaisedBevelBorder());
				buttons.get(row).get(col).setActionCommand("NormalButton");
				buttons.get(row).get(col).addActionListener(new SelectPieceController(buttons.get(row).get(col), this));
				add(buttons.get(row).get(col));
				group.add(buttons.get(row).get(col));
			}
		}

	

		populate(AssetHelper.attackingEagle, AssetHelper.visionaryEagle, AssetHelper.leadershipEagle);
		populate(AssetHelper.aggressiveShark, AssetHelper.defensiveShark, AssetHelper.healingShark);

		PropertyChangeListener[] listeners = EngineImpl.getSingletonInstance().getGameEngineCallback()
				.getPropertyChangeListener();
		for (PropertyChangeListener listener : listeners) {
			if (listener instanceof TimerPropertyChangeListener) {
				((TimerPropertyChangeListener) listener).injectBoard(this);
			}
		}
	}

	private void populate(String pieceName1, String pieceName2, String pieceName3) {

		Map<String, Integer> posPiece1 = EngineImpl.getSingletonInstance().getAllPieces().get(pieceName1).getPosition();

		Map<String, Integer> posPiece2 = EngineImpl.getSingletonInstance().getAllPieces().get(pieceName2).getPosition();

		Map<String, Integer> posPiece3 = EngineImpl.getSingletonInstance().getAllPieces().get(pieceName3).getPosition();
		try {
			Image pieceImage1 = ImageIO.read(getClass().getResource(AssetHelper.fileName.get(pieceName1)));
			Image pieceImage2 = ImageIO.read(getClass().getResource(AssetHelper.fileName.get(pieceName2)));
			Image pieceImage3 = ImageIO.read(getClass().getResource(AssetHelper.fileName.get(pieceName3)));

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
		buttons.get(posPiece1.get("y")).get(posPiece1.get("x")).setIcon(new ImageIcon(pieceImage1));
		buttons.get(posPiece1.get("y")).get(posPiece1.get("x")).setActionCommand(pieceName1);

		buttons.get(posPiece2.get("y")).get(posPiece2.get("x")).setIcon(new ImageIcon(pieceImage2));
		buttons.get(posPiece2.get("y")).get(posPiece2.get("x")).setActionCommand(pieceName2);

		buttons.get(posPiece3.get("y")).get(posPiece3.get("x")).setIcon(new ImageIcon(pieceImage3));
		buttons.get(posPiece3.get("y")).get(posPiece3.get("x")).setActionCommand(pieceName3);
	}

	public List<List<AbstractButton>> getButtonList() {
		return buttons;
	}

	/**
	 * @implNote: basically clear all effects - using color as enum would be a <br>
	 *            better design choice here
	 */
	public void updateBoardEndOfTimer() {
		int nRow = EngineImpl.getSingletonInstance().getBoard().getRow();
		int nCol = EngineImpl.getSingletonInstance().getBoard().getCol();

		for (int row = 0; row < nRow; ++row) {
			for (int col = 0; col < nCol; ++col) {
				buttons.get(row).get(col).setBackground(Color.WHITE);
				ActionListener[] listeners = buttons.get(row).get(col).getActionListeners();
				for (ActionListener listener : listeners) {
					buttons.get(row).get(col).removeActionListener(listener);
				}
				buttons.get(row).get(col).addActionListener(new SelectPieceController(buttons.get(row).get(col), this));
			}
		}
	}

	public void updateBoardAfterChoosingPiece(Set<List<Integer>> validMoves, String pieceType) {
		for (List<Integer> moves : validMoves) {
			if (AssetHelper.eagleNames.contains(pieceType.toLowerCase())) {
				buttons.get(moves.get(1)).get(moves.get(0)).setBackground(Color.yellow);
			} else if (AssetHelper.sharkNames.contains(pieceType.toLowerCase())) {
				buttons.get(moves.get(1)).get(moves.get(0)).setBackground(Color.blue);
			}

			ActionListener[] selectPieceListener = buttons.get(moves.get(1)).get(moves.get(0)).getActionListeners();
			buttons.get(moves.get(1)).get(moves.get(0)).removeActionListener(selectPieceListener[0]);

		}
	}

	public void repaintWhiteCell() {
		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(0).size(); ++col) {
				if (buttons.get(row).get(col).getBackground().equals(Color.YELLOW)
						|| buttons.get(row).get(col).getBackground().equals(Color.BLUE)) {
					buttons.get(row).get(col).setBackground(Color.WHITE);
				}
			}
		}
	}

	public void restoreButtonStateForNextTurn(Set<String> pieceName) {
		for (int i = 0; i < buttons.size(); ++i) {
			for (int j = 0; j < buttons.get(0).size(); ++j) {
				if (pieceName.contains(buttons.get(i).get(j).getActionCommand().toLowerCase())) {
					AbstractButton button = buttons.get(i).get(j);
					for (ActionListener l : button.getActionListeners()) {
						button.removeActionListener(l);
					}
					button.addActionListener(new SelectPieceController(button, this));
				}
			}
		}
	}

	public void updateIcon(AbstractButton buttonClicked, String pieceType) {
		Image animal = null;
		try {
			animal = ImageIO.read(getClass().getResource(AssetHelper.fileName.get(pieceType)));
		} catch (IOException e1) {
			System.err.println("IMAGE NOT FOUND");
		}
		buttonClicked.setIcon(new ImageIcon(animal));
	}

	public void restoreViewForOldPos(Map<String, Integer> oldPos) {
		buttons.get(oldPos.get("y")).get(oldPos.get("x")).setIcon(null);
		buttons.get(oldPos.get("y")).get(oldPos.get("x")).setActionCommand("NormalButton");
	}

	public void restoreButtonStateForColorButton() {
		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(0).size(); ++col) {
				if ((buttons.get(row).get(col).getBackground().equals(Color.YELLOW)
						|| buttons.get(row).get(col).getBackground().equals(Color.BLUE))) {
					ActionListener[] listeners = buttons.get(row).get(col).getActionListeners();
					for (ActionListener l : listeners) {
						buttons.get(row).get(col).removeActionListener(l);
					}
					buttons.get(row).get(col)
							.addActionListener(new SelectPieceController(buttons.get(row).get(col), this));
				}
			}
		}
	}

	public void restoreStateForPossibleValidMove(Set<List<Integer>> validMoves) {
		for (List<Integer> l : validMoves) {
			ActionListener[] movePieceController = buttons.get(l.get(1)).get(l.get(0)).getActionListeners();
			buttons.get(l.get(1)).get(l.get(0)).removeActionListener(movePieceController[0]);

			buttons.get(l.get(1)).get(l.get(0))
					.addActionListener(new SelectPieceController(buttons.get(l.get(1)).get(l.get(0)), this));

		}
	}

	public void updateBoardAfterMovingPiece(AbstractButton buttonClicked, String pieceType,
			Set<List<Integer>> validMoves) {
		updateIcon(buttonClicked, pieceType);
		repaintWhiteCell();
		restoreStateForPossibleValidMove(validMoves);
	}

	public void updateBoardRollback() {
		restoreButtonStateForColorButton();
		repaintWhiteCell();
	}

}
