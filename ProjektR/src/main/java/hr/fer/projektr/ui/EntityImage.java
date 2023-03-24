package hr.fer.projektr.ui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import hr.fer.projektr.game.entities.Entity;
import hr.fer.projektr.game.entities.EntityType;
import hr.fer.projektr.game.entities.Player;

public class EntityImage {
	private static int COUNTER_LIMIT = 20;
	
	private Image playerCurrent;
	
	private Image playerImage1;
	private Image playerDuckingImage1;
	private Image playerImage2;
	private Image playerDuckingImage2;
	private Image playerJumpingImage;
	private Image cactusSmallImage;
	private Image cactusStandardImage;
	private Image cactusLargeImage;
	private Image birdImage;
	private Image cactusSvenImage;
	private Image coinImage;
	private int counter;
	
	public EntityImage() {
		try {
			this.playerImage1 = ImageIO.read(getClass().getClassLoader().getResource("DinoRunning1.png"));
			this.playerDuckingImage1 = ImageIO.read(getClass().getClassLoader().getResource("DinoDucking1.png"));
			this.playerImage2 = ImageIO.read(getClass().getClassLoader().getResource("DinoRunning2.png"));
			this.playerDuckingImage2 = ImageIO.read(getClass().getClassLoader().getResource("DinoDucking2.png"));
			this.playerJumpingImage = ImageIO.read(getClass().getClassLoader().getResource("Dino.png"));
			this.cactusSmallImage = ImageIO.read(getClass().getClassLoader().getResource("CactusSmall.png"));
			this.cactusStandardImage = ImageIO.read(getClass().getClassLoader().getResource("CactusStandard.png"));
			this.cactusLargeImage = ImageIO.read(getClass().getClassLoader().getResource("CactusLarge.png"));
			this.cactusSvenImage = ImageIO.read(getClass().getClassLoader().getResource("CactusSven.png"));
			this.birdImage = ImageIO.read(getClass().getClassLoader().getResource("Bird.png"));
			this.coinImage = ImageIO.read(getClass().getClassLoader().getResource("Coin.png"));
			this.counter = 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Image getEntityImage(Entity entity) {
		EntityType type = entity.getEntityType();
		
		switch (type) {
			case PLAYER:
				Player player = (Player) entity;
				
				if (player.isJumping()) {
					return playerJumpingImage;
				}
				
				if (player.isDucking()) {
					if (playerCurrent != playerDuckingImage1 && playerCurrent != playerDuckingImage2) {
						counter = 0;
						playerCurrent = playerDuckingImage1;
						return playerCurrent;
					}
					
					if (playerCurrent == playerDuckingImage1 && counter > COUNTER_LIMIT) {
						counter = 0;
						playerCurrent = playerDuckingImage2;
					} else if (counter > COUNTER_LIMIT) {
						counter = 0;
						playerCurrent = playerDuckingImage1;	
					}
				} else {
					if (playerCurrent != playerImage1 && playerCurrent != playerImage2) {
						counter = 0;
						playerCurrent = playerImage1;
						return playerCurrent;
					}
					
					if (playerCurrent == playerImage1 && counter > COUNTER_LIMIT) {
						counter = 0;
						playerCurrent = playerImage2;
					} else if (counter > COUNTER_LIMIT) {
						counter = 0;
						playerCurrent = playerImage1;
					}
				}
				
				counter++;
				return playerCurrent;
		
			case CACTUS_SMALL:
				return cactusSmallImage;
				
			case CACTUS_STANDARD:
				return cactusStandardImage;
				
			case CACTUS_LARGE:
				return cactusLargeImage;

			case CACTUS_LONG:
				return cactusSvenImage;

			case BIRD:
				return birdImage;
			case COIN:
				return coinImage;
			default:
				return null;
		}
	}

	public double getWidthAdjustments(Entity entity){

		if (entity.getEntityType().equals(EntityType.PLAYER) && ((Player) entity).isDucking()){
			return 0;
		}

		BufferedImage entityImage = (BufferedImage) getEntityImage(entity);
		return (entity.getHeight() * entityImage.getWidth() / entityImage.getHeight() - entity.getWidth()) / 2;
	}
}
