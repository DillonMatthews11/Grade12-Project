
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable, KeyListener,MouseListener {

	public static final int HEIGHT = 725;
	public static final int WIDTH = 725;
	boolean up, down, left, right;
	int tempX = 0, tempY;
	public Thread thread;
	private boolean running = false;
	boolean moving;
	private Handler handler;
	private HUD hud = new HUD();
	Random rng = new Random();
	int terrainRNG;
	int lilyRNG;
	Font titleFont = new Font("Times New Roman", 16, 32);
	Font optionFont = new Font("Times New Roman", 16, 20);
	int randomPlat;
	static boolean died = false;
	
	public enum STATE {
		Game, Menu;
	}

	public static STATE gameState = STATE.Menu;

	public void createStage() {
		HUD.time = 200;
		HUD.slower = 10000000;
		
		handler.addPlayer(new Player(300, 650, 50, 50, ID.Player, handler));
		handler.addObject(new Platform(0, HEIGHT - 75, WIDTH, 50, ID.Platform, 0));
		for (int i = HEIGHT - 125; i >= 50; i -= 50) {
			terrainRNG = rng.nextInt(3);
			if (terrainRNG == 0 || terrainRNG == 1) {
				
				handler.addObject(new Platform(0, i, WIDTH, 50, ID.Platform, rng.nextInt(2)));
				
			} else {

				for (int j = WIDTH + 25; j >= 0; j -= 50) {

					lilyRNG = rng.nextInt(2);
					if (lilyRNG == 0) {
						handler.addObject(new LilyPad(j, i, 50, 50, ID.Platform));
					} else {
						handler.addEnemy(new Water(j, i, 50, 50, ID.Enemy));
					}
				}
			}

		}
	}
	
	Game() {
		
		new Window(WIDTH, HEIGHT, "Platter", this);
		handler = new Handler();
		
		if (gameState == STATE.Game) {
			
			hud = new HUD();
			createStage();
			
	
		}

		addKeyListener(this);
		addMouseListener(this);
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			if (gameState == STATE.Game) {
				
				if(up == true){
					if(handler.getPlayer(0).getY() <= tempY-handler.getPlayer(0).getHeight()){
						handler.getPlayer(0).setVelY(0);
						up =false;
					}
					
					handler.getPlayer(0).setVelY(-5);
				}
				if(left == true){
					if(handler.getPlayer(0).getX()<= tempX-handler.getPlayer(0).getWidth()){
						handler.getPlayer(0).setVelX(0);
						left =false;
					}
					
					handler.getPlayer(0).setVelX(-5);
				}
				
				if(down == true){
					if(handler.getPlayer(0).getY()>= tempY+handler.getPlayer(0).getHeight()){
						handler.getPlayer(0).setVelX(0);
						down =false;
					}
					handler.getPlayer(0).setVelY(5);
				}else if(up == false){
					handler.getPlayer(0).setVelY(0);
				}
				if(right == true){
					if(handler.getPlayer(0).getX() >= tempX+handler.getPlayer(0).getWidth()){
						handler.getPlayer(0).setVelX(0);
						right =false;
					}
					handler.getPlayer(0).setVelX(5);
				}else if(left == false){
					handler.getPlayer(0).setVelX(0);
				}
				if(HUD.time <= 0) {
					
					while (handler.enemy.size() > 0 || handler.object.size() > 0) {
						died = true;
						handler.remover();
						
					}
					gameState = STATE.Menu;
				
			}

				else if(handler.getPlayer(0).getY() <= 0) {
					
					up = false;
					while (handler.enemy.size() > 0 || handler.object.size() > 0) {
						handler.remover();
					}
					HUD.time = 200;
					HUD.slower = 10000000;
					createStage();
				}
			}

			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS "+ frames);
				frames = 0;
			}

		}
		stop();

	}

	public static int clamp(int var, int min, int max) {
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}

	private void tick() {
		handler.tick();
		if (gameState == STATE.Game) {
			hud.tick();
		}
		

	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g);
		if (gameState == STATE.Game) {
			hud.render(g);
		} else {
			up = false;
			right = false;
			down = false;
			left= false;
			g.setColor(Color.white);
			g.setFont(titleFont);
			g.drawString("Platter", 320, 200);
			g.setColor(Color.cyan);
			g.fillRect(250, 300, 200, 100);
			g.setColor(Color.black);
			g.setFont(optionFont);
			g.drawString("Play", 335, 350);
		}
		g.dispose();
		bs.show();

	}

	public static void main(String[] args) {

		new Game();

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		switch (e.getKeyCode()) {
		case 87:
			if (handler.getPlayer(0).getY() > 0) {
				up = true;
				hud.incrementScore();
				tempY = handler.getPlayer(0).getY();
			}
			break;

		case 65:
			if (handler.getPlayer(0).getX() > 0) {
				left = true;
				tempX = handler.getPlayer(0).getX();
			}
			break;

		case 83:
			if (handler.getPlayer(0).getY() < 650) {
				down = true;
				hud.decrementScore();
				tempY = handler.getPlayer(0).getY();
			}
			break;
		case 68:
			if (handler.getPlayer(0).getX() < WIDTH - 100) {
				right = true;
				tempX = handler.getPlayer(0).getX();
			}
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 87:

			break;
		case 65:

			break;
		case 83:

			break;
		case 68:

			break;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(gameState == STATE.Menu) {
			if(e.getX()>=250&&e.getX()<=450&&e.getY() <= 400&&e.getY()>=300) {
				gameState = STATE.Game;
				HUD.score = 0;
				createStage();
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
