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
import asset.TeamType;
import controller.MovePieceController;
import controller.SelectPieceController;
import controller.TimerPropertyChangeListener;
import models.engine.EngineImpl;
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
		int boardSize = EngineImpl.getSingletonInstance().getBoard().getSize();

		ButtonGroup group = new ButtonGroup();

		buttons = new ArrayList<>();

		JPanel btnContainerPanel = new JPanel();
		btnContainerPanel.setLayout(new GridLayout(boardSize, boardSize, 0, 0));

		facade = new ViewControllerFacade();
		facade.addPropertyChangeListener(this);

		for (int row = 0; row < boardSize; ++row) {
			buttons.add(new ArrayList<AbstractButton>());

			for (int col = 0; col < boardSize; ++col) {
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

		setLayout(new BorderLayout());
		add(btnContainerPanel, BorderLayout.CENTER);
		setPreferredSize(new Dimension(800, 800));
		setMaximumSize(new Dimension(800, 800));
		setMinimumSize(new Dimension(800, 800));

		populatePieces(boardSize);

		PropertyChangeListener[] listeners = EngineImpl.getSingletonInstance().getGameEngineCallback()
				.getPropertyChangeListener();

		for (PropertyChangeListener listener : listeners) {
			if (listener instanceof TimerPropertyChangeListener) {
				((TimerPropertyChangeListener) listener).injectBoard(this);
			}
		}
	}

	private void addListenerOnValidMoveCell(AbstractButton buttonClicked, MovePieceController movePieceController) {
		PieceType pieceType = PieceType.valueOf(buttonClicked.getActionCommand().toUpperCase());
		Set<List<Integer>> validMoves = EngineImpl.getSingletonInstance().getAllPieces().get(pieceType).getValidMove();
		for (List<Integer> moves : validMoves) {
			AbstractButton button = buttons.get(moves.get(1)).get(moves.get(0));
			button.addActionListener(movePieceController);
		}
	}

	/**
	 * @return
	 */
	public List<List<AbstractButton>> getButtonList() {
		return buttons;
	}

	private void locateNewPos(AbstractButton buttonClicked, Map<String, Integer> newPos) {
		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(row).size(); ++col) {
				if (buttons.get(row).get(col).equals(buttonClicked)) {
					newPos.put("y", row);
					newPos.put("x", col);
					break;
				}
			}
		}
	}

	private void populateCustomPiece(int positionX, int positionY, PieceType pieceType) {
		try {
			Image pieceImage = ImageIO.read(pieceType.file());
			buttons.get(positionX).get(positionY).setIcon(new ImageIcon(pieceImage));
		} catch (IOException e) {
			e.printStackTrace();
		}
		buttons.get(positionX).get(positionY).setActionCommand(pieceType.toString());
	}

	private void populatePieces(int boardSize) {
		PieceType.onBoardSize(boardSize);
		for (PieceType pt : PieceType.values()) {
			populateCustomPiece(pt.yCoordinate(), pt.xCoordinate(), pt);
		}
	}

	/*
	 * Later design change to pure fabrication class for routing also change event
	 * name to be more descriptive
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String event = evt.getPropertyName();
		if (event.equalsIgnoreCase("EnableAvailableMove")) {
			updateBoardPieceSelected((AbstractButton) evt.getNewValue());
		} else if (event.equalsIgnoreCase("AddListenerValidMoveCell")) {
			addListenerOnValidMoveCell((AbstractButton) evt.getNewValue(), (MovePieceController) evt.getOldValue());
		} else if (event.equalsIgnoreCase("UpdateBoardMovingPiece")) {
			updateBoardMovingPiece((AbstractButton) evt.getNewValue(),
					PieceType.parsePieceType((String) evt.getOldValue()));
		} else if (event.equalsIgnoreCase("UpdateBoardRollback")) {
			updateBoardRollback();
		} else if (event.equalsIgnoreCase("UpdateBoardAfterChoosingPiece")) {
			updateBoardAfterChoosingPiece(PieceType.parsePieceType((String) evt.getNewValue()));
		} else if (event.equalsIgnoreCase("ReaddMovePieceController")) {
			reAddMovePieceController(PieceType.parsePieceType((String) evt.getNewValue()),
					(MovePieceController) evt.getOldValue());
		} else if (event.equalsIgnoreCase("LocateNewPosition")) {
			locateNewPos((AbstractButton) evt.getOldValue(), (Map<String, Integer>) evt.getNewValue());
		} else if (event.equalsIgnoreCase("RestoreButtonStateForNextTurn")) {
			restoreButtonStateForNextTurn();
		} else if (event.equalsIgnoreCase("RollbackSelectedPiece")) {
			rollbackSelectedPiece((AbstractButton) evt.getNewValue());
		} else if (event.equalsIgnoreCase("NotifyNotStartGame")) {
			MessageDialog.notifyNotStartGame(this);
		} else if (event.equalsIgnoreCase("NotifySelectWrongTeam")) {
			MessageDialog.notifySelectWrongTeam(this);
		}
	}

	private void reAddMovePieceController(PieceType pieceType, MovePieceController movePieceController) {
		Set<List<Integer>> validMoves = EngineImpl.getSingletonInstance().getAllPieces().get(pieceType).getValidMove();
		for (List<Integer> moves : validMoves) {
			AbstractButton button = buttons.get(moves.get(1)).get(moves.get(0));
			button.addActionListener(movePieceController);
		}
	}

	/**
	 * @return
	 */
	public void restoreButtonStateForColorButton() {
		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(row).size(); ++col) {
				AbstractButton button = buttons.get(row).get(col);
				Color color = button.getBackground();
				if (color.equals(Color.YELLOW) || color.equals(Color.BLUE)) {
					for (ActionListener al : button.getActionListeners()) {
						button.removeActionListener(al);
					}
					button.addActionListener(new SelectPieceController(facade, this));
				}
			}
		}
	}

	/**
	 * @return
	 */
	public void restoreButtonStateForNextTurn() {
		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(row).size(); ++col) {
				AbstractButton button = buttons.get(row).get(col);
				if (!button.getActionCommand().equals("NormalButton")) {
					for (ActionListener al : button.getActionListeners()) {
						button.removeActionListener(al);
					}
					button.addActionListener(new SelectPieceController(facade, this));
				}
			}
		}
	}

	/**
	 * @return
	 */
	public void restoreCellColor() {
		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(row).size(); ++col) {
				AbstractButton button = buttons.get(row).get(col);
				Color color = button.getBackground();
				if (color.equals(Color.YELLOW) || color.equals(Color.BLUE)) {
					button.setBackground(Color.WHITE);
				}
			}
		}
	}

	/**
	 * @return
	 */
	public void restoreStateForPossibleValidMove(Set<List<Integer>> validMoves) {
		for (List<Integer> l : validMoves) {
			AbstractButton button = buttons.get(l.get(1)).get(l.get(0));
			ActionListener[] movePieceController = button.getActionListeners();
			button.removeActionListener(movePieceController[0]);
			button.addActionListener(new SelectPieceController(facade, this));
		}
	}

	/**
	 * @return
	 */
	public void restoreViewForOldPos(Map<String, Integer> oldPos) {
		AbstractButton button = buttons.get(oldPos.get("y")).get(oldPos.get("x"));
		button.setIcon(null);
		button.setActionCommand("NormalButton");
	}

	private void rollbackSelectedPiece(AbstractButton buttonClicked) {
		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(row).size(); ++col) {
				Color color = buttons.get(row).get(col).getBackground();
				if (EngineImpl.getSingletonInstance()
						.checkSelectPiece(PieceType.parsePieceType(buttonClicked.getActionCommand()))
						&& (color.equals(Color.YELLOW) || color.equals(Color.BLUE))) {
					rollbackSelectedPieceStatus();
				}
			}
		}
	}

	private void rollbackSelectedPieceStatus() {
		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(row).size(); ++col) {
				AbstractButton button = buttons.get(row).get(col);
				Color color = buttons.get(row).get(col).getBackground();
				if (color.equals(Color.YELLOW) || color.equals(Color.BLUE)) {
					button.setBackground(Color.WHITE);
					for (ActionListener al : button.getActionListeners()) {
						button.removeActionListener(al);
					}
					button.addActionListener(new SelectPieceController(facade, this));
				}
			}
		}
	}

	/**
	 * @return
	 */
	public void updateBoardAfterChoosingPiece(PieceType pieceType) {
		Set<List<Integer>> validMoves = EngineImpl.getSingletonInstance().getAllPieces().get(pieceType).getValidMove();
		for (List<Integer> moves : validMoves) {
			if (pieceType.team() == TeamType.EAGLE) {
				buttons.get(moves.get(1)).get(moves.get(0)).setBackground(Color.yellow);
			} else if (pieceType.team() == TeamType.SHARK) {
				buttons.get(moves.get(1)).get(moves.get(0)).setBackground(Color.blue);
			}

			ActionListener[] selectPieceListener = buttons.get(moves.get(1)).get(moves.get(0)).getActionListeners();
			for (ActionListener listener : selectPieceListener) {
				buttons.get(moves.get(1)).get(moves.get(0)).removeActionListener(listener);
			}
		}
	}

	/**
	 * @return
	 */
	public void updateBoardAfterMovingPiece(AbstractButton buttonClicked, PieceType pieceType,
			Set<List<Integer>> validMoves) {
		updateIcon(buttonClicked, pieceType);
		restoreCellColor();
		restoreStateForPossibleValidMove(validMoves);
	}

	/**
	 * <b>Note:</b> basically clear all effects - using color as enum would be a
	 * better design choice here
	 */
	public void updateBoardEndOfTimer(int boardSize) {
		for (int row = 0; row < boardSize; ++row) {
			for (int col = 0; col < boardSize; ++col) {
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
	 * @param buttonClicked
	 * @param pieceType
	 */
	public void updateBoardMovingPiece(AbstractButton buttonClicked, PieceType pieceType) {
		Map<String, Integer> oldPos = EngineImpl.getSingletonInstance().getAllPieces().get(pieceType).getPosition();
		Set<List<Integer>> validMoves = EngineImpl.getSingletonInstance().getAllPieces().get(pieceType).getValidMove();
		buttonClicked.setActionCommand(pieceType.toString());
		updateBoardAfterMovingPiece(buttonClicked, pieceType, validMoves);
		restoreViewForOldPos(oldPos);
	}

	private void updateBoardPieceSelected(AbstractButton buttonClicked) {
		PieceType pieceType = PieceType.valueOf(buttonClicked.getActionCommand().toUpperCase());

		Set<List<Integer>> validMoves = EngineImpl.getSingletonInstance().getAllPieces().get(pieceType).getValidMove();

		for (List<Integer> moves : validMoves) {
			if (pieceType.team() == TeamType.EAGLE) {
				buttons.get(moves.get(1)).get(moves.get(0)).setBackground(Color.yellow);
			} else if (pieceType.team() == TeamType.SHARK) {
				buttons.get(moves.get(1)).get(moves.get(0)).setBackground(Color.blue);
			}

			ActionListener[] selectPieceListener = buttons.get(moves.get(1)).get(moves.get(0)).getActionListeners();
			for (ActionListener listener : selectPieceListener) {
				buttons.get(moves.get(1)).get(moves.get(0)).removeActionListener(listener);
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
	public void updateIcon(AbstractButton buttonClicked, PieceType pieceType) {
		try {
			Image animal = ImageIO.read(pieceType.file());
			buttonClicked.setIcon(new ImageIcon(animal));
		} catch (IOException e1) {
			e1.getStackTrace();
		}
	}
}
