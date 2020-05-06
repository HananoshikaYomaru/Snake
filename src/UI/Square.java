package UI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Square extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7365498625357459529L;
	Color c;

	public Square(Color c, int width, int height) {
		setPreferredSize(new Dimension(width, height));
		this.setBackground(c);
		this.c = c;
	}

	public void changeColor(Color c) {
		this.setBackground(c);
		this.c = c;
		repaint();
	}
}
