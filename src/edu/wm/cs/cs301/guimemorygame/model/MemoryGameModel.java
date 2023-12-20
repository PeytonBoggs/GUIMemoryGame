package edu.wm.cs.cs301.guimemorygame.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
		if (difficulty == "Easy") {
			this.rows = 3;
			this.cols = 4;
		} else if (difficulty == "Medium") { 
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
					try {updateLeaderboard(difficulty, turn);} catch (IOException e) {}
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
	
	public String[] getLeaderboard() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("resources/leaderboard.txt"));
		String[] toReturn = new String[6];
		
		String easy = br.readLine();
		if (easy == null || easy.equals(",") || easy.equals("")) {
			toReturn[0] = null;
			toReturn[1] = null;
		} else {
			toReturn[0] = easy.substring(0, easy.indexOf(","));
			toReturn[1] = easy.substring(easy.indexOf(",")+1, easy.length());	
		}
		
		String medium = br.readLine();
		if (medium == null || medium.equals(",") || medium.equals("")) {
			toReturn[2] = null;
			toReturn[3] = null;
		} else {
			toReturn[2] = medium.substring(0, medium.indexOf(","));
			toReturn[3] = medium.substring(medium.indexOf(",")+1, medium.length());	
		}
		
		String hard = br.readLine();
		if (hard == null || hard.equals(",") || hard.equals("")) {
			toReturn[4] = null;
			toReturn[5] = null;
		} else {
			toReturn[4] = hard.substring(0, hard.indexOf(","));
			toReturn[5] = hard.substring(hard.indexOf(",")+1, hard.length());	
		}
		
		br.close();
		return toReturn;
	}
	
	public void updateLeaderboard(String difficulty, int turn) throws IOException {	
		String stringRecord = "-1";
		String[] leaderboard = getLeaderboard();
		
		if (difficulty.equals("Easy") && leaderboard[1] != null) {
			stringRecord = leaderboard[1];
		} else if (difficulty.equals("Medium") && leaderboard[3] != null) {
			stringRecord = leaderboard[3];
		} else if (difficulty.equals("Hard") && leaderboard[5] != null) {
			stringRecord = leaderboard[5];
		}
		
		Integer intRecord = 0;
		intRecord = Integer.valueOf(stringRecord);
		
		if (turn >= intRecord && intRecord != -1) {
			return;
		}		
		
		FileWriter fw = new FileWriter("resources/leaderboard.txt");
		
		String easy = "";
		String medium = "";
		String hard = "";
		
		if (leaderboard[0] != null) {
			easy = leaderboard[0] + "," + leaderboard[1];
		}
		if (leaderboard[2] != null) {
			medium = leaderboard[2] + "," + leaderboard[3];
		}
		if (leaderboard[4] != null) {
			hard = leaderboard[4] + "," + leaderboard[5];
		}
		
		if (difficulty.equals("Easy")) {
			fw.write("Player1," + turn + "\n" + medium + "\n" + hard);
		} else if (difficulty.equals("Medium")) {
			fw.write(easy + "\nPlayer1," + turn + "\n" + hard);
		} else {
			fw.write(easy + "\n" + medium + "\nPlayer1," + turn);
		}
		
		fw.close();
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
