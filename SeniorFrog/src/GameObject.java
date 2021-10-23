import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class GameObject {
	protected int x,y;
	protected ID  id;
	protected int velX, velY;
	  protected  int width;
	  protected  int height;
	
	public GameObject(int x, int y,int width, int height, ID id){
		this.x =x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
	}
	public abstract Rectangle getBounds();
	public abstract void tick();
	public abstract void render(Graphics2D g);
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public void setX ( int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public int getX(){
		return x;
	} 
	public int getY(){
		return y;
	}
	
	public void setID(ID id){
		this.id = id;
	}
	public ID getID(){
		return id;
	}
	public void setVelX(int velX){
		this.velX = velX;
	}
	public void setVelY(int velY){
		this.velY = velY;
	}
	public int getVelX(){
		return velX;
	}
	public int getVelY(){
		return velY;
	}

}
