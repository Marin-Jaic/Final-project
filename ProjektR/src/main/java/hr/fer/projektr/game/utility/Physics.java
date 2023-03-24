package hr.fer.projektr.game.utility;

import hr.fer.projektr.game.GameState;
import hr.fer.projektr.game.entities.Enemy;
import hr.fer.projektr.game.entities.Entity;
import hr.fer.projektr.game.entities.EntityType;

public class Physics {
    public static void moveEnemies(GameState gameState){
        for (Enemy enemy: gameState.getEnemies()){
            enemy.setPositionX(enemy.getLeftX() - gameState.getSpeed() * gameState.STEP_DURATION);
        }
    }

    public static void playerUpdate(GameState gameState){
        //ako ne nije u zraku i nema brzinu nema smisla izvrsavati fju
        if (gameState.getPlayer().getVerticalSpeed() == 0. && gameState.getPlayer().getBottomY() == GameState.INITIAL_PLAYER_POSITION_Y){
        	gameState.getPlayer().setJumping(false);
            return;
        }

        if (gameState.getPlayer().getBottomY() + gameState.getPlayer().getVerticalSpeed() * gameState.STEP_DURATION > GameState.INITIAL_PLAYER_POSITION_Y){
            gameState.getPlayer().setBottomPositionY(GameState.INITIAL_PLAYER_POSITION_Y);
            gameState.getPlayer().setVerticalSpeed(0.);
        } else {
            gameState.getPlayer().setBottomPositionY(gameState.getPlayer().getBottomY() + gameState.getPlayer().getVerticalSpeed() * gameState.STEP_DURATION);
            gameState.getPlayer().setVerticalSpeed(gameState.getPlayer().getVerticalSpeed() + GameState.GRAVITY * gameState.STEP_DURATION);
        }
    }

    public static boolean checkCollisions(GameState gameState){
        for (Enemy enemy: gameState.getEnemies()){
            if (areColliding(gameState.getPlayer(), enemy)){
                if (enemy.getEntityType() == EntityType.COIN){
                    gameState.coinCollected();
                    gameState.addToBeRemoved(enemy);
                } else {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean areColliding(Entity first, Entity second){

        if (first.getBottomY() < second.getTopY() || second.getBottomY() < first.getTopY()) {
            return false;
        }
        if (first.getRightX() < second.getLeftX() || second.getRightX() < first.getLeftX()) {
            return false;
        }
        return true;

    }

  //Sluzilo samo za isprobavanje skoka, mozda korisno za ubuduce, inace ignore ili delete

    /* 
    public static void main(String ...args) {
    	double positionX = 0;
    	double positionY = 1;
    	double speed = 0.785;
    	double gravity = 1;
    	double t = 0;
    	
    	@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
    	String s;
    	boolean jump = false;
    	
    	while(true) {
    		s = sc.nextLine();
    		
    		if(s.equals("w") && jump == false) 
    			jump = true;
    		else if (!(s.equals("w")) && jump == true)
    			jump = false;
    		
    		//positionX += speed * t;
    		
    		if(jump == true && (positionY < 1.000001 && positionY > 0.9999999)) {
	    		do {
	    			//konstante namjestati, gravitacija najvise ovisi o frameratu zbog kvadrata, paziti na to
	    			t += 0.1;
	    			positionY = positionY - speed * t + gravity * t * t;
	    			
	    			if(positionY >= 0.99) {
	    				positionY = 1;
		    			t = 0;
	    			}
	    			
	    			//printove zamijeniti s iscrtavanjem
	    			System.out.println("PositionY: " + positionY);
	    			System.out.println("t: " + t);
	    		} while(positionY < 0.99);
	    		
    		}
    	}
    	
    }
    */
}
