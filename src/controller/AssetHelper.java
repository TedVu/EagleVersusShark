package controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import models.enumeration.Player;

/**
 * @author Ted
 * @implNote Pure Fabrication class decouples relevant data of two team with
 *           internal system
 * @see MovePieceController.java
 */
public class AssetHelper {
	public static Map<String, String> fileName = new HashMap<String, String>();
	public static final Player sharkTeamName = Player.SHARK;
	public static final Player eagleTeamName = Player.EAGLE;

	public static final String attackingEagle = "AttackingEagle";
	public static final String leadershipEagle = "LeadershipEagle";
	public static final String visionaryEagle = "VisionaryEagle";

	public static final String aggressiveShark = "AggressiveShark";
	public static final String healingShark = "HealingShark";
	public static final String defensiveShark = "DefensiveShark";

	public static final Set<String> eagleNames = new HashSet<String>();
	public static final Set<String> sharkNames = new HashSet<String>();

	public static final Map<String, Integer> initialPosAttackingEagle = new HashMap<String, Integer>();
	public static final Map<String, Integer> initialPosLeadershipEagle = new HashMap<String, Integer>();
	public static final Map<String, Integer> initialPosVisionaryEagle = new HashMap<String, Integer>();

	public static final Map<String, Integer> initialPosAggressiveShark = new HashMap<String, Integer>();
	public static final Map<String, Integer> initialPosDefensiveShark = new HashMap<String, Integer>();
	public static final Map<String, Integer> initialPosHealingShark = new HashMap<String, Integer>();

	public static void populate() {
		initialPosAttackingEagle.put("x", 3);
		initialPosAttackingEagle.put("y", 0);

		initialPosLeadershipEagle.put("x", 4);
		initialPosLeadershipEagle.put("y", 1);

		initialPosVisionaryEagle.put("x", 5);
		initialPosVisionaryEagle.put("y", 0);

		initialPosAggressiveShark.put("x", 3);
		initialPosAggressiveShark.put("y", 8);

		initialPosDefensiveShark.put("x", 4);
		initialPosDefensiveShark.put("y", 7);

		initialPosHealingShark.put("x", 5);
		initialPosHealingShark.put("y", 8);

		fileName.put("AttackingEagle", "/asset/AttackingEagle.png");
		fileName.put("LeadershipEagle", "/asset/LeadershipEagle.png");
		fileName.put("VisionaryEagle", "/asset/VisionaryEagle.png");
		fileName.put("AggressiveShark", "/asset/AggressiveShark.png");
		fileName.put("HealingShark", "/asset/HealingShark.png");
		fileName.put("DefensiveShark", "/asset/DefensiveShark.png");

		eagleNames.add("attackingeagle");
		eagleNames.add("leadershipeagle");
		eagleNames.add("visionaryeagle");

		sharkNames.add("aggressiveshark");
		sharkNames.add("defensiveshark");
		sharkNames.add("healingshark");
	}

	private AssetHelper() {

	}
}
