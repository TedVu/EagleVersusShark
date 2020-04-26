package model.piece;

import java.util.HashMap;
import java.util.Map;

import model.contract.EngineInterface;
import model.engine.*;
import model.contract.PieceInterface;
import model.enumtype.PieceAbility;
import model.enumtype.PieceType;
import model.enumtype.TeamType;
import model.piece.commands.CommandExecutor;
import model.piece.commands.MovePiece;
import model.piece.commands.Undo;
import model.piece.commands.UseAbility;

public class MockClient2 {

	public static void main(String[] args) {

		EngineInterface engine = EngineImpl.getSingletonInstance();
		Map<PieceType, PieceInterface> pieces = new HashMap<PieceType, PieceInterface>();
		CommandExecutor commandExecutor = new CommandExecutor();
		//
		pieces = engine.pieceOperator().getAllPieces();
		// // System.out.println(engine.getInitialPlayerActivePlayer().getPlayerType());
		//
		PieceInterface attackerPiece = null;
		PieceInterface visionPiece = null;
		PieceInterface leaderPiece = null;
		PieceInterface defenceShark = null;
		//
		for (PieceInterface piece : pieces.values()) {

			if (piece instanceof AttackingEagle) {
				attackerPiece = piece;

			}
			if (piece instanceof VisionaryEagle) {
				visionPiece = piece;
			}
			if (piece instanceof LeadershipEagle) {
				leaderPiece = piece;
			}

			if (piece instanceof DefensiveShark) {
				defenceShark = piece;
			}

		}

		System.out.println("try undoing empty stack");
		try {
			commandExecutor.executeCommand(new Undo(TeamType.EAGLE));
		} catch (Exception e) {
			System.out.println(e);
		}
		//

		System.out.println("attack eagle loc: " + attackerPiece.getPosition());
		System.out.println("vision eagle loc: " + visionPiece.getPosition());
		System.out.println();
		
		// move 1
		System.out.println("moving attacking eagle to (4,0)");
		commandExecutor.executeCommand(new MovePiece(4, 0, attackerPiece));
		System.out.println("attack eagle loc: " + attackerPiece.getPosition());
		System.out.println();
//
		
		System.out.println("getting possible swapping position for visionay eagle");
		System.out.println(visionPiece.abilityCells());
		System.out.println();
		
		//move 2
		System.out.println("swapping attacking eagle & visionary position");
		commandExecutor.executeCommand(new UseAbility(PieceAbility.SWAP, visionPiece, attackerPiece));
		System.out.println("attack eagle loc: " + attackerPiece.getPosition());
		System.out.println("vision eagle loc: " + visionPiece.getPosition());
		System.out.println();
		
		//move 3
		System.out.println("visionary eagle has immunity: " + visionPiece.isImmune());
		System.out.println("leader eagle give immunity to visionary");
		commandExecutor.executeCommand(new UseAbility(PieceAbility.PROTECT, leaderPiece, visionPiece));
		System.out.println("visionary eagle has immunity: " + visionPiece.isImmune());
		System.out.println("try to give immunity again");
		try {
			commandExecutor.executeCommand(new UseAbility(PieceAbility.PROTECT, leaderPiece, visionPiece));
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println();
		
		
		System.out.println("trying to capture defence eagle that is too far");
		System.out.println("defence shark loc: " + defenceShark.getPosition());
		System.out.println("capture defence eagle");
		try {
			commandExecutor.executeCommand(new UseAbility(PieceAbility.CAPTURE, attackerPiece, defenceShark));
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("defence eagle active status: " + defenceShark.isActive());
		System.out.println();
		
		// move 4
		System.out.println("moving defence shark to be close to attack eagle");
		commandExecutor.executeCommand(new MovePiece(4, 0, defenceShark));
		System.out.println("defence shark loc: " + defenceShark.getPosition());
		System.out.println("capture defence eagle");
		//move5
		commandExecutor.executeCommand(new UseAbility(PieceAbility.CAPTURE, attackerPiece, defenceShark));
		System.out.println("defence eagle active status: " + defenceShark.isActive());
		System.out.println();
		
		System.out.println("undoing the last round1");
		commandExecutor.executeCommand(new Undo(TeamType.EAGLE));
		System.out.println("defence shark active status: " + defenceShark.isActive());
		System.out.println("defence shark loc: " + defenceShark.getPosition());
		System.out.println();
		
		
		
//		System.out.println("attack eagle has immunity: " + attackerPiece.isImmune());
//		System.out.println("leader eagle give immunity to visionary");
//		commandExecutor.executeCommand(new UseAbility(PieceAbility.PROTECT, leaderPiece, attackerPiece));
//		System.out.println("attacker eagle has immunity: " + attackerPiece.isImmune());
//		
//		System.out.println();
		
		
		System.out.println("vision eagle loc: " + visionPiece.getPosition());
		System.out.println("attack eagle loc: " + attackerPiece.getPosition());
		System.out.println("swapping attacking eagle & visionary position");
		commandExecutor.executeCommand(new UseAbility(PieceAbility.SWAP, visionPiece, attackerPiece));
		System.out.println("vision eagle loc: " + visionPiece.getPosition());
		System.out.println("attack eagle loc: " + attackerPiece.getPosition());
		System.out.println();
		
		System.out.println("attack eagle has immunity: " + attackerPiece.isImmune());
		System.out.println("leader eagle give immunity to visionary");
		commandExecutor.executeCommand(new UseAbility(PieceAbility.PROTECT, leaderPiece, attackerPiece));
		System.out.println("attacker eagle has immunity: " + attackerPiece.isImmune());
		
		System.out.println();
		

		System.out.println("undoing the last round2");
		commandExecutor.executeCommand(new Undo(TeamType.EAGLE));
		System.out.println("attacker eagle has immunity: " + attackerPiece.isImmune());
		System.out.println("vision eagle loc: " + visionPiece.getPosition());
		System.out.println("attack eagle loc: " + attackerPiece.getPosition());
		
		System.out.println();
		
	}

}
