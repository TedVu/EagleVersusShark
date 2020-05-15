package model.engine;

import model.board.GameBoard;
import model.contract.Engine;
import model.piece.GamePiece;

public class GameConfig {
	
	private Engine engine;
	private GameBoard gameBoard;
	private GamePiece gamePiece;
	
	public void configBoardSize(int boardSize) {
		gameBoard = new GameBoard(boardSize);
//		gamePiece.setBoard(this);

	}

}
