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
		public int x() {
			return boardSize / 2 - 1;
		}

		/**
		 * Attacking Eagle y is on the top row.
		 */
		@Override
		public int y() {
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
		public int x() {
			return boardSize / 2;
		}

		/**
		 * Leadership Eagle y is 1 cell below the top row.
		 */
		@Override
		public int y() {
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
		public int x() {
			return boardSize / 2 + 1;
		}

		/**
		 * Visionary Eagle y is on the top row.
		 */
		@Override
		public int y() {
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
		public int x() {
			return boardSize / 2 - 1;
		}

		/**
		 * Aggressive Shark y is on the bottom row.
		 */
		@Override
		public int y() {
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
		public int x() {
			return boardSize / 2;
		}

		/**
		 * Defensive Shark y is 1 cell above the bottom row.
		 */
		@Override
		public int y() {
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
		public int x() {
			return boardSize / 2 + 1;
		}

		/**
		 * HealingShark y is on the bottom row.
		 */
		@Override
		public int y() {
			return boardSize - 1;
		}
	};

	private static int boardSize;
	/**
	 * Used for calculating the initial (x,y) of a piece.
	 * 
	 * @param size The current Size of the board that the piece is located.
	 */
	public static void onBoardSize(int size) {
		boardSize = size;
	}
	/**
	 * Casting from String to Enum PieceType.
	 * 
	 * @param s The input string
	 * @return The Enum version of the String refered to.
	 */
	public static PieceType parsePieceType(String s) {
		if (s.equalsIgnoreCase(PieceType.AGGRESSIVESHARK.toString())) {
			return PieceType.AGGRESSIVESHARK;
		} else if (s.equalsIgnoreCase(PieceType.ATTACKINGEAGLE.toString())) {
			return PieceType.ATTACKINGEAGLE;
		} else if (s.equalsIgnoreCase(PieceType.DEFENSIVESHARK.toString())) {
			return PieceType.DEFENSIVESHARK;
		} else if (s.equalsIgnoreCase(PieceType.HEALINGSHARK.toString())) {
			return PieceType.HEALINGSHARK;
		} else if (s.equalsIgnoreCase(PieceType.LEADERSHIPEAGLE.toString())) {
			return PieceType.LEADERSHIPEAGLE;
		} else if (s.equalsIgnoreCase(PieceType.VISIONARYEAGLE.toString())) {
			return PieceType.VISIONARYEAGLE;
		} else {
			return null;
		}
	}

	private final File file;

	private final TeamType teamType;

	/**
	 * @param file     Relative file path.
	 * @param teamType TeamType of the Piece Type.
	 */
	PieceType(File file, TeamType teamType) {
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
	public abstract int x();

	/**
	 * @return The inituial coordinate y
	 */
	public abstract int y();
}
