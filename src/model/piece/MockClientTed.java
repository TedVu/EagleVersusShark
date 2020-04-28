package model.piece;

import java.util.HashMap;
import java.util.Map;

import model.contract.EngineInterface;
import model.contract.PieceInterface;
import model.engine.EngineImpl;
import model.enumtype.PieceType;
import model.enumtype.TeamType;
import model.piece.commands.CommandExecutor;
import model.piece.commands.MovePiece;
import model.piece.commands.Undo;

public class MockClientTed {
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
		System.out.println("attack eagle loc: " + attackerPiece.getPosition());
		System.out.println("vision eagle loc: " + visionPiece.getPosition());
		System.out.println();

		System.out.println("MOVE 1");
		commandExecutor.executeCommand(new MovePiece(2, 0, attackerPiece));
		System.out.println("attack eagle loc: " + attackerPiece.getPosition());
		System.out.println();

		System.out.println("MOVE 2");
		System.out.println("defence shark loc: " + defenceShark.getPosition());

		commandExecutor.executeCommand(new MovePiece(4, 6, defenceShark));
		System.out.println("defence shark loc: " + defenceShark.getPosition());
		System.out.println();
	

		
		System.out.println("UNDO");
		commandExecutor.executeCommand(new Undo(TeamType.EAGLE));
		
		System.out.println("attack eagle loc: " + attackerPiece.getPosition());
	

	}
}
