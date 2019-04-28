import java.awt.Color;
import java.awt.Graphics;

public class Rocketship extends GameObject {
	
	public Rocketship(int x, int y, int width, int height) {
		super(x,y,width,height);
		this.speed = 10;
	}
	
	void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(this.x, this.y, this.width, this.height);
	}
	
	  public void right() {
	        x+=speed;
	    }
	  
	  public void left() {
	        x-=speed;
	    }
	  
	  public void up() {
	        y-=speed;
	    }
	  
	  public void down() {
	        y+=speed;
	    }
}
