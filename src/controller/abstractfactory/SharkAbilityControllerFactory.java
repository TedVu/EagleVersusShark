package controller.abstractfactory;

import model.enumtype.PieceType;

public class SharkAbilityControllerFactory implements AbilityControllerFactory {
	@Override
	public AbilityController createAbilityController(PieceType sharkType) {
		if (sharkType == PieceType.AGGRESSIVESHARK) {

		} else if (sharkType == PieceType.DEFENSIVESHARK) {

		} else if (sharkType == PieceType.HEALINGSHARK) {

		} else {
			throw new IllegalArgumentException("Piece type not found");
		}
		return null;
	}
}
