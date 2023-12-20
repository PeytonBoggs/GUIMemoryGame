package edu.wm.cs.cs301.guimemorygame.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import edu.wm.cs.cs301.guimemorygame.model.MemoryGameModel;
import edu.wm.cs.cs301.guimemorygame.view.MemoryGameBoard;

public class NewGameAction extends AbstractAction {
	
private static final long serialVersionUID = 1L;
	
	private final MemoryGameBoard board;
	
	private final MemoryGameModel model;
	
	public NewGameAction(MemoryGameBoard board, MemoryGameModel model) {
		this.board = board;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand() == "Play Again") {
			new MemoryGameBoard(new MemoryGameModel(model.getDifficulty()));
			board.shutdownFrame();
		} else if (event.getActionCommand() == "Easy") {
			new MemoryGameBoard(new MemoryGameModel("Easy"));
			board.shutdownFrame();
		} else if (event.getActionCommand() == "Medium") {
			new MemoryGameBoard(new MemoryGameModel("Medium"));
			board.shutdownFrame();
		} else if (event.getActionCommand() == "Hard") {
			new MemoryGameBoard(new MemoryGameModel("Hard"));
			board.shutdownFrame();
		} else {
			board.shutdown();
		}
	}

}
