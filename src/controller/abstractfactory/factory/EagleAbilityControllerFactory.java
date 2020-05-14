package controller.abstractfactory.factory;

import controller.abstractfactory.AbilityController;
import controller.abstractfactory.eagleability.AttackingEagleAbilityController;
import controller.abstractfactory.eagleability.LeadershipEagleAbilityController;
import controller.abstractfactory.eagleability.VisionaryEagleAbilityController;
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
