package seniorfrog;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable, KeyListener{
	
	public static final int HEIGHT = 600;
	public static final int WIDTH = 1000;
	boolean up, down, left, right;
	int tempX = 0, tempY;
	public Thread thread;
	private boolean running = false;
	boolean moving;
	Handler handler;
	Game(){
		
		handler = new Handler();
		handler.addObject(new Player(475, 500 ,ID.Player));
		new Window(WIDTH, HEIGHT, "Platter", this);
		addKeyListener(this);
		 
		
		 
		 
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void run(){
		
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			
			
			
			if(up == true){
				if(handler.getObject(0).getY()<= tempY-50){
					handler.getObject(0).setVelY(0);
					up =false;
				}
				
				handler.getObject(0).setVelY(-3);
			}
			if(left == true){
				if(handler.getObject(0).getX()<= tempX-50){
					handler.getObject(0).setVelX(0);
					left =false;
				}
				
				handler.getObject(0).setVelX(-3);
			}
			
			if(down == true){
				handler.getObject(0).setVelY(3);
			}else if(up == false){
				handler.getObject(0).setVelY(0);
			}
			if(right == true){
				handler.getObject(0).setVelX(3);
			}else if(left == false){
				handler.getObject(0).setVelX(0);
			}
			
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			while(delta>= 1){
				tick();
				delta --;
			}
			if(running)
				render();
			frames++;
			if(System.currentTimeMillis()-timer > 1000){
				timer += 1000;
				//System.out.println("FPS "+ frames);
				frames = 0;
			}
			
		}
		stop();
		
	}
	private void tick(){
		handler.tick();
		
	}
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g);
		g.dispose();
		bs.show();
		
		
	}

	public static void main(String[] args) {
		new Game();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		System.out.println(key);
		switch(e.getKeyCode()){
		case 87:
			up = true;
			tempY = handler.getObject(0).getY();
			break;
		case 65:
			left = true;
			tempX = handler.getObject(0).getX();
			break;
		case 83:
			down = true;
			break;
		case 68:
			right = true;
			break;
		}
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
		case 87:
			
			break;
		case 65:
			
			break;
		case 83:
			down = false;
			break;
		case 68:
			right = false;
			break;
		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
