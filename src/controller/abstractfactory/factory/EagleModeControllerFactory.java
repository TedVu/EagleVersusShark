package controller.abstractfactory.factory;

import controller.abstractfactory.contract.ModeController;
import controller.abstractfactory.contract.ModeControllerFactory;
import controller.abstractfactory.eaglemode.AttackingEagleModeController;
import controller.abstractfactory.eaglemode.LeadershipEagleModeController;
import controller.abstractfactory.eaglemode.VisionaryEagleModeController;
import model.enumtype.PieceType;

/**
 * @author Ted & Kevin
 *
 *         A concrete factory to produce different modes for eagle
 */
public class EagleModeControllerFactory implements ModeControllerFactory {

	@Override
	public ModeController createModeController(PieceType pieceType) {
		if (pieceType == PieceType.ATTACKINGEAGLE) {
			return new AttackingEagleModeController();
		} else if (pieceType == PieceType.LEADERSHIPEAGLE) {
			return new LeadershipEagleModeController();
		} else if (pieceType == PieceType.VISIONARYEAGLE) {
			return new VisionaryEagleModeController();
		} else {
			throw new IllegalArgumentException("Piece type not found");
		}
	}

}
