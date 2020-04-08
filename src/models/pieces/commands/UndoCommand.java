package models.pieces.commands;

public class UndoCommand implements Command{
	
	PieceOperator pieceOperator;
	
	
	
	public UndoCommand(PieceOperator pieceOperator) {
		this.pieceOperator = pieceOperator;
	}

	@Override
	public void execute() {
		pieceOperator.undo();
		
	}

	@Override
	public void undo() {
		throw new IllegalArgumentException("Unable to redo");
	}

}
