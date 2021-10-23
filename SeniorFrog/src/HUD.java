import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	static int time = 200;
	static int slower = 10000000;
	static int score = 0;
	public void tick() {
		
		slower--;
		if(slower%3 == 0)
			time--;
		time = Game.clamp(time, 0, 200);
		
	}
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(15, 15, 200, 20);
		g.setColor(Color.pink.darker());
		g.fillRect(15, 15, time, 20);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 20);
		g.drawString("Score: "+ score, 15, 50);
	}
	public void decrementScore() {
		score--;
	}
	public void incrementScore() {
		score++;
	}
	

}
