package hr.fer.projektr.ui;


import hr.fer.projektr.game.GameInterface;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DinosaurGame extends JFrame {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static int WIDTH = 950;
	private final static int HEIGHT = 950;
	
	public JPanel panel;

	public DinosaurGame() {		
		setLocation(0, 0);
		setSize(WIDTH, HEIGHT);
		setTitle("Dinosaur");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		initGui();
	}

	public DinosaurGame(GameInterface gameInterface) {
		setLocation(0, 0);
		setSize(WIDTH, HEIGHT);
		setTitle("Dinosaur");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		initGui(gameInterface);
	}
	
	private void initGui() {
		GameInterface game = new GameInterface();

		DinosaurPanel panel = new DinosaurPanel(game);
		add(panel);
		
		addKeyListener(new InputListener(game, panel));
	}

	private void initGui(GameInterface gameInterface) {
		DinosaurPanel panel = new DinosaurPanel(gameInterface);
		add(panel);
	}
	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new DinosaurGame();
			frame.setVisible(true);
		});
	}
	
	private static class InputListener extends KeyAdapter {
		GameInterface game;
		DinosaurPanel panel;
		
		public InputListener(GameInterface game, DinosaurPanel panel) {
			this.game = game;
			this.panel = panel;
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();

			switch (key) {
			case 38:
				game.input(false, true);
				break;
	
			case 40:
				game.input(true, false);
				break;

			case 32:
				if (game.isOver()) {
					panel.gameStart();
				}
				
			default:
				break;
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			
			if (key == 40) {
				game.input(false, false);
			}
		}
	}
	
}
