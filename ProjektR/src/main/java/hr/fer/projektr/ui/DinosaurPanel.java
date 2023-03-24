package hr.fer.projektr.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import hr.fer.projektr.game.GameInterface;
import hr.fer.projektr.game.entities.Entity;

public class DinosaurPanel extends JPanel implements ActionListener {
	private final static int DELAY = 10;
	
	private static final long serialVersionUID = 1L;
	
	private GameInterface game;
	private EntityImage ei;
	private Timer timer;
	
	public DinosaurPanel(GameInterface game) {
		this.game = game;
		ei = new EntityImage();
        timer = new Timer(DELAY, this);

		gameStart();
	}
	
	public void gameStart() {
		game.start(3);
        timer.start();
	}
	
	private void render() {
		if (game.isOver()) {
			timer.stop();
			return;
		}
		
		game.step();
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		/*
		Ovo dolje popravlja moje linux zivotne probleme, pls ne micati.
		Ty,
		Matej.
		 */
		Toolkit.getDefaultToolkit().sync();
		drawEntity(g, game.getPlayer());
		drawCollider(g, game.getPlayer());
		for (Entity entity : game.getEnemies()) {
			drawEntity(g, entity);
			drawCollider(g, entity);
		}
		
		Insets insets = getInsets();
		int width = getWidth() - (insets.left + insets.right);
		
		FontMetrics fm = getFontMetrics(getFont());
		
		g.drawString(Integer.toString(game.getScore()), width - fm.stringWidth(Integer.toString(game.getScore())), fm.getAscent());
	}
	
	private void drawEntity(Graphics g, Entity entity) {
		Image image = ei.getEntityImage(entity);
		double widthAdjustment = ei.getWidthAdjustments(entity);
		g.drawImage(image, convertWidth(entity.getLeftX() - widthAdjustment),
							convertHeight(entity.getTopY()),
							convertWidth(entity.getWidth() + 2*widthAdjustment),
							convertHeight(entity.getHeight()),
				null);
	}

	private void drawCollider(Graphics g, Entity entity){
		g.setColor(Color.RED);
		g.drawRect(convertWidth(entity.getLeftX()), convertHeight(entity.getTopY()), convertWidth(entity.getWidth()), convertHeight(entity.getHeight()));
		g.setColor(Color.BLACK);
	}

	private int convertWidth(double width) {
		Insets insets = getInsets();
		int widthComp = getHeight() - (insets.left + insets.right);

		return (int) (width * (widthComp - 1));
	}
	
	private int convertHeight(double height) {
		Insets insets = getInsets();
		int heightComp = getHeight() - (insets.top + insets.bottom);

		return (int) (height * (heightComp - 1));
	}

	private int scaleSize(double size) {
		Insets insets = getInsets();
		int length = Math.min(getWidth() - (insets.left + insets.right), getHeight() - (insets.top + insets.bottom));

		return (int) (size * (length - 1));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		render();
	}

}