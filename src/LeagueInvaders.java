import javax.swing.JFrame;

public class LeagueInvaders {
	
	JFrame gameWindow;
	public static final int WIDTH = 500; 
	public static final int HEIGHT = 800;
	GamePanel gamePanel;
	
	public LeagueInvaders() {
		gameWindow = new JFrame();
		gamePanel = new GamePanel();
	}
	
	public void setup() {
		gameWindow.add(gamePanel);
		gameWindow.addKeyListener(gamePanel);
		gameWindow.setSize(WIDTH, HEIGHT);
		gameWindow.setVisible(true);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		LeagueInvaders game = new LeagueInvaders();
		game.setup();
	}

}
