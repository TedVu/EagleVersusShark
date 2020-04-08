package models.pieces.commands;

public interface Command {
	
	public void execute();
	public void undo();
}
