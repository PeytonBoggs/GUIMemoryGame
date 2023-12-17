package edu.wm.cs.cs301.guimemorygame.model;

import edu.wm.cs.cs301.guimemorygame.view.CharacterGamePiece;

public class MemoryGameModel {
	
	private int rows;
	private int cols;
	private CharacterGamePiece[][] pieces;
	
	public MemoryGameModel() {
		this.rows = 3;
		this.cols = 4;
		this.pieces = generatePieces();
	}
	
	private CharacterGamePiece[][] generatePieces() {
		CharacterGamePiece[][] board = new CharacterGamePiece[this.rows][this.cols];
		
		char[] symbols = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 
		'b'};
		
		int symbolIndex = 0;
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				CharacterGamePiece temp = new CharacterGamePiece(symbols[symbolIndex]);
				symbolIndex++;
				board[i][j] = temp;
			}
		}
		
		return board;
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}
	
	public CharacterGamePiece[][] getPieces() {
		return pieces;
	}
}
