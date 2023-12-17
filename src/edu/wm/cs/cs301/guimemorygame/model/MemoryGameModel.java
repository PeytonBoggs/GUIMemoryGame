package edu.wm.cs.cs301.guimemorygame.model;

public class MemoryGameModel {
	
	private int rows;
	
	private int cols;
	
	public MemoryGameModel() {
		this.rows = 3;
		this.cols = 4;
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}
}
