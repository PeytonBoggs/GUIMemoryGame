package edu.wm.cs.cs301.guimemorygame.view;

import javax.swing.JButton;

public class CharacterGamePiece extends JButton {
	
	private static final long serialVersionUID = 1L;
	private final String symbol;
	private boolean revealed;
	
	public CharacterGamePiece(String s) {
		this.symbol = s;
		this.revealed = false;		
		
		setText(getSymbol());
	}

	public String getSymbol() {
		if (revealed) {
			return this.symbol;
		}
		return "?";
	}
	
	public void setRevealed(boolean v) {
		revealed = v;
	}
	
	public boolean isRevealed() {
		return revealed;
	}
	
	public boolean equals(CharacterGamePiece other) {		
		if (symbol.compareTo(other.getSymbol()) == 0) {
			return true;
		}
		return false;
	}	
}
