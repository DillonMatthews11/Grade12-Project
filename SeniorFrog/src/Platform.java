

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Platform extends GameObject {

	private int randomInt;

	public Platform(int x, int y, int width, int height, ID id,int randomInt) {
		super(x, y, width, height, id);
		this.randomInt = randomInt;
	}

	@Override
	public void tick() {
		
		
	}

	@Override
	public void render(Graphics2D g) {
		switch(randomInt) {
		case 0: 
			g.setColor(Color.green.darker());
			
			break;
		case 1:
			g.setColor(Color.DARK_GRAY);
			
			break;
		}
		
		
		g.fillRect(0, y, Game.WIDTH, height);
		if(randomInt ==1) {
			g.setColor(Color.yellow);
			for(int i = 0;i<Game.WIDTH;i+=50) {
				g.fillRect(i, y+23, 25,5 );
			}
			
		}
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}

