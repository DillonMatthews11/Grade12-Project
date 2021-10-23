


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

public class Handler {
	 LinkedList<GameObject> object = new LinkedList<GameObject>();
	LinkedList<GameObject> player = new LinkedList<GameObject>();
	LinkedList<GameObject> enemy = new LinkedList<GameObject>();
	
	public void tick() {
		for (int i = 0; i < player.size(); i++) {
			GameObject tempObject = player.get(i);
			tempObject.tick();
		}
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
		for (int i = 0; i < enemy.size(); i++) {
			GameObject tempObject = enemy.get(i);
			tempObject.tick();
		}
	}

	public void render(Graphics2D g) {
		for (int i = 0; i < enemy.size(); i++) {
			GameObject tempObject = enemy.get(i);
			tempObject.render(g);
		}

		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
		for (int i = 0; i < player.size(); i++) {
			GameObject tempObject = player.get(i);
			tempObject.render(g);
		}
		
	}

	public void addObject(GameObject object) {
		this.object.add(object);

	}

	public GameObject getObject(int index) {
		return object.get(index);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	public void addPlayer(GameObject player) {
		this.player.add(player);

	}

	public GameObject getPlayer(int index) {
		return player.get(index);
	}

	public void removePlayer(GameObject player) {
		this.player.remove(player);
	}
	public void addEnemy(GameObject object) {
		this.enemy.add(object);

	}

	public GameObject getEnemy(int index) {
		return enemy.get(index);
	}

	public void removeEnemy(GameObject object) {
		this.enemy.remove(object);
	}
	public void remover() {
		for (int j = 0; j < object.size(); j++)
			removeObject(getObject(j));
		for (int j = 0; j < player.size(); j++)
			removePlayer(getPlayer(j));
		for (int j = 0; j < enemy.size(); j++)
			removeEnemy(getEnemy(j));
	}
}
