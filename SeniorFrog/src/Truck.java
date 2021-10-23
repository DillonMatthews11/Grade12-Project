import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Truck extends GameObject {
	Random r = new Random();
	
	public Truck(int x, int y, int width, int height, ID id) {
		super(x, y, width, height, id);
		velX = r.nextInt(5)+1;
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,width,height);
	}

	@Override
	public void tick() {
		
			
		
		x+=2;
		
	}

	@Override
	public void render(Graphics2D g) {
		//try {
			//g.drawImage(ImageIO.read(new File ("src\\Platter Images\\battlebus.png")),x,y, null);
		//} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
		
	}

}
