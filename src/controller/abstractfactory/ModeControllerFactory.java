package controller.abstractfactory;

import model.enumtype.PieceType;

public interface ModeControllerFactory {
	public ModeController createModeController(PieceType pieceType);
}
