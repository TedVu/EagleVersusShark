package models.pieces.commands;

import models.pieces.Piece;

public class UseAbilityCommand implements Command{
	
	private String abilityName;
	private Piece piece, affectedPiece;
	private PieceOperator pieceOperator;
	
	public UseAbilityCommand(String abilityName, Piece piece, Piece affectedPiece, PieceOperator pieceOperator) {
		this.abilityName = abilityName;
		this.piece = piece;
		this.affectedPiece = affectedPiece;
		this.pieceOperator = pieceOperator;
	}

	@Override
	public void execute() {
		System.out.println("executing from useAbilityCommand");
		pieceOperator.useAbility(abilityName, piece, affectedPiece);
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
