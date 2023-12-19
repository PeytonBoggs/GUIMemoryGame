package edu.wm.cs.cs301.guimemorygame.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import edu.wm.cs.cs301.guimemorygame.view.CharacterGamePiece;

public class MemoryGameModel {
	
	private int rows;
	private int cols;
	private int turn;
	private List<String> characters;
	
	private CharacterGamePiece firstGuess;
	private CharacterGamePiece secondGuess;
	private boolean freeze;
	
	public MemoryGameModel() {
		this.rows = 3;
		this.cols = 4;
		this.turn = 1;
		this.characters = generateCharacters();
		this.firstGuess = null;
		this.secondGuess = null;
		this.freeze = false;
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
				firstGuess = null;
				secondGuess = null;
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
	
	private void waitTwoSeconds() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
