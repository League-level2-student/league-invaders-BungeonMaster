import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener,KeyListener {

	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont;
	Font subtitleFont;
	Timer frameDraw;
	Rocketship rocketship;
	ObjectManager objectManager;
	public Timer alienSpawn;
	int lastScore;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	public GamePanel() {
		titleFont = new Font("Arial", Font.PLAIN,48);
		subtitleFont = new Font("Arial", Font.PLAIN, 24);
		frameDraw = new Timer(1000/60,this);
		frameDraw.start();
		rocketship = new Rocketship(250,700,50,50);
		objectManager = new ObjectManager(rocketship);
		lastScore = 0;
		if (needImage) {
		    loadImage ("space.png");
		}
	}
	
	public void updateMenuState() {

	}
	
	public void updateGameState() {
		
		objectManager.update();
		
		if(!rocketship.isActive) {
			currentState = END;
			alienSpawn.stop();
    			lastScore = objectManager.getScore();
    			rocketship = new Rocketship(250,700,50,50);
    			objectManager = new ObjectManager(rocketship);
		}
		
	}
	
	public void updateEndState() {
		
	}

	@Override
	
	public void paintComponent(Graphics g) {
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		}
	}
	
	public void drawMenuState(Graphics g) {
		
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("League Invaders", 65, 100);
		g.setFont(subtitleFont);
		g.drawString("Press ENTER to start", 120, 300);
		g.drawString("Press SPACE for instructions", 90, 500);
		
		
	}
	
	public void drawGameState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}
		objectManager.draw(g);
		
	}
	
	public void drawEndState (Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
		g.setFont(titleFont);
		g.setColor(Color.GREEN);
		g.drawString("Game Over!", LeagueInvaders.WIDTH / 2 - 125, 100);
		g.setFont(subtitleFont);
		g.drawString("Final Score: " + lastScore, LeagueInvaders.WIDTH / 2 - 75, 200);
		
		
	}
	
	public void startGame() {
		alienSpawn = new Timer(1000, objectManager);
		alienSpawn.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		    } 
		    else if (currentState == MENU) {
		        currentState++;
		        startGame();
		    }
		    else {
		    		currentState++;
		    		alienSpawn.stop();
		    		lastScore = objectManager.getScore();
		    		rocketship = new Rocketship(250,700,50,50);
		    		objectManager = new ObjectManager(rocketship);
		    }
		    		
		}			
		if (e.getKeyCode()==KeyEvent.VK_UP) {
		    
		    if(rocketship.y > 25 && currentState == GAME) {
		    		rocketship.up();
		    }
		}
		
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
		    
		    if(rocketship.y < LeagueInvaders.HEIGHT - 100  && currentState == GAME) {
	    			rocketship.down();
		    }
		}
		
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
		    
		    if(rocketship.x < LeagueInvaders.WIDTH - 75  && currentState == GAME) {
    				rocketship.right();
		    }
		}
		
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
		    
		    if(rocketship.x > 25  && currentState == GAME) {
				rocketship.left();
		    }
		}
		
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			if(currentState == GAME) {
				objectManager.addProjectile(rocketship.getProjectile());
			}
		}
	}
	
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}

	@Override
	public void keyReleased(KeyEvent e) {
	
		}
		
}
