package edu.wm.cs.cs301.guimemorygame.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.wm.cs.cs301.guimemorygame.controller.KeyboardButtonAction;
import edu.wm.cs.cs301.guimemorygame.model.MemoryGameModel;

public class MemoryGameBoard {
	
	private final JFrame frame;
	
	private JPanel titlePanel;
	
	private JLabel instructionLabel;
	
	private final JPanel characterGridPanel;
	
	private JPanel turnPanel;
	
	private JLabel turnLabel;
	
	private final KeyboardButtonAction action;
	
	public MemoryGameBoard(MemoryGameModel model) {		
		this.action = new KeyboardButtonAction(this, model);
		this.instructionLabel = new JLabel("Click on a card to begin", JLabel.CENTER);
		this.titlePanel = createTitlePanel();
		this.characterGridPanel = createCharacterGridPanel(model.getRows(), model.getCols(), model.getCharacters());
		this.turnLabel = new JLabel("Turn: " + model.getTurn(), JLabel.CENTER);
		this.turnPanel = createTurnPanel(model.getTurn());
		this.frame = createAndShowGUI();
	}
	
	private JFrame createAndShowGUI() {
		JFrame frame = new JFrame("Memory Game");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//		frame.setJMenuBar(createMenuBar());
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			 public void windowClosing(WindowEvent event) {
				shutdown();
			}
		});
		
		frame.add(titlePanel, BorderLayout.NORTH);
		frame.add(characterGridPanel, BorderLayout.CENTER);
		frame.add(turnPanel, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		
		System.out.println("Frame size: " + frame.getSize());
		
		return frame;
	}

	private JPanel createTitlePanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1));
		
		JLabel label = new JLabel("Memory Game", JLabel.CENTER);
		label.setFont(AppFonts.getTitleFont());
		instructionLabel.setFont(AppFonts.getTextFont());
		panel.add(label);
		panel.add(instructionLabel);
		
		panel.setBorder(new EmptyBorder(10, 100, 10, 100));
		return panel;
	}
	
	private JPanel createCharacterGridPanel(int r, int c, List<String> characters) {
		JPanel panel = new JPanel(new GridLayout(r, c, 10, 10));

		int characterIndex = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				CharacterGamePiece temp = new CharacterGamePiece(characters.get(characterIndex));
				temp.addActionListener(action);
				panel.add(temp);
				characterIndex++;
			}
		}
		
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		return panel;
	}
	
	private JPanel createTurnPanel(int t) {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		turnLabel.setFont(AppFonts.getTextFont());
		panel.add(turnLabel);
		
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		return panel;
	}
	
	public void setInstructionLabel(String text) {
		instructionLabel.setText(text);
	}
	
	public void setTurnLabel(int t) {
		turnLabel.setText("Turn: " + t);
	}
	
	public void shutdown() {
		frame.dispose();
		System.exit(0);
	}
}
