package view.operationview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
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
import controller.MovePieceController;
import controller.SelectPieceController;
import controller.TimerPropertyChangeListener;
import models.engine.EngineImpl;
import models.pieces.Piece;
import view.messagedialog.MessageDialog;
import viewcontroller.facade.ViewControllerFacade;
import viewcontroller.interfaces.ViewControllerInterface;

/**
 * <h1>Board Panel View</h1> BoardPanel class contains the boards (can be
 * various size).
 * <p>
 * <b>Note:</b> for 1-1 correspondence with model I use a list of list for
 * button (this is a little bit model-ish)
 * 
 * @author ted & kevin
 * @version 1.0
 * @since 07.04.20
 */
public class BoardPanel extends JPanel implements PropertyChangeListener {

	/**
	 * @serial -146176190184206205L
	 */
	private static final long serialVersionUID = -146176190184206205L;
	private List<List<AbstractButton>> buttons;

	private ViewControllerInterface facade;

	/**
	 * Constructing the board panel,at the beginning, the board is a hard-coded
	 * construction since we know exactly the beginning position of each piece
	 * <p>
	 * Layout: BorderLayout.
	 * 
	 * @see
	 */
	public BoardPanel() {
		ButtonGroup group = new ButtonGroup();

		int numberOfRow = EngineImpl.getSingletonInstance().getBoard().getRow();
		int numberOfCol = EngineImpl.getSingletonInstance().getBoard().getCol();

		buttons = new ArrayList<>();

		setLayout(new BorderLayout());

		JPanel btnContainerPanel = new JPanel();
		btnContainerPanel.setLayout(new GridLayout(numberOfRow, numberOfCol, 0, 0));

		facade = new ViewControllerFacade();
		facade.addPropertyChangeListener(this);

		for (int row = 0; row < numberOfRow; ++row) {
			buttons.add(new ArrayList<AbstractButton>());
			for (int col = 0; col < numberOfCol; ++col) {
				JButton currentButton = new JButton();
				buttons.get(row).add(currentButton);

				currentButton.setBackground(Color.WHITE);
				currentButton.setBorder(BorderFactory.createRaisedBevelBorder());

				currentButton.setActionCommand("NormalButton");
				currentButton.addActionListener(new SelectPieceController(facade, this));

				btnContainerPanel.add(currentButton);
				group.add(currentButton);

			}
		}
		add(btnContainerPanel, BorderLayout.CENTER);
		setPreferredSize(new Dimension(800, 800));
		setMaximumSize(new Dimension(800, 800));
		setMinimumSize(new Dimension(800, 800));

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

	/**
	 * @return
	 */
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

	/**
	 * @return
	 */
	public List<List<AbstractButton>> getButtonList() {
		return buttons;
	}

	/**
	 * @return
	 */
	private void populateTwoMiddlePiece() {
		populateCustomPiece(1, EngineImpl.getSingletonInstance().getBoard().getCol() / 2, PieceType.LEADERSHIPEAGLE);

		populateCustomPiece(EngineImpl.getSingletonInstance().getBoard().getCol() - 2,
				EngineImpl.getSingletonInstance().getBoard().getCol() / 2, PieceType.DEFENSIVESHARK);
	}

	/**
	 * @return
	 */
	private void populateTwoSidePiece() {
		populateCustomPiece(0, EngineImpl.getSingletonInstance().getBoard().getCol() / 2 - 1, PieceType.ATTACKINGEAGLE);
		populateCustomPiece(0, EngineImpl.getSingletonInstance().getBoard().getCol() / 2 + 1, PieceType.VISIONARYEAGLE);

		populateCustomPiece(EngineImpl.getSingletonInstance().getBoard().getRow() - 1,
				EngineImpl.getSingletonInstance().getBoard().getCol() / 2 - 1, PieceType.AGGRESSIVESHARK);
		populateCustomPiece(EngineImpl.getSingletonInstance().getBoard().getRow() - 1,
				EngineImpl.getSingletonInstance().getBoard().getCol() / 2 + 1, PieceType.HEALINGSHARK);
	}

	/**
	 * @return
	 */
	private void populateCustomPiece(int positionX, int positionY, PieceType pieceType) {
		try {
			Image pieceImage = ImageIO.read(getClass().getResource(pieceType.getFileName()));
			buttons.get(positionX).get(positionY).setIcon(new ImageIcon(pieceImage));
		} catch (IOException e) {
			e.printStackTrace();
		}
		buttons.get(positionX).get(positionY).setActionCommand(pieceType.toString());
	}

	/**
	 * @return
	 */
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

	/**
	 * @return
	 */
	public void restoreButtonStateForColorButton() {
		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(0).size(); ++col) {
				if ((buttons.get(row).get(col).getBackground().equals(Color.YELLOW)
						|| buttons.get(row).get(col).getBackground().equals(Color.BLUE))) {
					ActionListener[] listeners = buttons.get(row).get(col).getActionListeners();
					for (ActionListener l : listeners) {
						buttons.get(row).get(col).removeActionListener(l);
					}
					buttons.get(row).get(col).addActionListener(new SelectPieceController(facade, this));
				}
			}
		}
	}

	/**
	 * @return
	 */
	public void restoreButtonStateForNextTurn(EnumSet<PieceType> pieceName) {
		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(0).size(); ++col) {
				if (!buttons.get(row).get(col).getActionCommand().equals("NormalButton") && pieceName
						.contains(PieceType.valueOf(buttons.get(row).get(col).getActionCommand().toUpperCase()))) {
					AbstractButton button = buttons.get(row).get(col);
					for (ActionListener l : button.getActionListeners()) {
						button.removeActionListener(l);
					}
					button.addActionListener(new SelectPieceController(facade, this));
				}
			}
		}
	}

	/**
	 * @return
	 */
	public void restoreStateForPossibleValidMove(Set<List<Integer>> validMoves) {
		for (List<Integer> l : validMoves) {
			ActionListener[] movePieceController = buttons.get(l.get(1)).get(l.get(0)).getActionListeners();
			buttons.get(l.get(1)).get(l.get(0)).removeActionListener(movePieceController[0]);
			buttons.get(l.get(1)).get(l.get(0)).addActionListener(new SelectPieceController(facade, this));

		}
	}

	/**
	 * @return
	 */
	public void restoreViewForOldPos(Map<String, Integer> oldPos) {
		buttons.get(oldPos.get("y")).get(oldPos.get("x")).setIcon(null);
		buttons.get(oldPos.get("y")).get(oldPos.get("x")).setActionCommand("NormalButton");
	}

	/**
	 * @return
	 */
	public void updateBoardAfterChoosingPiece(String pieceType) {
		EnumSet<PieceType> eagleSet = EnumSet.of(PieceType.ATTACKINGEAGLE, PieceType.LEADERSHIPEAGLE,
				PieceType.VISIONARYEAGLE);
		EnumSet<PieceType> sharkSet = EnumSet.of(PieceType.AGGRESSIVESHARK, PieceType.DEFENSIVESHARK,
				PieceType.HEALINGSHARK);
		PieceType pieceTypeEnum = PieceType.valueOf(pieceType.toUpperCase());

		Set<List<Integer>> validMoves = EngineImpl.getSingletonInstance().getAllPieces().get(pieceType.toString())
				.getValidMove();

		for (List<Integer> moves : validMoves) {
			if (eagleSet.contains(pieceTypeEnum)) {
				buttons.get(moves.get(1)).get(moves.get(0)).setBackground(Color.yellow);
			} else if (sharkSet.contains(pieceTypeEnum)) {
				buttons.get(moves.get(1)).get(moves.get(0)).setBackground(Color.blue);
			}

			ActionListener[] selectPieceListener = buttons.get(moves.get(1)).get(moves.get(0)).getActionListeners();
			for (ActionListener listener : selectPieceListener) {
				buttons.get(moves.get(1)).get(moves.get(0)).removeActionListener(listener);
			}
		}
	}

	public void updateBoardMovingPiece(AbstractButton buttonClicked, String pieceType) {
		Map<String, Integer> oldPos = EngineImpl.getSingletonInstance().getAllPieces().get(pieceType.toString())
				.getPosition();
		Set<List<Integer>> validMoves = EngineImpl.getSingletonInstance().getAllPieces().get(pieceType.toString())
				.getValidMove();
		buttonClicked.setActionCommand(pieceType.toString());
		updateBoardAfterMovingPiece(buttonClicked, pieceType, validMoves);
		restoreViewForOldPos(oldPos);
	}

	/**
	 * @return
	 */
	public void updateBoardAfterMovingPiece(AbstractButton buttonClicked, String pieceType,
			Set<List<Integer>> validMoves) {
		updateIcon(buttonClicked, pieceType);
		restoreCellColor();
		restoreStateForPossibleValidMove(validMoves);
	}

	/**
	 * <b>Note:</b> basically clear all effects - using color as enum would be a
	 * better design choice here
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
				buttons.get(row).get(col).addActionListener(new SelectPieceController(facade, this));
			}
		}
	}

	/**
	 * @return
	 */
	public void updateBoardRollback() {
		restoreButtonStateForColorButton();
		restoreCellColor();
	}

	/**
	 * @return
	 */
	public void updateIcon(AbstractButton buttonClicked, String pieceType) {
		Image animal = null;

		try {
			PieceType pieceTypeEnum = PieceType.valueOf(pieceType.toUpperCase());
			animal = ImageIO.read(getClass().getResource(pieceTypeEnum.getFileName()));
		} catch (IOException e1) {
			System.err.println("IMAGE NOT FOUND");
		}
		buttonClicked.setIcon(new ImageIcon(animal));
	}

	/**
	 * @return
	 */
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

	private void updateBoardPieceSelected(AbstractButton buttonClicked) {
		PieceType pieceType = PieceType.valueOf(buttonClicked.getActionCommand().toUpperCase());

		Set<List<Integer>> validMoves = EngineImpl.getSingletonInstance().getAllPieces().get(pieceType.toString())
				.getValidMove();

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
			for (ActionListener listener : selectPieceListener) {
				buttons.get(moves.get(1)).get(moves.get(0)).removeActionListener(listener);
			}
		}
	}

	private void addListenerOnValidMoveCell(AbstractButton buttonClicked, MovePieceController movePieceController) {
		PieceType pieceType = PieceType.valueOf(buttonClicked.getActionCommand().toUpperCase());

		Set<List<Integer>> validMoves = EngineImpl.getSingletonInstance().getAllPieces().get(pieceType.toString())
				.getValidMove();
		for (List<Integer> moves : validMoves) {
			buttons.get(moves.get(1)).get(moves.get(0)).addActionListener(movePieceController);
		}
	}

	private void reAddMovePieceController(String pieceType, MovePieceController movePieceController) {
		Set<List<Integer>> validMoves = EngineImpl.getSingletonInstance().getAllPieces().get(pieceType.toString())
				.getValidMove();

		for (List<Integer> moves : validMoves) {
			buttons.get(moves.get(1)).get(moves.get(0)).addActionListener(movePieceController);
		}
	}

	private void locateNewPos(AbstractButton buttonClicked, Map<String, Integer> newPos) {
		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(0).size(); ++col) {
				if (buttons.get(row).get(col).equals(buttonClicked)) {
					newPos.put("y", row);
					newPos.put("x", col);
					break;
				}
			}
		}
	}

	private void rollbackSelectedPiece(AbstractButton buttonClicked) {
		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(0).size(); ++col) {
				if (EngineImpl.getSingletonInstance().checkSelectPiece(buttonClicked.getActionCommand())
						&& (buttons.get(row).get(col).getBackground().equals(Color.YELLOW)
								|| buttons.get(row).get(col).getBackground().equals(Color.BLUE))) {
					rollbackSelectedPieceStatus();
				}
			}
		}
	}

	private void rollbackSelectedPieceStatus() {

		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(0).size(); ++col) {

				if (buttons.get(row).get(col).getBackground().equals(Color.YELLOW)
						|| buttons.get(row).get(col).getBackground().equals(Color.BLUE)) {
					buttons.get(row).get(col).setBackground(Color.WHITE);

					ActionListener[] listenersAttached = buttons.get(row).get(col).getActionListeners();
					for (ActionListener listener : listenersAttached) {
						buttons.get(row).get(col).removeActionListener(listener);
					}
					buttons.get(row).get(col).addActionListener(new SelectPieceController(facade, this));
				}
			}
		}
	}

	/*
	 * Later design change to pure fabrication class for routing also change event
	 * name to be more descriptive
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getPropertyName().equalsIgnoreCase("EnableAvailableMove")) {
			updateBoardPieceSelected((AbstractButton) evt.getNewValue());
		} else if (evt.getPropertyName().equalsIgnoreCase("AddListenerValidMoveCell")) {
			addListenerOnValidMoveCell((AbstractButton) evt.getNewValue(), (MovePieceController) evt.getOldValue());
		} else if (evt.getPropertyName().equalsIgnoreCase("UpdateBoardMovingPiece")) {
			updateBoardMovingPiece((AbstractButton) evt.getNewValue(), (String) evt.getOldValue());
		} else if (evt.getPropertyName().equalsIgnoreCase("UpdateBoardRollback")) {
			updateBoardRollback();
		} else if (evt.getPropertyName().equalsIgnoreCase("UpdateBoardAfterChoosingPiece")) {
			updateBoardAfterChoosingPiece((String) evt.getNewValue());
		} else if (evt.getPropertyName().equalsIgnoreCase("ReaddMovePieceController")) {
			reAddMovePieceController((String) evt.getNewValue(), (MovePieceController) evt.getOldValue());
		} else if (evt.getPropertyName().equalsIgnoreCase("LocateNewPosition")) {
			locateNewPos((AbstractButton) evt.getOldValue(), (Map<String, Integer>) evt.getNewValue());
		} else if (evt.getPropertyName().equalsIgnoreCase("RestoreButtonStateForNextTurn")) {
			restoreButtonStateForNextTurn((EnumSet<PieceType>) evt.getNewValue());
		} else if (evt.getPropertyName().equalsIgnoreCase("RollbackSelectedPiece")) {
			rollbackSelectedPiece((AbstractButton) evt.getNewValue());
		} else if (evt.getPropertyName().equalsIgnoreCase("NotifyNotStartGame")) {
			MessageDialog.notifyNotStartGame(this);
		} else if (evt.getPropertyName().equalsIgnoreCase("NotifySelectWrongTeam")) {
			MessageDialog.notifySelectWrongTeam(this);
		}
	}

}
