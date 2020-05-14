package controller.abstractfactory.factory;

import controller.abstractfactory.ModeController;
import model.enumtype.PieceType;

public interface ModeControllerFactory {
	public ModeController createModeController(PieceType pieceType);
}
