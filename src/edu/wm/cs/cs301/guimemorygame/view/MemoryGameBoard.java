package edu.wm.cs.cs301.guimemorygame.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import edu.wm.cs.cs301.guimemorygame.controller.KeyboardButtonAction;
import edu.wm.cs.cs301.guimemorygame.model.MemoryGameModel;

public class MemoryGameBoard {
	
	private final JFrame frame;
	
	private final JPanel characterGridPanel;
	
	private final KeyboardButtonAction action;
	
	public MemoryGameBoard(MemoryGameModel model) {
		displayInstructions();
		
		this.action = new KeyboardButtonAction(this, model);
		this.characterGridPanel = createCharacterGridPanel(model.getRows(), model.getCols(), model.getCharacters());
		this.frame = createAndShowGUI();
	}
	
	private void displayInstructions() {
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
		
		frame.add(createTitlePanel(), BorderLayout.NORTH);
		frame.add(characterGridPanel, BorderLayout.CENTER);
		
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		
		System.out.println("Frame size: " + frame.getSize());
		
		return frame;
	}

	private JPanel createTitlePanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		InputMap inputMap = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelAction");
		ActionMap actionMap = panel.getActionMap();
		actionMap.put("cancelAction", new CancelAction());
		
		JLabel label = new JLabel("MemoryGame");
		label.setFont(AppFonts.getTitleFont());
		panel.add(label);
		
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
		
		return panel;
	}
	
	public void shutdown() {
		frame.dispose();
		System.exit(0);
	}
	
	private class CancelAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent event) {
			shutdown();
		}
		
	}
}
