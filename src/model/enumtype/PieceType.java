package model.enumtype;

import java.io.File;

/**
 * Type of all pieces according to game rules.
 * 
 * @author ted &#38; kevin
 *
 */
public enum PieceType {
	/**
	 * Attacking Eagle form Eagle team. Pre-calculated position (x,y) according to
	 * the board size.
	 * 
	 * @author ted &#38; kevin
	 *
	 */
	ATTACKINGEAGLE(new File("asset\\AttackingEagle.png"), TeamType.EAGLE) {
		/**
		 * Attacking Eagle x is 1 cell to the left of middle column.
		 * 
		 * @param boardSize TODO
		 */
		@Override
		public int xCoordinate(final int boardSize) {
			return boardSize / 2 - 1;
		}

		/**
		 * Attacking Eagle y is on the top row.
		 * 
		 * @param boardSize TODO
		 */
		@Override
		public int yCoordinate(final int boardSize) {
			return 0;
		}
	},

	/**
	 * Leadership Eagle from Eagle team. Pre-calculated position (x,y) according to
	 * the board size.
	 * 
	 * @author ted &#38; kevin
	 *
	 */
	LEADERSHIPEAGLE(new File("asset\\LeadershipEagle.png"), TeamType.EAGLE) {
		/**
		 * Leadership Eagle x is in the middle column.
		 * 
		 * @param boardSize TODO
		 */
		@Override
		public int xCoordinate(final int boardSize) {
			return boardSize / 2;
		}

		/**
		 * Leadership Eagle y is 1 cell below the top row.
		 * 
		 * @param boardSize TODO
		 */
		@Override
		public int yCoordinate(final int boardSize) {
			return 1;
		}
	},

	/**
	 * Visionary Eagle from Eagle team. Pre-calculated position (x,y) according to
	 * the board size.
	 * 
	 * @author ted &#38; kevin
	 *
	 */
	VISIONARYEAGLE(new File("asset\\VisionaryEagle.png"), TeamType.EAGLE) {
		/**
		 * Visionary Eagle x is 1 cell to the right of the middle column.
		 * 
		 * @param boardSize TODO
		 */
		@Override
		public int xCoordinate(final int boardSize) {
			return boardSize / 2 + 1;
		}

		/**
		 * Visionary Eagle y is on the top row.
		 * 
		 * @param boardSize TODO
		 */
		@Override
		public int yCoordinate(final int boardSize) {
			return 0;
		}
	},

	/**
	 * Aggressive Shark from Shark team. Pre-calculated position (x,y) according to
	 * the board size.
	 * 
	 * @author ted &#38; kevin
	 *
	 */
	AGGRESSIVESHARK(new File("asset\\AggressiveShark.png"), TeamType.SHARK) {
		/**
		 * Aggressive Shark x is 1 cell to the left of the middle column.
		 * 
		 * @param boardSize TODO
		 */
		@Override
		public int xCoordinate(final int boardSize) {
			return boardSize / 2 - 1;
		}

		/**
		 * Aggressive Shark y is on the bottom row.
		 * 
		 * @param boardSize TODO
		 */
		@Override
		public int yCoordinate(final int boardSize) {
			return boardSize - 1;
		}
	},

	/**
	 * Defensive Shark from Shark team. Pre-calculated position (x,y) according to
	 * the board size.
	 * 
	 * @author ted &#38; kevin
	 *
	 */
	DEFENSIVESHARK(new File("asset\\DefensiveShark.png"), TeamType.SHARK) {
		/**
		 * Defensive Shark x is in the middle column.
		 * 
		 * @param boardSize TODO
		 */
		@Override
		public int xCoordinate(final int boardSize) {
			return boardSize / 2;
		}

		/**
		 * Defensive Shark y is 1 cell above the bottom row.
		 * 
		 * @param boardSize TODO
		 */
		@Override
		public int yCoordinate(final int boardSize) {
			return boardSize - 2;
		}
	},

	/**
	 * Healing Shark from Shark team. Pre-calculated position (x,y) according to the
	 * board size.
	 * 
	 * @author ted &#38; kevin
	 *
	 */
	HEALINGSHARK(new File("asset\\HealingShark.png"), TeamType.SHARK) {
		/**
		 * Healing Shark x is 1 cell to the right of the middle column.
		 * 
		 * @param boardSize TODO
		 */
		@Override
		public int xCoordinate(final int boardSize) {
			return boardSize / 2 + 1;
		}

		/**
		 * HealingShark y is on the bottom row.
		 * 
		 * @param boardSize TODO
		 */
		@Override
		public int yCoordinate(final int boardSize) {
			return boardSize - 1;
		}
	};

	/**
	 * Casting from String to Enum PieceType.
	 * 
	 * @param string The input string
	 * @return The Enum version of the String referred to.
	 */
	public static PieceType parsePieceType(final String string) {
		PieceType pieceType = null;
		if (string.equalsIgnoreCase(AGGRESSIVESHARK.toString())) {
			pieceType = PieceType.AGGRESSIVESHARK;
		} else if (string.equalsIgnoreCase(ATTACKINGEAGLE.toString())) {
			pieceType = PieceType.ATTACKINGEAGLE;
		} else if (string.equalsIgnoreCase(DEFENSIVESHARK.toString())) {
			pieceType = PieceType.DEFENSIVESHARK;
		} else if (string.equalsIgnoreCase(HEALINGSHARK.toString())) {
			pieceType = PieceType.HEALINGSHARK;
		} else if (string.equalsIgnoreCase(LEADERSHIPEAGLE.toString())) {
			pieceType = PieceType.LEADERSHIPEAGLE;
		} else if (string.equalsIgnoreCase(VISIONARYEAGLE.toString())) {
			pieceType = PieceType.VISIONARYEAGLE;
		}
		return pieceType;
	}

	/**
	 * 
	 */
	private final File file;

	/**
	 * 
	 */
	private final TeamType teamType;

	/**
	 * @param file     Relative file path.
	 * @param teamType TeamType of the Piece Type.
	 */
	PieceType(final File file, final TeamType teamType) {
		this.file = file;
		this.teamType = teamType;
	}

	/**
	 * @return The file provided by relative Path of the piece type.
	 */
	public File file() {
		return file;
	}

	/**
	 * @return The team type that the piece type is belonged to.
	 */
	public TeamType team() {
		return teamType;
	}

	/**
	 * @return The initial coordinate x
	 */
	public abstract int xCoordinate(int boardSize);

	/**
	 * @return The initial coordinate y
	 */
	public abstract int yCoordinate(int boardSize);
}
