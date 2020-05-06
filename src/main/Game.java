package main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import UI.GUI;

public class Game {
	public static void main(String args[]) {
		EventQueue.invokeLater(() -> {
			JFrame ui = new GUI();
			ui.setVisible(true);
		});
	}
}
