import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.Timer;

public class ObjectManager implements ActionListener {
	
	Rocketship rocketship;
	ArrayList<Projectile> projectiles;
	ArrayList<Alien> aliens;
	Random random;
	int score;
	Font scoreFont;
	
	public ObjectManager(Rocketship rocket) {
		 this.rocketship = rocket;
		 this.projectiles = new ArrayList<Projectile>();
		 this.aliens = new ArrayList<Alien>();
		 random = new Random();
		 score = 0;
		 scoreFont = new Font("Arial", Font.PLAIN,24);
	}
	
	public void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
	}
	
	public void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH),0,50,50));
	}
	
	public void update() {
			for (Iterator<Alien> iterator = aliens.iterator(); iterator.hasNext();) {
				Alien alien = iterator.next();
				alien.update();
				
				if(alien.y > LeagueInvaders.HEIGHT) {
					alien.isActive = false;
				}
				
			}
			
			for (Iterator<Projectile> iterator = projectiles.iterator(); iterator.hasNext();) {
				Projectile projectile = iterator.next();
				projectile.update();
				
				if(projectile.y < 0) {
					projectile.isActive = false;
				}
				
			}
			
			rocketship.update();
			
			checkCollision();
			
			purgeObjects();
	}
	
	public void draw(Graphics g) {
		rocketship.draw(g);
		
		for (Iterator<Alien> iterator = aliens.iterator(); iterator.hasNext();) {
			
			Alien alien = iterator.next();
			alien.draw(g);
		
			
		}
		
		for (Iterator<Projectile> iterator = projectiles.iterator(); iterator.hasNext();) {
			
			Projectile projectile = iterator.next();
			projectile.draw(g);
			
		}
		
		g.setFont(scoreFont);
		g.setColor(Color.MAGENTA);
		g.drawString("Score: " + score, LeagueInvaders.WIDTH / 2 - 50 , 50);
	}
	
	public void purgeObjects() {
		
		for (Iterator<Alien> iterator = aliens.iterator(); iterator.hasNext();) {
			
			Alien alien = iterator.next();
			
			if(!alien.isActive) {
				iterator.remove();
			}
		}
		
		for (Iterator<Projectile> iterator = projectiles.iterator(); iterator.hasNext();) {
			
			Projectile projectile = iterator.next();
			
			if(!projectile.isActive) {
				iterator.remove();
			}
			
		}
		
	}
	
	public void checkCollision() {
		for (Iterator<Alien> iterator = aliens.iterator(); iterator.hasNext();) {
			
			Alien alien = iterator.next();
			
			if(alien.collisionBox.intersects(rocketship.collisionBox))  {
				alien.isActive = false;
				rocketship.isActive = false;
				
			}
			
			for (Iterator<Projectile> iteratorp = projectiles.iterator(); iteratorp.hasNext();) {
				
				Projectile projectile = iteratorp.next();
				
				if(alien.collisionBox.intersects(projectile.collisionBox)) {
					alien.isActive = false;
					projectile.isActive = false;
					score++;
				}
			}
		}
	}
	
	public int getScore() {
		return this.score;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		addAlien();
	}
}
