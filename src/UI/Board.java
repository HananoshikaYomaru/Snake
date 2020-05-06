package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import logic.Snake.Direction;

public class Board extends JPanel implements KeyListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int B_WIDTH;
	private final int B_HEIGHT;
	private final int WIDTH;
	private final int HEIGHT;
	private final int DOT_SIZE = 20;
	private final int DELAY = 100;

	private Timer timer;

	logic.Board board;
	logic.Snake snake;

	Square[][] grid;

	static ArrayList<Color> colors = new ArrayList<Color>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			add(Color.WHITE); // background
			add(Color.BLUE); // apple
			add(Color.RED); // head
			add(Color.GREEN); // body
		};
	};

	public Board() {
		board = new logic.Board();

		snake = board.getSnake();
		WIDTH = logic.Board.WIDTH;
		HEIGHT = logic.Board.HEIGHT;
		B_WIDTH = WIDTH * DOT_SIZE;
		B_HEIGHT = HEIGHT * DOT_SIZE;
		addKeyListener(this);
		setBackground(Color.WHITE);
		setFocusable(true);
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		this.requestFocusInWindow();

		setLayout(new GridLayout(HEIGHT, WIDTH, 0, 0));
		grid = new Square[HEIGHT][WIDTH];
		for (int i = 0; i < HEIGHT; ++i) {
			grid[i] = new Square[WIDTH];
			for (int j = 0; j < WIDTH; ++j) {
				grid[i][j] = new Square(colors.get(0), DOT_SIZE, DOT_SIZE);
			}
		}
		for (int y = HEIGHT - 1; y >= 0; y--)
			for (int x = 0; x < WIDTH; ++x)
				add(grid[y][x]);

		initGame();
	}

	private void initGame() {
		for (int i = 0; i < logic.Board.HEIGHT; i++)
			for (int j = 0; j < logic.Board.WIDTH; j++)
				grid[i][j].changeColor(colors.get(0));
		timer = new Timer(DELAY, this);
		timer.start();
	}

	private void gameOver() {
		timer.stop();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		board = new logic.Board();
		snake = board.getSnake();
		initGame();
	}

	@Override
	public void paintComponent(Graphics g) {
		// super.paintComponent(g);
		doDrawing(g);
	}

	private void doDrawing(Graphics g) {

		if (snake.getLastDot() != null) {
			grid[board.boardY(snake.getLastDot().y)][board.boardX(snake.getLastDot().x)].changeColor(colors.get(0));
		}
		for (logic.Snake.Dot dot : snake.getDots()) {
			Color c = dot.equals(snake.head) ? colors.get(2) : colors.get(3);
			grid[board.boardY(dot.y)][board.boardX(dot.x)].changeColor(c);
		}
		grid[logic.Board.appley][logic.Board.applex].changeColor(colors.get(1));
		Toolkit.getDefaultToolkit().sync();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			snake.changeDirection(Direction.UP);
			break;
		case KeyEvent.VK_DOWN:
			snake.changeDirection(Direction.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			snake.changeDirection(Direction.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			snake.changeDirection(Direction.RIGHT);
			break;
		case KeyEvent.VK_SPACE:
			timer.setDelay(DELAY / 2);
			break;
		default:
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			timer.setDelay(DELAY);
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (snake.crash()) {
			gameOver();
		}
		if (!snake.crash())
			snake.move();
		repaint();
	}

}
