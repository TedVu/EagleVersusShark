package asset;

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
	ATTACKINGEAGLE(new File("src\\asset\\AttackingEagle.png"), TeamType.EAGLE) {
		/**
		 * Attacking Eagle x is 1 cell to the left of middle column.
		 */
		@Override
		public int xCoordinate() {
			return boardSize / 2 - 1;
		}

		/**
		 * Attacking Eagle y is on the top row.
		 */
		@Override
		public int yCoordinate() {
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
	LEADERSHIPEAGLE(new File("src\\asset\\LeadershipEagle.png"), TeamType.EAGLE) {
		/**
		 * Leadership Eagle x is in the middle column.
		 */
		@Override
		public int xCoordinate() {
			return boardSize / 2;
		}

		/**
		 * Leadership Eagle y is 1 cell below the top row.
		 */
		@Override
		public int yCoordinate() {
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
	VISIONARYEAGLE(new File("src\\asset\\VisionaryEagle.png"), TeamType.EAGLE) {
		/**
		 * Visionary Eagle x is 1 cell to the right of the middle column.
		 */
		@Override
		public int xCoordinate() {
			return boardSize / 2 + 1;
		}

		/**
		 * Visionary Eagle y is on the top row.
		 */
		@Override
		public int yCoordinate() {
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
	AGGRESSIVESHARK(new File("src\\asset\\AggressiveShark.png"), TeamType.SHARK) {
		/**
		 * Aggressive Shark x is 1 cell to the left of the middle column.
		 */
		@Override
		public int xCoordinate() {
			return boardSize / 2 - 1;
		}

		/**
		 * Aggressive Shark y is on the bottom row.
		 */
		@Override
		public int yCoordinate() {
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
	DEFENSIVESHARK(new File("src\\asset\\DefensiveShark.png"), TeamType.SHARK) {
		/**
		 * Defensive Shark x is in the middle column.
		 */
		@Override
		public int xCoordinate() {
			return boardSize / 2;
		}

		/**
		 * Defensive Shark y is 1 cell above the bottom row.
		 */
		@Override
		public int yCoordinate() {
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
	HEALINGSHARK(new File("src\\asset\\HealingShark.png"), TeamType.SHARK) {
		/**
		 * Healing Shark x is 1 cell to the right of the middle column.
		 */
		@Override
		public int xCoordinate() {
			return boardSize / 2 + 1;
		}

		/**
		 * HealingShark y is on the bottom row.
		 */
		@Override
		public int yCoordinate() {
			return boardSize - 1;
		}
	};

	private static int boardSize;

	/**
	 * Used for calculating the initial (x,y) of a piece.
	 * 
	 * @param size The current Size of the board that the piece is located.
	 */
	public static void onBoardSize(final int size) {
		boardSize = size;
	}

	/**
	 * Casting from String to Enum PieceType.
	 * 
	 * @param string The input string
	 * @return The Enum version of the String refered to.
	 */
	public static PieceType parsePieceType(final String string) {
		PieceType pieceType = null;
		if (string.equalsIgnoreCase(PieceType.AGGRESSIVESHARK.toString())) {
			pieceType = PieceType.AGGRESSIVESHARK;
		} else if (string.equalsIgnoreCase(PieceType.ATTACKINGEAGLE.toString())) {
			pieceType = PieceType.ATTACKINGEAGLE;
		} else if (string.equalsIgnoreCase(PieceType.DEFENSIVESHARK.toString())) {
			pieceType = PieceType.DEFENSIVESHARK;
		} else if (string.equalsIgnoreCase(PieceType.HEALINGSHARK.toString())) {
			pieceType = PieceType.HEALINGSHARK;
		} else if (string.equalsIgnoreCase(PieceType.LEADERSHIPEAGLE.toString())) {
			pieceType = PieceType.LEADERSHIPEAGLE;
		} else if (string.equalsIgnoreCase(PieceType.VISIONARYEAGLE.toString())) {
			pieceType = PieceType.VISIONARYEAGLE;
		}
		return pieceType;
	}

	private final File file; // NOPMD by kevin on 4/14/20 10:19 PM

	private final TeamType teamType; // NOPMD by kevin on 4/14/20 10:20 PM

	/**
	 * @param file     Relative file path.
	 * @param teamType TeamType of the Piece Type.
	 */
	PieceType(final File file,final TeamType teamType) {
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
	public abstract int xCoordinate();

	/**
	 * @return The inituial coordinate y
	 */
	public abstract int yCoordinate();
}
