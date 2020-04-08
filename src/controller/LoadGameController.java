package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JFrame;

import models.engine.EngineImpl;
import models.pieces.Piece;
import models.pieces.PieceFactory;
import view.configuration.LoadGameDialog;
import view.mainframe.MainAppFrame;
import view.messagedialog.MessageDialog;

public class LoadGameController implements ActionListener {

	private JFrame startFrame;
	private LoadGameDialog loadGameDialog;
	private BufferedReader br;

	public LoadGameController(JFrame startFrame, LoadGameDialog loadGameDialog) {
		this.startFrame = startFrame;
		this.loadGameDialog = loadGameDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// perform loading game state here
		String fileRequest = loadGameDialog.getFileNameInput();
		if (fileRequest.endsWith(".status")) {
			File file = new File(fileRequest);
			if (!file.exists()) {
				MessageDialog.notifyInputFileNotExist(startFrame);
			} else {
				// start processing file here
				try {
					StringTokenizer stToken;
					br = new BufferedReader(new FileReader(fileRequest));
					stToken = new StringTokenizer(br.readLine());
					stToken.nextToken();
					int boardSize = Integer.parseInt(stToken.nextToken());

					stToken = new StringTokenizer(br.readLine());
					stToken.nextToken();
					int numPiece = Integer.parseInt(stToken.nextToken());

					List<Piece> pieces = new ArrayList<Piece>();
					PieceFactory pieceFactory = new PieceFactory();

					for (int i = 1; i <= numPiece; ++i) {
						stToken = new StringTokenizer(br.readLine());
						String pieceName = stToken.nextToken();
						int x = Integer.parseInt(stToken.nextToken());
						int y = Integer.parseInt(stToken.nextToken());
						Piece piece = pieceFactory.generatePiece(pieceName, x, y);
						pieces.add(piece);
					}

					stToken = new StringTokenizer(br.readLine());
					stToken.nextToken();
					String currentTurn = stToken.nextToken();

					EngineImpl.getSingletonInstance().loadBoard(boardSize);
					EngineImpl.getSingletonInstance().loadPiece(pieces);
					EngineImpl.getSingletonInstance().loadTurn(currentTurn);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				startFrame.setVisible(false);
				loadGameDialog.setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							MainAppFrame window = new MainAppFrame();
							window.getBoardPanel().loadGame();
							window.setVisible(true);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			}
		} else {
			MessageDialog.notifyInputWrongFileFormat(startFrame);
		}

	}

}
