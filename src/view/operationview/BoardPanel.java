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
import controller.abstractfactory.AbilityController;
import model.board.Cell;
import model.contract.EngineInterface;
import model.contract.PieceInterface;
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
	private EngineInterface engine = EngineImpl.getSingletonInstance();

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

				// water cell
				if (EngineImpl.getSingletonInstance().getBoard().getCell(col, row).getIsWaterCell()) {
					Color color = new Color(178, 221, 247);
					currentButton.setBackground(color);
				}

				if (EngineImpl.getSingletonInstance().getBoard().getCell(col, row).getIsMasterCell()) {
					Color color = new Color(7, 6, 0);
					currentButton.setBackground(color);

				}

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
		if (event.equalsIgnoreCase("UpdateBoardAfterMovingPiece")) {
			updateBoardAfterMovingPiece((AbstractButton) evt.getNewValue(),
					PieceType.parsePieceType((String) evt.getOldValue()));
		} else if (event.equalsIgnoreCase("LocateNewPosition")) {
			locateNewPos((AbstractButton) evt.getOldValue(), (Map<String, Integer>) evt.getNewValue());
		} else if (event.equalsIgnoreCase("RollbackSelectedPiece")) {
			refreshBoardColorAndState();
		} else if (event.equalsIgnoreCase("NotifyNotStartGame")) {
			MessageDialog.notifyNotStartGame(this);
		} else if (event.equalsIgnoreCase("NotifySelectWrongTeam")) {
			MessageDialog.notifySelectWrongTeam(this);
		} else if (event.equalsIgnoreCase("UpdateBoardBeforeMovingPiece")) {
			updateBoardBeforeMovingPiece((AbstractButton) evt.getOldValue(), (MovePieceController) evt.getNewValue());
		} else if (event.equalsIgnoreCase("UpdateBoardBeforeSwap")) {
			updateBoardBeforeUseAbility((AbilityController) evt.getNewValue(), PieceType.VISIONARYEAGLE);
		} else if (event.equalsIgnoreCase("UpdateBoardAfterSwap")) {
			updateBoardAfterSwap((AbstractButton) evt.getNewValue());
		} else if (event.equalsIgnoreCase("UpdateBoardChangeAction")) {
			refreshBoardColorAndState();
		} else if (event.equalsIgnoreCase("UpdateBoardBeforeLeadershipProtect")) {
			updateBoardBeforeUseAbility((AbilityController) evt.getNewValue(), PieceType.LEADERSHIPEAGLE);
		} else if (event.equalsIgnoreCase("UpdateBoardAfterLeadershipProtect")) {
			updateBoardAfterLeadershipProtect();
		} else if (event.equalsIgnoreCase("UpdateBoardBeforeAttackingEagleCapture")) {
			updateBoardBeforeCapture((AbilityController) evt.getNewValue(), PieceType.ATTACKINGEAGLE);
		} else if (event.equalsIgnoreCase("UpdateBoardAfterAttackingEagleCapture")) {
			updateBoardAfterCapture((AbstractButton) evt.getNewValue(), PieceType.ATTACKINGEAGLE);
		} else if (event.equalsIgnoreCase("UpdateBoardFailToCaptureImmunity")) {
			updateBoardFailToCaptureImmunity();
		} else if (event.equalsIgnoreCase("UndoCancelTimer")) {
			refreshBoardColorAndState();
		} else if (event.equalsIgnoreCase("ConfirmUndoSuccessful")) {
			confirmUndoSuccessful();
		} else if (event.equalsIgnoreCase("UpdateBoardBeforeAggressiveSharkCapture")) {
			updateBoardBeforeCapture((AbilityController) evt.getNewValue(), PieceType.AGGRESSIVESHARK);
		} else if (event.equalsIgnoreCase("UpdateBoardAfterAggressiveSharkCapture")) {
			updateBoardAfterCapture((AbstractButton) evt.getNewValue(), PieceType.AGGRESSIVESHARK);
		}
	}

	private void updateBoardAfterCapture(AbstractButton btnClicked, PieceType pieceName) {

		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(0).size(); ++col) {
				if (!engine.getBoard().getCell(col, row).getOccupied()) {
					buttons.get(row).get(col).setIcon(null);
					buttons.get(row).get(col).setActionCommand("NormalButton");
				}
			}
		}

		btnClicked.setActionCommand(pieceName.toString());
		updateIcon(btnClicked, pieceName);
		refreshBoardColorAndState();
	}

	private void updateBoardBeforeCapture(AbilityController captureController, PieceType pieceName) {
		PieceInterface animalCapture = EngineImpl.getSingletonInstance().pieceOperator().getAllPieces().get(pieceName);
		Set<Cell> abilityCell = animalCapture.abilityCells();
		if (abilityCell.size() > 0) {
			for (Cell cell : abilityCell) {
				AbstractButton btn = buttons.get(cell.getY()).get(cell.getX());
				buttons.get(cell.getY()).get(cell.getX()).setBackground(Color.RED);
				ActionListener[] listeners = btn.getActionListeners();

				for (ActionListener l : listeners) {
					btn.removeActionListener(l);
				}
				btn.addActionListener(captureController);
			}
		} else {
			MessageDialog.notifyNoPieceNearbyToCapture(this);
		}
	}

	private void confirmUndoSuccessful() {
		MessageDialog.notifyUndoSuccessful(this);
	}

	private void updateBoardFailToCaptureImmunity() {
		MessageDialog.notifyFailToCaptureAttacking(this);
		refreshBoardColorAndState();
	}

	private void updateBoardAfterLeadershipProtect() {
		MessageDialog.notifyProtectSuccessLeadership(this);
		refreshBoardColorAndState();
	}

	/**
	 * Do the following: </br>
	 * 1) Update icon based on buttonClicked => affectedPiece </br>
	 * 2) Recolor white</br>
	 * 3) Set action command </br>
	 * 4) Register selectpiececontroller
	 * 
	 * @param buttonClicked
	 */
	private void updateBoardAfterSwap(AbstractButton buttonClicked) {
		PieceInterface visionaryEagle = EngineImpl.getSingletonInstance().pieceOperator().getAllPieces()
				.get(PieceType.VISIONARYEAGLE);

		AbstractButton visionButton = buttons.get(visionaryEagle.getPosition().get("y"))
				.get(visionaryEagle.getPosition().get("x"));

		PieceType affectedPiece = PieceType.parsePieceType(buttonClicked.getActionCommand());

		updateIcon(buttonClicked, PieceType.VISIONARYEAGLE);
		updateIcon(visionButton, affectedPiece);

		visionButton.setActionCommand(buttonClicked.getActionCommand());
		buttonClicked.setActionCommand(PieceType.VISIONARYEAGLE.toString());

		refreshBoardColorAndState();

	}

	public void refreshBoardColorAndState() {
		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(0).size(); ++col) {
				AbstractButton btn = buttons.get(row).get(col);
				if (!engine.getBoard().getCell(col, row).getIsWaterCell()
						&& !engine.getBoard().getCell(col, row).getIsMasterCell()) {
					btn.setBackground(Color.WHITE);
				} else if (engine.getBoard().getCell(col, row).getIsWaterCell()) {
					Color color = new Color(178, 221, 247);
					btn.setBackground(color);
				} else if (engine.getBoard().getCell(col, row).getIsMasterCell()) {
					Color color = new Color(7, 6, 0);
					btn.setBackground(color);
				}
				ActionListener[] listeners = btn.getActionListeners();
				for (ActionListener listener : listeners) {
					btn.removeActionListener(listener);
				}
				btn.addActionListener(new SelectPieceController(facade));
			}
		}
	}

	/**
	 * Do the following: </br>
	 * 1) Retrieve the coordinate of the other two eagles from model </br>
	 * 2) Color the button </br>
	 * 3) Remove SelectPieceController and add SwapController
	 * 
	 * @param swapController
	 */
	private void updateBoardBeforeUseAbility(AbilityController abilityController, PieceType pieceUseAbility) {
		PieceInterface visionaryEagle = EngineImpl.getSingletonInstance().pieceOperator().getAllPieces()
				.get(pieceUseAbility);
		Set<Cell> abilityCells = visionaryEagle.abilityCells();
		if (abilityCells.size() > 0) {
			for (Cell cell : abilityCells) {
				AbstractButton affectedBtn = buttons.get(cell.getY()).get(cell.getX());
				affectedBtn.setBackground(Color.YELLOW);

				ActionListener[] listeners = affectedBtn.getActionListeners();
				for (ActionListener listener : listeners) {
					affectedBtn.removeActionListener(listener);
				}
				affectedBtn.addActionListener(abilityController);
			}
		} else {
			printErrMsg(pieceUseAbility);
		}
	}

	private void printErrMsg(PieceType pieceUseAbility) {
		if (pieceUseAbility == PieceType.LEADERSHIPEAGLE) {
			MessageDialog.notifyCannotUseAbilityLeadershipNearby(this);
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

	/**
	 * @return
	 */

	@Requires({ "buttonClicked != null", "pieceType != null", "validMoves != null" })
	public void updateIconAndButtonStateAfterMovingPiece(AbstractButton buttonClicked, PieceType pieceType,
			Set<Cell> validMoves) {
		updateIcon(buttonClicked, pieceType);
		refreshBoardColorAndState();
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

	@Requires("boardSize > 0")
	private void populatePieces(int boardSize) {
		for (PieceType pt : PieceType.values()) {
			populateCustomPiece(pt.yCoordinate(boardSize), pt.xCoordinate(boardSize), pt);

		}
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

	@Requires({ "buttonClicked!=null", "movePieceController!=null" })
	private void updateBoardBeforeMovingPiece(AbstractButton buttonClicked, MovePieceController movePieceController) {
		PieceType pieceType = PieceType.valueOf(buttonClicked.getActionCommand().toUpperCase());

		Set<Cell> validMoves = engine.pieceOperator().getAllPieces().get(pieceType).getValidMove();

		for (Cell moves : validMoves) {
			if (pieceType.team() == TeamType.EAGLE) {
				buttons.get(moves.getY()).get(moves.getX()).setBackground(Color.yellow);
			} else if (pieceType.team() == TeamType.SHARK) {
				buttons.get(moves.getY()).get(moves.getX()).setBackground(Color.blue);
			}

			ActionListener[] listeners = buttons.get(moves.getY()).get(moves.getX()).getActionListeners();
			for (ActionListener listener : listeners) {
				buttons.get(moves.getY()).get(moves.getX()).removeActionListener(listener);
			}
			buttons.get(moves.getY()).get(moves.getX()).addActionListener(movePieceController);
		}
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

	public ViewControllerInterface getFacade() {
		return facade;
	}

}
