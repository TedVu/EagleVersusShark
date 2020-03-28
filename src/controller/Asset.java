package controller;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ted
 * @implNote Pure Fabrication class decouples files with internal system
 * @see MovePieceController.java
 */
public class Asset {
	public static Map<String, String> fileName = new HashMap<String, String>();
	public static final String sharkTeamName = "sharkplayer";
	public static final String eagleTeamName = "eagleplayer";
	
	public static final String attackingEagle = "AttackingEagle";
	public static final String leadershipEagle = "LeadershipEagle";
	public static final String visionaryEagle = "VisionaryEagle";
	
	public static final String aggressiveShark = "AggressiveShark";
	public static final String healingShark = "HealingShark";
	public static final String defensiveShark = "DefensiveShark";

	public static void populate() {
		fileName.put("AttackingEagle", "/asset/AttackingEagle.png");
		fileName.put("LeadershipEagle", "/asset/LeadershipEagle.png");
		fileName.put("VisionaryEagle", "/asset/VisionaryEagle.png");
		fileName.put("AggressiveShark", "/asset/AggressiveShark.png");
		fileName.put("HealingShark", "/asset/HealingShark.png");
		fileName.put("DefensiveShark", "/asset/DefensiveShark.png");
	}

	private Asset() {

	}
}
