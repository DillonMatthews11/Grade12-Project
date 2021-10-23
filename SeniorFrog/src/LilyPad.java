import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LilyPad extends GameObject{

	public LilyPad(int x, int y, int width, int height, ID id) {
		super(x, y, width, height, id);
		
	}

	@Override
	public void tick() {
		
		
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
		g.setColor(Color.green );
		g.fillOval(x+5, y+3, 40, 40);
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,width,height);
	}

}
