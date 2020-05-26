package controller.abstractfactory.factory;

import controller.abstractfactory.contract.AbilityController;
import controller.abstractfactory.contract.AbilityControllerFactory;
import controller.abstractfactory.eagleability.AttackingEagleAbilityController;
import controller.abstractfactory.eagleability.LeadershipEagleAbilityController;
import controller.abstractfactory.eagleability.VisionaryEagleAbilityController;
import model.enumtype.PieceType;

/**
 * @author Ted & Kevin
 *
 *         A concrete factory to produce different abilities for eagle
 */
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
