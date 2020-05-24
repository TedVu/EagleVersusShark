package model.enumtype;

import java.awt.Color;

/**
 * @author kevin
 *
 */
public enum CellType {

	SMASTER(Color.GREEN), EMASTER(Color.GREEN), NORMAL(Color.WHITE), WATER(Color.CYAN), OBSTACLE(Color.BLACK);

	private final Color color;

	CellType(final Color color) {
		this.color = color;
	}

	public Color color() {
		return color;
	}

}
