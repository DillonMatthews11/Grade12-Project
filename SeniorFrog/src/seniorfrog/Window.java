package seniorfrog;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {
	public Window(int length, int width, String title, Game game){
		JFrame frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(length, width));
		frame.setMaximumSize(new Dimension(length, width));
		frame.setMinimumSize(new Dimension(length, width));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
		
	}
}
