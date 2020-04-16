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

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import controller.MovePieceController;
import controller.SelectPieceController;
import controller.TimerPropertyChangeListener;
import model.board.Cell;
import model.engine.EngineImpl;
import model.enumtype.PieceType;
import model.enumtype.TeamType;
import view.messagedialog.MessageDialog;
import viewcontroller.contract.ViewControllerInterface;
import viewcontroller.facade.ViewControllerFacade;

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

	/**
	 * @return
	 */
	public List<List<AbstractButton>> getButtonList() {
		return buttons;
	}

	/*
	 * Later design change to pure fabrication class for routing also change event
	 * name to be more descriptive
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String event = evt.getPropertyName();
		if (event.equalsIgnoreCase("UpdateBoardMovingPiece")) {
			updateBoardMovingPiece((AbstractButton) evt.getNewValue(),
					PieceType.parsePieceType((String) evt.getOldValue()));
		} else if (event.equalsIgnoreCase("LocateNewPosition")) {
			locateNewPos((AbstractButton) evt.getOldValue(), (Map<String, Integer>) evt.getNewValue());
		} else if (event.equalsIgnoreCase("RollbackSelectedPiece")) {
			rollbackSelectedPiece((AbstractButton) evt.getNewValue());
		} else if (event.equalsIgnoreCase("NotifyNotStartGame")) {
			MessageDialog.notifyNotStartGame(this);
		} else if (event.equalsIgnoreCase("NotifySelectWrongTeam")) {
			MessageDialog.notifySelectWrongTeam(this);
		} else if (event.equalsIgnoreCase("UpdateBoardBeforeMovingPiece")) {
			updateBoardBeforeMovingPiece((AbstractButton) evt.getOldValue(), (MovePieceController) evt.getNewValue());
		}
	}

	private void updateBoardBeforeMovingPiece(AbstractButton buttonClicked, MovePieceController movePieceControlelr) {
		PieceType pieceType = PieceType.valueOf(buttonClicked.getActionCommand().toUpperCase());

		Set<Cell> validMoves = EngineImpl.getSingletonInstance().getAllPieces().get(pieceType).getValidMove();

		for (Cell moves : validMoves) {
			if (pieceType.team() == TeamType.EAGLE) {
				buttons.get(moves.getY()).get(moves.getX()).setBackground(Color.yellow);
			} else if (pieceType.team() == TeamType.SHARK) {
				buttons.get(moves.getY()).get(moves.getX()).setBackground(Color.blue);
			}

			ActionListener[] selectPieceListener = buttons.get(moves.getY()).get(moves.getX()).getActionListeners();
			for (ActionListener listener : selectPieceListener) {
				buttons.get(moves.getY()).get(moves.getX()).removeActionListener(listener);
			}
			buttons.get(moves.getY()).get(moves.getX()).addActionListener(movePieceControlelr);
		}
	}

	/**
	 * @return
	 */
	private void restoreViewForOldPos(Map<String, Integer> oldPos) {
		AbstractButton button = buttons.get(oldPos.get("y")).get(oldPos.get("x"));
		button.setIcon(null);
		button.setActionCommand("NormalButton");
	}

	/**
	 * @return
	 */

	@Requires({ "buttonClicked != null", "pieceType != null", "validMoves != null" })
	public void updateIconAndButtonStateAfterMovingPiece(AbstractButton buttonClicked, PieceType pieceType,
			Set<Cell> validMoves) {
		updateIcon(buttonClicked, pieceType);
		updateBoardStateAfterMove();

	}

	private void updateBoardStateAfterMove() {
		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(0).size(); ++col) {
				buttons.get(row).get(col).setBackground(Color.WHITE);
				ActionListener[] listeners = buttons.get(row).get(col).getActionListeners();
				for (ActionListener l : listeners) {
					buttons.get(row).get(col).removeActionListener(l);
				}
				buttons.get(row).get(col).addActionListener(new SelectPieceController(facade, this));

			}
		}
	}

	@Requires("boardSize > 0")
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
	@Requires({ "buttonClicked != null", "pieceType != null" })
	public void updateBoardMovingPiece(AbstractButton buttonClicked, PieceType pieceType) {
		Map<String, Integer> oldPos = EngineImpl.getSingletonInstance().getAllPieces().get(pieceType).getPosition();
		Set<Cell> validMoves = EngineImpl.getSingletonInstance().getAllPieces().get(pieceType).getValidMove();

		buttonClicked.setActionCommand(pieceType.toString());
		updateIconAndButtonStateAfterMovingPiece(buttonClicked, pieceType, validMoves);
		restoreViewForOldPos(oldPos);
	}

	/**
	 * @return
	 */
	@Requires({ "buttonClicked != null", "pieceType != null" })
	public void updateIcon(AbstractButton buttonClicked, PieceType pieceType) {
		try {
			Image animal = ImageIO.read(pieceType.file());
			buttonClicked.setIcon(new ImageIcon(animal));
		} catch (IOException e1) {
			e1.getStackTrace();
		}
	}

	@Requires({ "buttonClicked != null", "newPos != null" })
	@Ensures("newPos.size()>0")
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

	@Requires({ "positionX >= 0", "positionY >= 0", "pieceType != null" })
	private void populateCustomPiece(int positionX, int positionY, PieceType pieceType) {
		try {
			Image pieceImage = ImageIO.read(pieceType.file());
			buttons.get(positionX).get(positionY).setIcon(new ImageIcon(pieceImage));
		} catch (IOException e) {
			e.printStackTrace();
		}
		buttons.get(positionX).get(positionY).setActionCommand(pieceType.toString());
	}

	@Requires("boardSize > 0")
	private void populatePieces(int boardSize) {
		for (PieceType pt : PieceType.values()) {
			populateCustomPiece(pt.yCoordinate(boardSize), pt.xCoordinate(boardSize), pt);

		}
	}

	@Requires("buttonClicked != null")
	private void rollbackSelectedPiece(AbstractButton buttonClicked) {
		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(row).size(); ++col) {
				Color color = buttons.get(row).get(col).getBackground();
				if (EngineImpl.getSingletonInstance()
						.checkSelectPiece(PieceType.parsePieceType(buttonClicked.getActionCommand()))
						&& (color.equals(Color.YELLOW) || color.equals(Color.BLUE))) {
					AbstractButton button = buttons.get(row).get(col);
					button.setBackground(Color.WHITE);
					for (ActionListener al : button.getActionListeners()) {
						button.removeActionListener(al);
					}
					button.addActionListener(new SelectPieceController(facade, this));

				}
			}
		}
	}

}
