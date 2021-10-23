import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends GameObject {
	Handler handler;

	public Player(int x, int y, int height, int width, ID id, Handler handler) {
		super(x, y, width, height, id);
		this.handler = handler;

	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		collision();

	}

	private void collision() {
		for (int i = 0; i < handler.enemy.size(); i++) {
			GameObject tempObject = handler.enemy.get(i);
			if (tempObject.getID() == ID.Enemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					while (handler.enemy.size() > 0 || handler.object.size() > 0) {
					
						handler.remover();
						
					}
					Game.died = true;
					Game.gameState = Game.STATE.Menu;

				}
			}

		}
	}

	
	
	@Override
	public void render(Graphics2D g) {
		
		//g.fillRect(x, y, width, height);
		try {
			g.drawImage(ImageIO.read(new File ("src\\Platter Images\\perry.png")),x,y, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Rectangle getBounds() {

		return new Rectangle(x, y, width, height);
	}

}
