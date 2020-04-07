package view.operationview;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
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
 * @author Ted NOTE: for 1-1 correspondence with model I use a list of list for
 *         button (this is a little bit model-ish)
 */
public class BoardPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -146176190184206205L;
	private List<List<AbstractButton>> buttons;

	/**
	 * Constructing the board panel,at the beginning, the board is a hard-coded
	 * construction since we know exactly the beginning position of each piece
	 */
	public BoardPanel() {
		ButtonGroup group = new ButtonGroup();

		int numberOfRow = EngineImpl.getSingletonInstance().getBoard().getRow();
		int numberOfCol = EngineImpl.getSingletonInstance().getBoard().getCol();

		setLayout(new GridLayout(numberOfRow, numberOfCol));
		buttons = new ArrayList<>();

		for (int row = 0; row < numberOfRow; ++row) {
			buttons.add(new ArrayList<AbstractButton>());
			for (int col = 0; col < numberOfCol; ++col) {
				JButton currentButton = new JButton();
				buttons.get(row).add(currentButton);

				currentButton.setBackground(Color.WHITE);
				currentButton.setBorder(BorderFactory.createRaisedBevelBorder());

				currentButton.setActionCommand("NormalButton");
				currentButton.addActionListener(new SelectPieceController(currentButton, this));

				add(currentButton);
				group.add(currentButton);
			}
		}

		if (!EngineImpl.getSingletonInstance().getLoadGame()) {
			defaultNumberPiece();
		}

		PropertyChangeListener[] listeners = EngineImpl.getSingletonInstance().getGameEngineCallback()
				.getPropertyChangeListener();

		for (PropertyChangeListener listener : listeners) {
			if (listener instanceof TimerPropertyChangeListener) {
				((TimerPropertyChangeListener) listener).injectBoard(this);
			}
		}
	}

	private void defaultNumberPiece() {
		int numberOfPiece = EngineImpl.getSingletonInstance().getAllPieces().size();
		if (numberOfPiece == 2) {
			populateTwoMiddlePiece();
		} else if (numberOfPiece == 4) {
			populateTwoSidePiece();
		} else if (numberOfPiece == 6) {
			populateTwoMiddlePiece();
			populateTwoSidePiece();
		}
	}

	public List<List<AbstractButton>> getButtonList() {
		return buttons;
	}

	private void populateTwoMiddlePiece() {
		populateCustomPiece(1, EngineImpl.getSingletonInstance().getBoard().getCol() / 2, PieceType.LEADERSHIPEAGLE);

		populateCustomPiece(EngineImpl.getSingletonInstance().getBoard().getCol() - 2,
				EngineImpl.getSingletonInstance().getBoard().getCol() / 2, PieceType.DEFENSIVESHARK);
	}

	private void populateTwoSidePiece() {
		populateCustomPiece(0, EngineImpl.getSingletonInstance().getBoard().getCol() / 2 - 1, PieceType.ATTACKINGEAGLE);
		populateCustomPiece(0, EngineImpl.getSingletonInstance().getBoard().getCol() / 2 + 1, PieceType.VISIONARYEAGLE);

		populateCustomPiece(EngineImpl.getSingletonInstance().getBoard().getRow() - 1,
				EngineImpl.getSingletonInstance().getBoard().getCol() / 2 - 1, PieceType.AGGRESSIVESHARK);
		populateCustomPiece(EngineImpl.getSingletonInstance().getBoard().getRow() - 1,
				EngineImpl.getSingletonInstance().getBoard().getCol() / 2 + 1, PieceType.HEALINGSHARK);
	}

	private void populateCustomPiece(int positionX, int positionY, PieceType pieceType) {
		try {
			Image pieceImage = ImageIO.read(getClass().getResource(pieceType.getFileName()));
			buttons.get(positionX).get(positionY).setIcon(new ImageIcon(pieceImage));
		} catch (IOException e) {
			e.printStackTrace();
		}
		buttons.get(positionX).get(positionY).setActionCommand(pieceType.toString());
	}

	public void restoreCellColor() {
		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(0).size(); ++col) {
				if (buttons.get(row).get(col).getBackground().equals(Color.YELLOW)
						|| buttons.get(row).get(col).getBackground().equals(Color.BLUE)) {
					buttons.get(row).get(col).setBackground(Color.WHITE);
				}
			}
		}
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

	public void restoreStateForPossibleValidMove(Set<List<Integer>> validMoves) {
		for (List<Integer> l : validMoves) {
			ActionListener[] movePieceController = buttons.get(l.get(1)).get(l.get(0)).getActionListeners();
			buttons.get(l.get(1)).get(l.get(0)).removeActionListener(movePieceController[0]);
			buttons.get(l.get(1)).get(l.get(0))
					.addActionListener(new SelectPieceController(buttons.get(l.get(1)).get(l.get(0)), this));

		}
	}

	public void restoreViewForOldPos(Map<String, Integer> oldPos) {
		buttons.get(oldPos.get("y")).get(oldPos.get("x")).setIcon(null);
		buttons.get(oldPos.get("y")).get(oldPos.get("x")).setActionCommand("NormalButton");
	}

	public void updateBoardAfterChoosingPiece(Set<List<Integer>> validMoves, PieceType pieceType) {
		EnumSet<PieceType> eagleSet = EnumSet.of(PieceType.ATTACKINGEAGLE, PieceType.LEADERSHIPEAGLE,
				PieceType.VISIONARYEAGLE);
		EnumSet<PieceType> sharkSet = EnumSet.of(PieceType.AGGRESSIVESHARK, PieceType.DEFENSIVESHARK,
				PieceType.HEALINGSHARK);

		for (List<Integer> moves : validMoves) {
			if (eagleSet.contains(pieceType)) {
				buttons.get(moves.get(1)).get(moves.get(0)).setBackground(Color.yellow);
			} else if (sharkSet.contains(pieceType)) {
				buttons.get(moves.get(1)).get(moves.get(0)).setBackground(Color.blue);
			}

			ActionListener[] selectPieceListener = buttons.get(moves.get(1)).get(moves.get(0)).getActionListeners();
			buttons.get(moves.get(1)).get(moves.get(0)).removeActionListener(selectPieceListener[0]);
		}
	}

	public void updateBoardAfterMovingPiece(AbstractButton buttonClicked, PieceType pieceType,
			Set<List<Integer>> validMoves) {
		updateIcon(buttonClicked, pieceType);
		restoreCellColor();
		restoreStateForPossibleValidMove(validMoves);
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

	public void updateBoardRollback() {
		restoreButtonStateForColorButton();
		restoreCellColor();
	}

	public void updateIcon(AbstractButton buttonClicked, PieceType pieceType) {
		Image animal = null;
		try {
			animal = ImageIO.read(getClass().getResource(pieceType.getFileName()));
		} catch (IOException e1) {
			System.err.println("IMAGE NOT FOUND");
		}
		buttonClicked.setIcon(new ImageIcon(animal));
	}

	public void loadGame() {
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
				e.printStackTrace();
			}
		}
	}
}
