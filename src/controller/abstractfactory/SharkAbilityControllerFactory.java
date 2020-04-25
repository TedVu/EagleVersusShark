package controller.abstractfactory;

import model.enumtype.PieceType;

public class SharkAbilityControllerFactory implements AbilityControllerFactory {
	@Override
	public AbilityController createAbilityController(PieceType sharkType) {
		if (sharkType == PieceType.AGGRESSIVESHARK) {
			return new AggressiveSharkAbilityController();
		} else if (sharkType == PieceType.DEFENSIVESHARK) {

		} else if (sharkType == PieceType.HEALINGSHARK) {

		} else {
			throw new IllegalArgumentException("Piece type not found");
		}
		return null;
	}
}
