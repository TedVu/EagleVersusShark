package views.main;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import models.engine.Engine;
import models.engine.EngineImpl;
import models.pieces.Piece;

public class MockClient {

	public static void main(String[] args) {

		Engine engine = EngineImpl.getSingletonInstance();
		Map<UUID, Piece> pieces = new HashMap<UUID, Piece>();

		pieces = engine.getAllPieces();

		for (Piece piece : pieces.values()) {

			System.out.println("piece class type : " + piece.getClass());
			System.out.println("position : " + piece.getPosition());
			System.out.println("after move : " + piece.move(1, 2));

			System.out.println();
		}

	}

}
