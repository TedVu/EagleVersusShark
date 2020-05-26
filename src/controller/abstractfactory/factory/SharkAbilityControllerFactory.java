package controller.abstractfactory.factory;

import controller.abstractfactory.contract.AbilityController;
import controller.abstractfactory.contract.AbilityControllerFactory;
import controller.abstractfactory.sharkability.AggressiveSharkAbilityController;
import controller.abstractfactory.sharkability.DefensiveSharkAbilityController;
import controller.abstractfactory.sharkability.HealingSharkAbilityController;
import model.enumtype.PieceType;

/**
 * @author Ted & Kevin
 *
 *         A concrete factory to produce different abilities for shark
 */
public class SharkAbilityControllerFactory implements AbilityControllerFactory {

	@Override
	public AbilityController createAbilityController(PieceType sharkType) {
		if (sharkType == PieceType.AGGRESSIVESHARK) {
			return new AggressiveSharkAbilityController();
		} else if (sharkType == PieceType.DEFENSIVESHARK) {
			return new DefensiveSharkAbilityController();
		} else if (sharkType == PieceType.HEALINGSHARK) {
			return new HealingSharkAbilityController();
		} else {
			throw new IllegalArgumentException("Piece type not found");
		}
	}

}
