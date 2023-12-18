package edu.wm.cs.cs301.guimemorygame.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import edu.wm.cs.cs301.guimemorygame.model.MemoryGameModel;
import edu.wm.cs.cs301.guimemorygame.view.CharacterGamePiece;
import edu.wm.cs.cs301.guimemorygame.view.MemoryGameBoard;

public class KeyboardButtonAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private final MemoryGameBoard board;
	
	private final MemoryGameModel model;

	public KeyboardButtonAction(MemoryGameBoard board, MemoryGameModel model) {
		this.board = board;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		CharacterGamePiece piece = (CharacterGamePiece) event.getSource();
		
		if (piece.getSymbol() != "?") {
			board.setInstructionLabel("You cannot flip a revealed card!");
		} else {
			piece.setRevealed(!piece.isRevealed());	
			piece.setText(piece.getSymbol());
			
			board.setTurnLabel(model.getTurn());
			board.setInstructionLabel(model.handleClick(piece));
		}
	}
}
