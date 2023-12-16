package edu.wm.cs.cs301.guimemorygame.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import edu.wm.cs.cs301.guimemorygame.model.MemoryGameModel;

public class MemoryGameFrame {
	
	private final JFrame frame;
	
	private final CharacterGridPanel characterGridPanel;
	
	public MemoryGameFrame(MemoryGameModel model) {
		this.characterGridPanel = new CharacterGridPanel(this, model, model.getCols() * 100);
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
	
	public void shutdown() {
//		model.getStatistics().writeStatistics();
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