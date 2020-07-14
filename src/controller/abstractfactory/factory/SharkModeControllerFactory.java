package controller.abstractfactory.factory;

import controller.abstractfactory.contract.ModeController;
import controller.abstractfactory.contract.ModeControllerFactory;
import controller.abstractfactory.sharkmode.AggressiveSharkModeController;
import controller.abstractfactory.sharkmode.DefensiveSharkModeController;
import controller.abstractfactory.sharkmode.HealingSharkModeController;
import model.enumtype.PieceType;

/**
 * @author Ted & Kevin
 *
 *         A concrete factory to produce different modes for shark
 */
public class SharkModeControllerFactory implements ModeControllerFactory {

	@Override
	public ModeController createModeController(PieceType pieceType) {
		if (pieceType == PieceType.AGGRESSIVESHARK) {
			return new AggressiveSharkModeController();
		} else if (pieceType == PieceType.DEFENSIVESHARK) {
			return new DefensiveSharkModeController();
		} else if (pieceType == PieceType.HEALINGSHARK) {
			return new HealingSharkModeController();
		} else {
			throw new IllegalArgumentException("Piece type not found");
		}
	}

}
