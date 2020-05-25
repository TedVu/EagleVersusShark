package controller.abstractfactory.contract;

import model.enumtype.PieceType;

public interface AbilityControllerFactory {

	/**
	 * @param pieceType
	 * @return
	 */
	public AbilityController createAbilityController(PieceType pieceType);

}
