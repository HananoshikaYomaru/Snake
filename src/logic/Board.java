package logic;

import java.util.Random;

public class Board {
	public static int WIDTH;
	public static int HEIGHT;
	public static int applex;
	public static int appley;
	Snake snake;

	/**
	 * generate apple and snake
	 */
	public Board() {
		WIDTH = 20;
		HEIGHT = 20;
		applex = new Random().nextInt(WIDTH);
		appley = new Random().nextInt(HEIGHT);
		snake = new Snake(this);
	}

	/**
	 * generate an apple and a snake
	 * 
	 * @param WIDTH
	 * @param HEIGHT
	 */
	public Board(int x, int y) {
		WIDTH = x;
		HEIGHT = y;
		applex = new Random().nextInt(x);
		appley = new Random().nextInt(y);
		snake = new Snake(this);
	}

	/**
	 * put the apple at new location but not on the snake
	 */
	public void changeAppleLocation() {
		do {
			applex = new Random().nextInt(WIDTH);
			appley = new Random().nextInt(HEIGHT);
		} while (!appleLocationValid());
	}

	/**
	 * @param WIDTH  new apple WIDTH
	 * @param HEIGHT new apple HEIGHT
	 * @return boolean whether it is valid
	 */
	private boolean appleLocationValid() {
		if (snake.isHead(applex, appley) || snake.isBody(applex, appley))
			return false;
		return true;
	}

	public Snake getSnake() {
		return snake;
	}

	public int boardX(int x) {
		return (x % WIDTH + WIDTH) % WIDTH;
	}

	public int boardY(int y) {
		return (y % HEIGHT + HEIGHT) % HEIGHT;
	}
}
