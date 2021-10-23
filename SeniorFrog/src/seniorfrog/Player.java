package seniorfrog;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {
	
	public Player(int x, int y, ID id) {
		super(x, y, id);
		
		
		
	}

	@Override
	public void tick() {
		x+= velX;
		y+= velY;
		System.out.println("X:" + x);
		System.out.println("Y:" + y);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.magenta);
		g.fillRect(x, y, 50, 50);
		
		
	}
	

}
