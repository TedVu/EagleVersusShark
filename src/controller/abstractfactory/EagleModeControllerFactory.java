package controller.abstractfactory;

import model.enumtype.PieceType;

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
			throw new IllegalArgumentException("");
		}
	}

}
