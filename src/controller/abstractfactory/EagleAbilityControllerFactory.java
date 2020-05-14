package controller.abstractfactory;

import model.enumtype.PieceType;

public class EagleAbilityControllerFactory implements AbilityControllerFactory {
	@Override
	public AbilityController createAbilityController(PieceType eagleType) {
		if (eagleType == PieceType.VISIONARYEAGLE) {
			return new VisionaryEagleAbilityController();
		} else if (eagleType == PieceType.ATTACKINGEAGLE) {
			return new AttackingEagleAbilityController();
		} else if (eagleType == PieceType.LEADERSHIPEAGLE) {
			return new LeadershipEagleAbilityController();
		} else {
			throw new IllegalArgumentException("Piece type not found");
		}
	}
}
