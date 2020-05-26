package controller.abstractfactory.contract;

import model.enumtype.PieceType;

/**
 * @author Ted & Kevin
 *
 *         A contract defining what a mode controller factory should do
 */
public interface ModeControllerFactory {

	/**
	 * @param pieceType
	 * @return
	 */
	public ModeController createModeController(PieceType pieceType);

}
