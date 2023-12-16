package edu.wm.cs.cs301.guimemorygame.model;

public class MemoryGameModel {
	
	private int rows;
	
	private int cols;
	
	private MemoryGameResponse[][] characterGrid;
	
	public MemoryGameModel() {
		this.rows = 3;
		this.cols = 4;
		this.characterGrid = initializeCharacterGrid();
	}
	
	private MemoryGameResponse[][] initializeCharacterGrid() {
		MemoryGameResponse[][] characterGrid = new MemoryGameResponse[rows][cols];

		for (int row = 0; row < characterGrid.length; row++) {
			for (int column = 0; column < characterGrid[row].length; column++) {
				characterGrid[row][column] = null;
			}
		}

		return characterGrid;
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}
	
	public MemoryGameResponse[][] getCharacterGrid() {
		return characterGrid;
	}
}
