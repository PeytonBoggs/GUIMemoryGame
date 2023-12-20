package edu.wm.cs.cs301.guimemorygame.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import edu.wm.cs.cs301.guimemorygame.view.CharacterGamePiece;

public class MemoryGameModel {
	
	private String difficulty;
	private int rows;
	private int cols;
	private int turn;
	private int matches;
	private List<String> characters;
	
	private CharacterGamePiece firstGuess;
	private CharacterGamePiece secondGuess;
	private boolean freeze;
	
	public MemoryGameModel(String difficulty) {
		this.difficulty = difficulty;
		initializeRowsCols(difficulty);
		this.turn = 1;
		this.matches = 0;
		this.characters = generateCharacters();
		this.firstGuess = null;
		this.secondGuess = null;
		this.freeze = false;
	}
	
	private void initializeRowsCols(String difficulty) {
		if (difficulty == "easy") {
			this.rows = 3;
			this.cols = 4;
		} else if (difficulty == "medium") { 
			this.rows = 4;
			this.cols = 7;
		} else {
			this.rows = 7;
			this.cols = 8;
		}
	}
	
	private int getNumPairs() {
		return (rows * cols) / 2;
	}
	
	private List<String> generateCharacters() {		
		String[] tempSymbols = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b"};
		
		String[] alphabet = new String[getNumPairs()];
		System.arraycopy(tempSymbols, 0, alphabet, 0, getNumPairs());
		
		List<String> symbolList = new ArrayList<String>();
		for (String c : alphabet) {
			symbolList.add(c);
		}
		
		symbolList.addAll(symbolList);
		Collections.shuffle(symbolList);

		return symbolList;
	}
	
	public String handleGuess(CharacterGamePiece piece) {		
		String instruction = "";
		
		if (firstGuess == null) {
			firstGuess = piece;
			return "";
		} else {
			secondGuess = piece;
			
			if (firstGuess.equals(secondGuess)) {
				instruction = "Match!";
				turn++;
				matches++;
				firstGuess = null;
				secondGuess = null;
				if (checkWon()) {
					instruction = "Game won in " + turn + " turns!";
					freeze = true;
				}
			} else {
				instruction = "No match";
				turn++;
				freeze = true;
				
				ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
				
				Runnable flipCardsBack = () -> {
					firstGuess.setRevealed(!firstGuess.isRevealed());	
					firstGuess.setText(firstGuess.getSymbol());
					secondGuess.setRevealed(!secondGuess.isRevealed());	
					secondGuess.setText(secondGuess.getSymbol());
					firstGuess = null;
					secondGuess = null;
					freeze = false;
				};
				
				ses.schedule(flipCardsBack, 2, TimeUnit.SECONDS);
			}
		}
		
		return instruction;
	}
	
	private boolean checkWon() {
		if (matches == getNumPairs()) {
			return true;
		}
		return false;
	}
	
	public String getDifficulty() {
		return difficulty;
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}
	
	public int getTurn() {
		return turn;
	}
	
	public List<String> getCharacters() {
		return characters;
	}
	
	public boolean getFreeze() {
		return freeze;
	}
}
