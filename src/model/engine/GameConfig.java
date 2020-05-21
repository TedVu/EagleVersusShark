package model.engine;

import model.contract.Engine;

public class GameConfig {
	
	private Engine engine;
	private GameBoard gameBoard;
	private GamePiece gamePiece;
	
	public void configBoardSize(int boardSize) {
		gameBoard = new GameBoard(boardSize);
//		gamePiece.setBoard(this);

	}

}
