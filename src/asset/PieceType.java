package asset;

public enum PieceType {
	ATTACKINGEAGLE {

		@Override
		public int getInitialPosX() {
			// TODO Auto-generated method stub
			return 3;
		}

		@Override
		public int getInitialPosY() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "AttackingEagle";
		}

		@Override
		public String getFileName() {
			// TODO Auto-generated method stub
			return "/asset/AttackingEagle.png";
		}

	},

	LEADERSHIPEAGLE {

		@Override
		public int getInitialPosX() {
			// TODO Auto-generated method stub
			return 4;
		}

		@Override
		public int getInitialPosY() {
			// TODO Auto-generated method stub
			return 1;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "LeadershipEagle";
		}

		@Override
		public String getFileName() {
			// TODO Auto-generated method stub
			return "/asset/LeadershipEagle.png";
		}

	},

	VISIONARYEAGLE {

		@Override
		public int getInitialPosX() {
			// TODO Auto-generated method stub
			return 5;
		}

		@Override
		public int getInitialPosY() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "VisionaryEagle";
		}

		@Override
		public String getFileName() {
			// TODO Auto-generated method stub
			return "/asset/VisionaryEagle.png";
		}

	},

	AGGRESSIVESHARK {

		@Override
		public int getInitialPosX() {
			// TODO Auto-generated method stub
			return 3;
		}

		@Override
		public int getInitialPosY() {
			// TODO Auto-generated method stub
			return 8;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "AggressiveShark";
		}

		@Override
		public String getFileName() {
			// TODO Auto-generated method stub
			return "/asset/AggressiveShark.png";
		}

	},

	DEFENSIVESHARK {

		@Override
		public int getInitialPosX() {
			// TODO Auto-generated method stub
			return 4;
		}

		@Override
		public int getInitialPosY() {
			// TODO Auto-generated method stub
			return 7;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "DefensiveShark";
		}

		@Override
		public String getFileName() {
			// TODO Auto-generated method stub
			return "/asset/DefensiveShark.png";
		}

	},

	HEALINGSHARK {

		@Override
		public int getInitialPosX() {
			// TODO Auto-generated method stub
			return 5;
		}

		@Override
		public int getInitialPosY() {
			// TODO Auto-generated method stub
			return 8;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "HealingShark";
		}

		@Override
		public String getFileName() {
			// TODO Auto-generated method stub
			return "/asset/HealingShark.png";
		}

	};
	public abstract int getInitialPosX();

	public abstract int getInitialPosY();

	public abstract String toString();

	public abstract String getFileName();

	private static String eagleTeamName = "eagleplayer";

	private static String sharkTeamName = "sharkplayer";

	public static String getEagleTeamName() {
		return eagleTeamName;
	}

	public static String getSharkTeamName() {
		return sharkTeamName;
	}

}
