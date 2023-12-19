package edu.wm.cs.cs301.guimemorygame.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;

import edu.wm.cs.cs301.guimemorygame.model.MemoryGameModel;
import edu.wm.cs.cs301.guimemorygame.view.MemoryGameBoard;

public class NewGameAction extends AbstractAction {
	
private static final long serialVersionUID = 1L;
	
	private final MemoryGameBoard board;
	
	public NewGameAction(MemoryGameBoard board, MemoryGameModel model) {
		this.board = board;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JButton button = (JButton) event.getSource();

		if (button.getActionCommand() == "Play Again") {
			new MemoryGameBoard(new MemoryGameModel());
		}
		board.shutdownFrame();
	}

}
