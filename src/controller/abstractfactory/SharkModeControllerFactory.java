package controller.abstractfactory;

import model.enumtype.PieceType;

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
