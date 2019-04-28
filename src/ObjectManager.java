import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ObjectManager implements ActionListener {
	
	Rocketship rocketship;
	ArrayList<Projectile> projectiles;
	ArrayList<Alien> aliens;
	Random random;
	
	public ObjectManager(Rocketship rocket) {
		 this.rocketship = rocket;
		 this.projectiles = new ArrayList<Projectile>();
		 this.aliens = new ArrayList<Alien>();
		 random = new Random();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		addAlien();
	}
}
