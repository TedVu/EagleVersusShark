package asset;

public enum PieceType {
	ATTACKINGEAGLE {

		@Override
		public String getFileName() {
			// TODO Auto-generated method stub
			return "/asset/AttackingEagle.png";
		}

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

	},

	LEADERSHIPEAGLE {

		@Override
		public String getFileName() {
			// TODO Auto-generated method stub
			return "/asset/LeadershipEagle.png";
		}

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

	},

	VISIONARYEAGLE {

		@Override
		public String getFileName() {
			// TODO Auto-generated method stub
			return "/asset/VisionaryEagle.png";
		}

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

	},

	AGGRESSIVESHARK {

		@Override
		public String getFileName() {
			// TODO Auto-generated method stub
			return "/asset/AggressiveShark.png";
		}

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

	},

	DEFENSIVESHARK {

		@Override
		public String getFileName() {
			// TODO Auto-generated method stub
			return "/asset/DefensiveShark.png";
		}

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

	},

	HEALINGSHARK {

		@Override
		public String getFileName() {
			// TODO Auto-generated method stub
			return "/asset/HealingShark.png";
		}

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
