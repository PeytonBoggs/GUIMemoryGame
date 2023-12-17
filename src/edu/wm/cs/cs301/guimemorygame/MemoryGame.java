package edu.wm.cs.cs301.guimemorygame;

import edu.wm.cs.cs301.guimemorygame.model.MemoryGameModel;
import edu.wm.cs.cs301.guimemorygame.view.MemoryGameBoard;

public class MemoryGame {

	public static void main(String[] args) {		
		new MemoryGameBoard(new MemoryGameModel());
	}
}
