package views.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.engine.Engine;
import models.engine.EngineImpl;
import models.pieces.*;

public class MockClient {

	public static void main(String[] args) {

		Engine engine = EngineImpl.getSingletonInstance();
		Map<String, Piece> pieces = new HashMap<String, Piece>();
		engine.seedData();

		pieces = engine.getAllPiecesTed();
//		System.out.println(engine.getInitialActivePlayer().getPlayerType());
		
		Piece attackerPiece = null;
		Piece visionPiece = null;
		Piece leaderPiece = null;

		//TESTING SHARKs
		Piece attackShark = null;
		Piece healShark = null;
		Piece defenseShark = null;

		for (Piece piece : pieces.values()) {

//			System.out.println("piece class type : " + piece.getClass());
//			System.out.println("position : " + piece.getPosition());
////			System.out.println("after move : " + piece.moveP(1,2));

//			System.out.println(engine.setPieceActiveStatus(piece.getId(), false));

			if (piece instanceof VisionaryEagle) {
				System.out.println("after move vision : " + engine.movePiece(piece, 2, 2));
				visionPiece = piece;
			}
			if (piece instanceof LeadershipEagle) {
				System.out.println("after move leader: " + engine.movePiece(piece, 2, 5));
				leaderPiece = piece;
			}
			if (piece instanceof AttackerEagle) {
				System.out.println("after move attacker: " + engine.movePiece(piece, 2, 3));
				attackerPiece = piece;
			}


			if (piece instanceof AggressiveShark) {
				System.out.println("after move aggressive shark: " + engine.movePiece(piece, 3, 2));
				attackShark = piece;
			}
			if (piece instanceof HealingShark) {
//				System.out.println("after move healing shark: " + engine.movePiece(piece, 3, 3));
				healShark = piece;
			}
			if (piece instanceof DefensiveShark) {
//				System.out.println("after move defense shark: " + engine.movePiece(piece, 5, 2));
				defenseShark = piece;
			}
		}
		engine.getInitiaActivePlayer();
		engine.setTimerStatus(true);
		engine.setActivePlayer("eagle");
		
		
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		            	System.out.println("stop timer");
		            	engine.setTimerStatus(false);
		            }
		        }, 
		        20000 
		);
//		System.out.println(engine.getActiveEagles());
//		
//		System.out.println(engine.useAbility("swap", visionPiece, attackerPiece));
//		
//		System.out.println(visionPiece.getPosition());
//		System.out.println(attackerPiece.getPosition());
		
//		System.out.println(engine.useAbility("protect", leaderPiece, attackerPiece));
//		
//		System.out.println(attackerPiece.isImmune());
		
		System.out.println(engine.useAbility("capture", attackerPiece, visionPiece));

		//TEST: IF you uncomment line 59 & 63, line below should return false
		//		BECAUSE the healShark is too far away from the defenseShark
		//		AND the positions of the two sharks don't qualify for the healing ability to work
		System.out.println(engine.useAbility("HEAL SHARK",healShark,defenseShark));

	}

}
