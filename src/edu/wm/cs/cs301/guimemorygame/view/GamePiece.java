package edu.wm.cs.cs301.guimemorygame.view;

public interface GamePiece {
	public boolean equals(GamePiece other);
	public void setVisible(boolean v);
	public boolean isVisible();
	public Character getSymbol();
}
