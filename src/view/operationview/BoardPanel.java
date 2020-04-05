package view.operationview;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
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

import asset.PieceType;
import controller.SelectPieceController;
import controller.TimerPropertyChangeListener;
import models.engine.EngineImpl;
import models.pieces.Piece;

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
		if (!EngineImpl.getSingletonInstance().getLoadGame()) {
			configNumPiece();
		}
		PropertyChangeListener[] listeners = EngineImpl.getSingletonInstance().getGameEngineCallback()
				.getPropertyChangeListener();
		for (PropertyChangeListener listener : listeners) {
			if (listener instanceof TimerPropertyChangeListener) {
				((TimerPropertyChangeListener) listener).injectBoard(this);
			}
		}

	}

	private void configNumPiece() {
		int numPiece = EngineImpl.getSingletonInstance().getAllPieces().size();

		if (numPiece == 2) {
			populateTwoMiddlePiece();
		} else if (numPiece == 4) {
			populateTwoSidePiece();
		} else if (numPiece == 6) {
			populateTwoMiddlePiece();
			populateTwoSidePiece();
		}
	}

	/**
	 * will extract into helper class for populate
	 */
	private void populateTwoMiddlePiece() {
		try {
			Image pieceImage1 = ImageIO
					.read(getClass().getResource(PieceType.valueOf(("LEADERSHIPEAGLE")).getFileName()));
			buttons.get(1).get(EngineImpl.getSingletonInstance().getBoard().getCol() / 2)
					.setIcon(new ImageIcon(pieceImage1));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buttons.get(1).get(EngineImpl.getSingletonInstance().getBoard().getCol() / 2)
				.setActionCommand(PieceType.LEADERSHIPEAGLE.toString());

		try {
			Image pieceImage1 = ImageIO
					.read(getClass().getResource(PieceType.valueOf(("DEFENSIVESHARK")).getFileName()));
			buttons.get(EngineImpl.getSingletonInstance().getBoard().getCol() - 2)
					.get(EngineImpl.getSingletonInstance().getBoard().getCol() / 2).setIcon(new ImageIcon(pieceImage1));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buttons.get(EngineImpl.getSingletonInstance().getBoard().getRow() - 2)
				.get(EngineImpl.getSingletonInstance().getBoard().getCol() / 2)
				.setActionCommand(PieceType.DEFENSIVESHARK.toString());
	}

	/**
	 * will extract into helper class for populate
	 */
	private void populateTwoSidePiece() {
		try {
			Image pieceImage1 = ImageIO
					.read(getClass().getResource(PieceType.valueOf(("ATTACKINGEAGLE")).getFileName()));
			Image pieceImage2 = ImageIO
					.read(getClass().getResource(PieceType.valueOf(("VISIONARYEAGLE")).getFileName()));
			buttons.get(0).get(EngineImpl.getSingletonInstance().getBoard().getCol() / 2 - 1)
					.setIcon(new ImageIcon(pieceImage1));
			buttons.get(0).get(EngineImpl.getSingletonInstance().getBoard().getCol() / 2 + 1)
					.setIcon(new ImageIcon(pieceImage2));
		} catch (IOException e) {

		}

		buttons.get(0).get(EngineImpl.getSingletonInstance().getBoard().getCol() / 2 - 1)
				.setActionCommand(PieceType.ATTACKINGEAGLE.toString());

		buttons.get(0).get(EngineImpl.getSingletonInstance().getBoard().getCol() / 2 + 1)
				.setActionCommand(PieceType.VISIONARYEAGLE.toString());

		try {
			Image pieceImage1 = ImageIO
					.read(getClass().getResource(PieceType.valueOf(("AGGRESSIVESHARK")).getFileName()));
			Image pieceImage2 = ImageIO.read(getClass().getResource(PieceType.valueOf(("HEALINGSHARK")).getFileName()));
			buttons.get(EngineImpl.getSingletonInstance().getBoard().getRow() - 1)
					.get(EngineImpl.getSingletonInstance().getBoard().getCol() / 2 - 1)
					.setIcon(new ImageIcon(pieceImage1));
			buttons.get(EngineImpl.getSingletonInstance().getBoard().getRow() - 1)
					.get(EngineImpl.getSingletonInstance().getBoard().getCol() / 2 + 1)
					.setIcon(new ImageIcon(pieceImage2));
		} catch (IOException e) {

		}

		buttons.get(EngineImpl.getSingletonInstance().getBoard().getRow() - 1)
				.get(EngineImpl.getSingletonInstance().getBoard().getCol() / 2 - 1)
				.setActionCommand(PieceType.AGGRESSIVESHARK.toString());

		buttons.get(EngineImpl.getSingletonInstance().getBoard().getRow() - 1)
				.get(EngineImpl.getSingletonInstance().getBoard().getCol() / 2 + 1)
				.setActionCommand(PieceType.HEALINGSHARK.toString());
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
		EnumSet<PieceType> eagleSet = EnumSet.of(PieceType.ATTACKINGEAGLE, PieceType.LEADERSHIPEAGLE,
				PieceType.VISIONARYEAGLE);
		EnumSet<PieceType> sharkSet = EnumSet.of(PieceType.AGGRESSIVESHARK, PieceType.DEFENSIVESHARK,
				PieceType.HEALINGSHARK);

		for (List<Integer> moves : validMoves) {
			if (eagleSet.contains(PieceType.valueOf(pieceType.toUpperCase()))) {
				buttons.get(moves.get(1)).get(moves.get(0)).setBackground(Color.yellow);
			} else if (sharkSet.contains(PieceType.valueOf(pieceType.toUpperCase()))) {
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

	public void restoreButtonStateForNextTurn(EnumSet<PieceType> pieceName) {
		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(0).size(); ++col) {
				if (!buttons.get(row).get(col).getActionCommand().equals("NormalButton") && pieceName
						.contains(PieceType.valueOf(buttons.get(row).get(col).getActionCommand().toUpperCase()))) {
					AbstractButton button = buttons.get(row).get(col);
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
			animal = ImageIO.read(getClass().getResource(PieceType.valueOf((pieceType.toUpperCase())).getFileName()));
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

	public void updateLoadPanel() {
		Map<String, Piece> pieces = EngineImpl.getSingletonInstance().getAllPieces();
		for (String pieceName : pieces.keySet()) {
			try {
				Image pieceImage = ImageIO
						.read(getClass().getResource(PieceType.valueOf((pieceName.toUpperCase())).getFileName()));
				buttons.get(pieces.get(pieceName).getPosition().get("y"))
						.get(pieces.get(pieceName).getPosition().get("x")).setIcon(new ImageIcon(pieceImage));
				buttons.get(pieces.get(pieceName).getPosition().get("y"))
						.get(pieces.get(pieceName).getPosition().get("x")).setActionCommand(pieceName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
