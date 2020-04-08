package views.main;

import java.util.HashMap;
import java.util.Map;


import models.engine.Engine;
import models.engine.EngineImpl;
import models.pieces.AttackerEagle;
import models.pieces.LeadershipEagle;
import models.pieces.Piece;
import models.pieces.VisionaryEagle;
import models.pieces.commands.CommandExecutor;
import models.pieces.commands.MovePieceCommand;
import models.pieces.commands.UndoCommand;
import models.pieces.commands.UseAbilityCommand;

public class MockClient2 {

	public static void main(String[] args) {

		Engine engine = EngineImpl.getSingletonInstance();
		Map<String, Piece> pieces = new HashMap<String, Piece>();
		engine.pieceOperator().initializePiece();
		CommandExecutor commandExecutor = new CommandExecutor();
		

		pieces = engine.pieceOperator().getAllPieces();
		// System.out.println(engine.getInitialPlayerActivePlayer().getPlayerType());

		Piece attackerPiece = null;
		Piece visionPiece = null;
		Piece leaderPiece = null;

		for (Piece piece : pieces.values()) {

			if(piece instanceof AttackerEagle) {
				attackerPiece = piece;
				
			}
			if(piece instanceof VisionaryEagle) {
				visionPiece = piece;
			}
			if(piece instanceof LeadershipEagle) {
				leaderPiece = piece;
			}

		}
		
		System.out.println("try undoing empty stack");
		try {
			commandExecutor.executeOperation(new UndoCommand(engine.pieceOperator()));
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		System.out.println("piece a loc: " + attackerPiece.getPosition());
		System.out.println("piece v loc: " + visionPiece.getPosition());
		
		System.out.println("swapping a and v location");
		
		commandExecutor.executeOperation(new UseAbilityCommand
				("swap", visionPiece, attackerPiece, engine.pieceOperator()));
		
		System.out.println("piece a loc: " + attackerPiece.getPosition());
		System.out.println("piece v loc: " + visionPiece.getPosition());
		
		System.out.println("moving a to 2,0");
		commandExecutor.executeOperation(new MovePieceCommand
				(engine.pieceOperator(), 2, 0, attackerPiece));
		
		System.out.println("new piece a loc: " + attackerPiece.getPosition());
		System.out.println("undoing command");
		commandExecutor.executeOperation(new UndoCommand(engine.pieceOperator()));
		System.out.println("piece a loc: " + attackerPiece.getPosition());
		// engine.setActivePlayer("eagle", true);
		// System.out.println(engine.getActiveEagles());
		//
		// System.out.println(engine.useAbility("swap", visionPiece, attackerPiece));
		//
		// System.out.println(visionPiece.getPosition());
		// System.out.println(attackerPiece.getPosition());

		// System.out.println(engine.useAbility("protect", leaderPiece, attackerPiece));
		//
		// System.out.println(attackerPiece.isImmune());

//		System.out.println(engine.useAbility("capture", attackerPiece, visionPiece));
		
		
	}
	
}
