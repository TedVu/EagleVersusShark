package models.pieces.commands;

import models.pieces.Piece;

public class MovePieceCommand implements Command{
	
	private int newX, newY, oldX, oldY;
	private Piece piece;
	private PieceOperator pieceOperator;
	
	public MovePieceCommand(PieceOperator pieceOperator, int newX, int newY, Piece piece) {
		this.pieceOperator = pieceOperator;
		this.newX = newX;
		this.newY = newY;
		this.oldX = piece.getPosition().get("x");
		this.oldY = piece.getPosition().get("y");
		this.piece = piece;
	}

	@Override
	public void execute(){
		try {
			pieceOperator.movePiece(piece, newX, newY);
			pieceOperator.addEvt(this);
			
		} catch (Exception e) {
			throw new RuntimeException("Failed to move piece");
		}
	}

	@Override
	public void undo() {
		pieceOperator.movePiece(piece, oldX, oldY);
		
	}

}
