package asset;

public enum PieceType {
	ATTACKINGEAGLE {
		@Override
		public String getFileName() {
			return "/asset/AttackingEagle.png";
		}

		@Override
		public int getInitialPosX() {
			return 3;
		}

		@Override
		public int getInitialPosY() {
			return 0;
		}

		@Override
		public String toString() {
			return "AttackingEagle";
		}
	},

	LEADERSHIPEAGLE {
		@Override
		public String getFileName() {
			return "/asset/LeadershipEagle.png";
		}

		@Override
		public int getInitialPosX() {
			return 4;
		}

		@Override
		public int getInitialPosY() {
			return 1;
		}

		@Override
		public String toString() {
			return "LeadershipEagle";
		}
	},

	VISIONARYEAGLE {
		@Override
		public String getFileName() {
			return "/asset/VisionaryEagle.png";
		}

		@Override
		public int getInitialPosX() {
			return 5;
		}

		@Override
		public int getInitialPosY() {
			return 0;
		}

		@Override
		public String toString() {
			return "VisionaryEagle";
		}
	},

	AGGRESSIVESHARK {
		@Override
		public String getFileName() {
			return "/asset/AggressiveShark.png";
		}

		@Override
		public int getInitialPosX() {
			return 3;
		}

		@Override
		public int getInitialPosY() {
			return 8;
		}

		@Override
		public String toString() {
			return "AggressiveShark";
		}

	},

	DEFENSIVESHARK {
		@Override
		public String getFileName() {
			return "/asset/DefensiveShark.png";
		}

		@Override
		public int getInitialPosX() {
			return 4;
		}

		@Override
		public int getInitialPosY() {
			return 7;
		}

		@Override
		public String toString() {
			return "DefensiveShark";
		}
	},

	HEALINGSHARK {
		@Override
		public String getFileName() {
			return "/asset/HealingShark.png";
		}

		@Override
		public int getInitialPosX() {
			return 5;
		}

		@Override
		public int getInitialPosY() {
			return 8;
		}

		@Override
		public String toString() {
			return "HealingShark";
		}
	};

	private static String eagleTeamName = "eagleplayer";

	private static String sharkTeamName = "sharkplayer";

	public static String getEagleTeamName() {
		return eagleTeamName;
	}

	public static String getSharkTeamName() {
		return sharkTeamName;
	}

	public abstract String getFileName();

	public abstract int getInitialPosX();

	public abstract int getInitialPosY();

	public abstract String toString();
}
