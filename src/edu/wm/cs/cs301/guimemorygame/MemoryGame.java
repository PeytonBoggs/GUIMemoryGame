package edu.wm.cs.cs301.guimemorygame;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.wm.cs.cs301.guimemorygame.model.MemoryGameModel;
import edu.wm.cs.cs301.guimemorygame.view.MemoryGameBoard;

public class MemoryGame {

	public static void main(String[] args) {
		displayInstructions();
		new MemoryGameBoard(new MemoryGameModel("medium"));
	}
	
	private static void displayInstructions() {
		JFrame frame = new JFrame("Instructions");
		frame.setResizable(false);
		
		JPanel panel = new JPanel(new GridLayout(6, 1));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		panel.add(new JLabel("Welcome to Memory Game!"));
		panel.add(new JLabel());
		panel.add(new JLabel("In this game, you'll be shown a grid of cards that are initially flipped over."));
		panel.add(new JLabel("Each turn, you can select two cards to be flipped over by clicking on them."));
		panel.add(new JLabel("If the two cards match, they will stay revealed - if not, they will be flipped back over."));
		panel.add(new JLabel("The object is to reveal all cards in as few turns as possible. Good luck!"));
		
		frame.add(panel);
		
		frame.pack();
		frame.setVisible(true);		
	}
}
