package edu.wm.cs.cs301.guimemorygame.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryGameModel {
	
	private int rows;
	private int cols;
	private List<String> characters;
	
	public MemoryGameModel() {
		this.rows = 3;
		this.cols = 4;
		this.characters = generateCharacters();
	}
	
	private List<String> generateCharacters() {		
		String[] tempSymbols = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b"};
		
		String[] alphabet = new String[6];
		System.arraycopy(tempSymbols, 0, alphabet, 0, 6);
		
		List<String> symbolList = new ArrayList<String>();
		for (String c : alphabet) {
			symbolList.add(c);
		}
		
		symbolList.addAll(symbolList);
		Collections.shuffle(symbolList);

		return symbolList;
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}
	
	public List<String> getCharacters() {
		return characters;
	}
}
