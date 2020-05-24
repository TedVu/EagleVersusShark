package controller.abstractfactory.contract;

import model.enumtype.PieceType;

public interface ModeControllerFactory {
	
	/**
	 * @param pieceType
	 * @return
	 */
	public ModeController createModeController(PieceType pieceType);

}
