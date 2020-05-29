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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import controller.abstractfactory.contract.AbilityController;
import controller.abstractfactory.contract.ModeController;
import controller.playinggamecontroller.MovePieceController;
import controller.playinggamecontroller.SelectPieceController;
import controller.playinggamecontroller.TimerPropertyChangeListener;
import model.board.Cell;
import model.contract.Engine;
import model.contract.Piece;
import model.engine.EngineImpl;
import model.enumtype.PieceType;
import view.mainframe.AppMainFrame;
import viewcontroller.contract.ViewControllerInterface;
import viewcontroller.facade.ViewControllerFacadeImpl;

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
	private AppMainFrame mainFrame;
	private List<List<AbstractButton>> buttons;
	private ViewControllerInterface facade;
	private Engine engine = EngineImpl.getSingletonInstance();

	/**
	 * Constructing the board panel and all of the necessary components
	 * <p>
	 * Layout: BorderLayout.
	 * 
	 * @see
	 */
	public BoardPanel(AppMainFrame mainFrame) {
		this.mainFrame = mainFrame;
		int boardSize = engine.gameBoard().getSize();

		ButtonGroup group = new ButtonGroup();

		buttons = new ArrayList<>();

		JPanel btnContainerPanel = new JPanel();
		btnContainerPanel.setLayout(new GridLayout(boardSize, boardSize, 0, 0));

		facade = new ViewControllerFacadeImpl();
		facade.addPropertyChangeListener(this);

		for (int row = 0; row < boardSize; ++row) {
			buttons.add(new ArrayList<AbstractButton>());

			for (int col = 0; col < boardSize; ++col) {
				JButton currentButton = new JButton();
				buttons.get(row).add(currentButton);

				currentButton.setBackground(engine.gameBoard().getCell(col, row).getType().color());
				currentButton.setBorder(BorderFactory.createRaisedBevelBorder());

				currentButton.setActionCommand("NormalButton");
				currentButton.addActionListener(new SelectPieceController(facade));

				btnContainerPanel.add(currentButton);
				group.add(currentButton);
			}
		}

		setLayout(new BorderLayout());
		add(btnContainerPanel, BorderLayout.CENTER);
		setPreferredSize(new Dimension(800, 800));
		setMaximumSize(new Dimension(800, 800));
		setMinimumSize(new Dimension(800, 800));

		populatePieces();

		PropertyChangeListener[] listeners = engine.gameTurn().getGameEngineCallback().getPropertyChangeListener();

		for (PropertyChangeListener listener : listeners) {
			if (listener instanceof TimerPropertyChangeListener) {
				((TimerPropertyChangeListener) listener).injectBoard(this);
			}
		}
	}

	private void deleteAllIconsAndCommands() {
		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(0).size(); ++col) {
				buttons.get(row).get(col).setIcon(null);
				buttons.get(row).get(col).setActionCommand("NormalButton");
			}
		}
	}

	/**
	 * Method on boardview to end the game
	 * 
	 * @param finalMsg
	 */
	public void endGame(String finalMsg) {
		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(0).size(); ++col) {
				AbstractButton btn = buttons.get(row).get(col);
				ActionListener[] listeners = btn.getActionListeners();
				for (ActionListener listener : listeners) {
					btn.removeActionListener(listener);
				}

			}
		}
		BoardPanel board = this;
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				JOptionPane.showMessageDialog(board, finalMsg);
				mainFrame.dispose();

				// ending all threads
				System.exit(0);
				return null;

			}
		};
		worker.execute();
	}

	public List<List<AbstractButton>> getButtonList() {
		return buttons;
	}

	public ViewControllerInterface getFacade() {
		return facade;
	}

	/**
	 * Post-condition is needed because this method will process new position
	 * 
	 * @param buttonClicked
	 * @param newPos
	 */
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

	/**
	 * 
	 * @param positionX
	 * @param positionY
	 * @param pieceType
	 */
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

	private void populatePieces() {
		Map<PieceType, Piece> pieces = engine.pieceOperator().getAllPieces();
		Set<PieceType> pts = pieces.keySet();
		for (PieceType pt : pts) {
			populateCustomPiece(pieces.get(pt).getPosition().get("y"), pieces.get(pt).getPosition().get("x"), pt);
		}
	}

	/*
	 * An Board API Gateway here, all events will be routed in this method
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String event = evt.getPropertyName();
		if (event.equalsIgnoreCase("UpdateBoardAfterMovingPiece")) {
			updateBoardAfterMovingPiece((AbstractButton) evt.getNewValue(),
					PieceType.parsePieceType((String) evt.getOldValue()));
		} else if (event.equalsIgnoreCase("LocateNewPosition")) {
			locateNewPos((AbstractButton) evt.getOldValue(), (Map<String, Integer>) evt.getNewValue());
		} else if (event.equalsIgnoreCase("RefreshBoard") || event.equalsIgnoreCase("UpdateBoardPauseGame")) {
			refreshBoardColorAndState();
		} else if (event.equalsIgnoreCase("UpdateBoardBeforeCommitAction")) {
			updateBoardBeforeCommitAction((ActionListener) evt.getNewValue(), (PieceType) evt.getOldValue());
		} else if (event.equalsIgnoreCase("UpdateBoardAfterSwap")) {
			updateBoardAfterSwap((AbstractButton) evt.getNewValue());
		} else if (event.equalsIgnoreCase("UpdateBoardAfterProtect")) {
			updateBoardAfterProtectSuccess();
		} else if (event.equalsIgnoreCase("UpdateBoardAfterCapture")) {
			updateBoardAfterCapture((AbstractButton) evt.getNewValue(), (PieceType) evt.getOldValue());
		} else if (event.equalsIgnoreCase("UndoSuccessful")) {
			updateBoardUndoSuccessful();
		} else if (event.equalsIgnoreCase("UpdateBoardReviveSharkSuccessful")) {
			updateBoardReviveSharkSuccessful((PieceType) evt.getNewValue());
		} else if (event.equalsIgnoreCase("UpdateBoardAfterHealingSharkUseMode")) {
			updateBoardAfterHealingSharkUseMode();
		} else if (event.equalsIgnoreCase("UpdateBoardNotification")) {
			updateBoardNotiDialog((String) evt.getNewValue());
		}
	}

	public void refreshBoardColorAndState() {
		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(0).size(); ++col) {
				AbstractButton btn = buttons.get(row).get(col);
				btn.setBackground(engine.gameBoard().getCell(col, row).getType().color());
				ActionListener[] listeners = btn.getActionListeners();
				for (ActionListener listener : listeners) {
					btn.removeActionListener(listener);
				}
				btn.addActionListener(new SelectPieceController(facade));
			}
		}
	}

	/**
	 * @return
	 */
	@Requires("oldPos!=null")
	private void restoreViewForOldPos(Map<String, Integer> oldPos) {
		AbstractButton button = buttons.get(oldPos.get("y")).get(oldPos.get("x"));
		button.setIcon(null);
		button.setActionCommand("NormalButton");
	}

	private void updateBoardAfterCapture(AbstractButton btnClicked, PieceType pieceName) {

		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(0).size(); ++col) {
				if (!engine.gameBoard().getCell(col, row).getOccupied()) {
					buttons.get(row).get(col).setIcon(null);
					buttons.get(row).get(col).setActionCommand("NormalButton");
				}
			}
		}

		btnClicked.setActionCommand(pieceName.toString());
		updateIcon(btnClicked, pieceName);
		refreshBoardColorAndState();
	}

	/**
	 * Healing shark mode is complex hence it has a separate method to handle events
	 * 
	 */
	private void updateBoardAfterHealingSharkUseMode() {

		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(0).size(); ++col) {
				AbstractButton btn = buttons.get(row).get(col);
				if (btn.getActionCommand().equalsIgnoreCase(PieceType.ATTACKINGEAGLE.name())
						|| btn.getActionCommand().equalsIgnoreCase(PieceType.LEADERSHIPEAGLE.name())
						|| btn.getActionCommand().equalsIgnoreCase(PieceType.VISIONARYEAGLE.name())) {
					btn.setActionCommand("NormalButton");
					btn.setIcon(null);
				}
			}
		}

		List<Piece> activeEagles = engine.pieceOperator().getActiveEagles();

		for (Piece eagle : activeEagles) {
			AbstractButton eagleBtn = buttons.get(eagle.getPosition().get("y")).get(eagle.getPosition().get("x"));
			eagleBtn.setActionCommand(eagle.toString());
			updateIcon(eagleBtn, PieceType.parsePieceType(eagle.toString()));
		}
		refreshBoardColorAndState();

	}

	/**
	 * @param buttonClicked
	 * @param pieceType
	 */
	@Requires({ "buttonClicked != null", "pieceType != null" })
	private void updateBoardAfterMovingPiece(AbstractButton buttonClicked, PieceType pieceType) {
		Map<String, Integer> oldPos = engine.pieceOperator().getAllPieces().get(pieceType).getPosition();
		Set<Cell> validMoves = engine.pieceOperator().getAllPieces().get(pieceType).getValidMove();

		buttonClicked.setActionCommand(pieceType.toString());
		updateIconAndButtonStateAfterMovingPiece(buttonClicked, pieceType, validMoves);
		restoreViewForOldPos(oldPos);
	}

	private void updateBoardAfterProtectSuccess() {
		JOptionPane.showMessageDialog(this, "Give protection successful");

		refreshBoardColorAndState();
	}

	private void updateBoardAfterSwap(AbstractButton buttonClicked) {
		Piece visionaryEagle = engine.pieceOperator().getAllPieces().get(PieceType.VISIONARYEAGLE);

		AbstractButton visionButton = buttons.get(visionaryEagle.getPosition().get("y"))
				.get(visionaryEagle.getPosition().get("x"));

		PieceType affectedPiece = PieceType.parsePieceType(buttonClicked.getActionCommand());

		updateIcon(buttonClicked, PieceType.VISIONARYEAGLE);
		updateIcon(visionButton, affectedPiece);

		visionButton.setActionCommand(buttonClicked.getActionCommand());
		buttonClicked.setActionCommand(PieceType.VISIONARYEAGLE.toString());

		refreshBoardColorAndState();

	}

	/**
	 * Update board before commiting action such as: Move, UseAbility, UseMode
	 * 
	 * @param abilityController
	 * @param pieceType
	 */
	private void updateBoardBeforeCommitAction(ActionListener abilityController, PieceType pieceType) {
		Piece animal = engine.pieceOperator().getAllPieces().get(pieceType);

		Set<Cell> actionCells = null;

		Color color = pieceType.team().color();
		boolean isMoveAction = false;
		boolean isAbility = false;
		if (abilityController instanceof AbilityController) {
			actionCells = animal.abilityCells();
			isAbility = true;
		} else if (abilityController instanceof ModeController) {
			actionCells = animal.modeCells();
		} else if (abilityController instanceof MovePieceController) {
			actionCells = animal.getValidMove();
			isMoveAction = true;
		}

		if ((!isMoveAction && pieceType == PieceType.ATTACKINGEAGLE)
				|| (isAbility && pieceType == PieceType.AGGRESSIVESHARK)) {
			// red coloring for capturing behaviour
			color = Color.RED;
		}

		for (Cell cell : actionCells) {
			AbstractButton affectedBtn = buttons.get(cell.getY()).get(cell.getX());

			if ((pieceType == PieceType.DEFENSIVESHARK || pieceType == PieceType.LEADERSHIPEAGLE)
					&& !affectedBtn.getActionCommand().equalsIgnoreCase("NormalButton")) {
				Color protectColor = Color.ORANGE;
				affectedBtn.setBackground(protectColor);
			} else {
				affectedBtn.setBackground(color);
			}

			ActionListener[] listeners = affectedBtn.getActionListeners();
			for (ActionListener listener : listeners) {
				affectedBtn.removeActionListener(listener);
			}
			affectedBtn.addActionListener(abilityController);
		}

	}

	/**
	 * For logging notification when playing game
	 * 
	 * @param errMsg
	 */
	private void updateBoardNotiDialog(String msg) {

		JOptionPane.showMessageDialog(this, msg);
	}

	private void updateBoardReviveSharkSuccessful(PieceType revivedPieceEnum) {
		Piece revivedPiece = engine.pieceOperator().getAllPieces().get(revivedPieceEnum);

		AbstractButton revivedBtn = buttons.get(revivedPiece.getPosition().get("y"))
				.get(revivedPiece.getPosition().get("x"));

		updateIcon(revivedBtn, revivedPieceEnum);
		revivedBtn.setActionCommand(revivedPiece.toString());
		revivedBtn.addActionListener(new SelectPieceController(facade));

	}

	private void updateBoardUndoSuccessful() {
		refreshBoardColorAndState();
		deleteAllIconsAndCommands();
		List<Piece> activeSharks = engine.pieceOperator().getActiveSharks();

		for (Piece pieces : activeSharks) {
			AbstractButton pieceBtn = buttons.get(pieces.getPosition().get("y")).get(pieces.getPosition().get("x"));
			pieceBtn.setActionCommand(pieces.toString());
			updateIcon(pieceBtn, PieceType.parsePieceType(pieces.toString()));
		}
		List<Piece> activeEagles = engine.pieceOperator().getActiveEagles();
		for (Piece pieces : activeEagles) {
			AbstractButton pieceBtn = buttons.get(pieces.getPosition().get("y")).get(pieces.getPosition().get("x"));
			pieceBtn.setActionCommand(pieces.toString());
			updateIcon(pieceBtn, PieceType.parsePieceType(pieces.toString()));
		}

		JOptionPane.showMessageDialog(this, "Undo Successful !!! Please resume game");

	}

	/**
	 * @return
	 */
	@Requires({ "buttonClicked != null", "pieceType != null" })
	private void updateIcon(AbstractButton buttonClicked, PieceType pieceType) {
		try {
			Image animal = ImageIO.read(pieceType.file());
			buttonClicked.setIcon(new ImageIcon(animal));
		} catch (IOException e1) {
			e1.getStackTrace();
		}
	}

	/**
	 * @return
	 */
	@Requires({ "buttonClicked != null", "pieceType != null", "validMoves != null" })
	public void updateIconAndButtonStateAfterMovingPiece(AbstractButton buttonClicked, PieceType pieceType,
			Set<Cell> validMoves) {
		updateIcon(buttonClicked, pieceType);
		refreshBoardColorAndState();
	}

}