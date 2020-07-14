package model.enumtype;

import java.awt.Color;

/**
 * @author kevin
 *
 */
public enum CellType {

	/**
	 * SMASTER,EMASTER: Melon color - (R,G,B) = (227,181,164) <br>
	 *
	 * WATER: French blue - (R,G,B) = (101,175,254) <br>
	 *
	 */
	SMASTER(new Color(227, 181, 164)), EMASTER(new Color(227, 181, 164)), NORMAL(Color.WHITE),
	WATER(new Color(101, 175, 254)), OBSTACLE(Color.BLACK);

	private final Color color;

	CellType(final Color color) {
		this.color = color;
	}

	public Color color() {
		return color;
	}

}
