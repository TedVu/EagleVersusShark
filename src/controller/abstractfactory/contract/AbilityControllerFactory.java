package controller.abstractfactory.contract;

import model.enumtype.PieceType;

/**
 * @author Ted & Kevin
 * 
 *         A contract defining what an abilitcontrollerfactory should do
 */
public interface AbilityControllerFactory {

	/**
	 * @param pieceType
	 * @return
	 */
	public AbilityController createAbilityController(PieceType pieceType);

}
