package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Snake {
	Board board;
	ArrayList<Dot> dots = new ArrayList<Dot>();

	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	};

	private static HashMap<Direction, Direction> opposite = new HashMap<Direction, Direction>() {
		private static final long serialVersionUID = 5800019278213603286L;

		{
			put(Direction.UP, Direction.DOWN);
			put(Direction.DOWN, Direction.UP);
			put(Direction.LEFT, Direction.RIGHT);
			put(Direction.RIGHT, Direction.LEFT);
		};

	};
	public Direction d;

	// cache
	int length;
	private Dot lastDot;
	public Dot head;
	private final int INITIAL_LENGTH = 3;

	public class Dot {
		public int x;
		public int y;

		Dot(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			if (o instanceof Dot == false)
				return false;

			Dot doto = (Dot) o;
			if (board.boardX(doto.x) == board.boardX(x) && board.boardY(doto.y) == board.boardY(y))
				return true;
			else
				return false;
		}

	}

	Snake(Board board) {

		this.board = board;
		// randomly choose a direction
		this.d = Direction.values()[new Random().nextInt(4)];

		// randomly spawn head
		getDots().add(new Dot(new Random().nextInt(Board.WIDTH), new Random().nextInt(Board.HEIGHT)));
		head = getDots().get(0);
		length = 1;
		lastDot = head;
		for (; length < INITIAL_LENGTH;) {
			move();
			add();
		}

	}

	public void eat() {
		// if head = apple , return true
		if (board.boardX(head.x) == Board.applex && board.boardY(head.y) == Board.appley) {
			add();
			board.changeAppleLocation();
		}
	}

	/**
	 * remove the lastdot and add a new dot, so the total length is not change .
	 * last dot is useful for add function
	 */
	public void move() {
		int x = head.x;
		int y = head.y;
		lastDot = getDots().get(length - 1);
		getDots().remove(length - 1);
		switch (d) {
		case UP:
			y++;
			break;
		case DOWN:
			y--;
			break;
		case LEFT:
			x--;
			break;
		case RIGHT:
			x++;
			break;
		default:
			throw new IllegalStateException("no such direction ");
		}
		getDots().add(0, new Dot(x, y));
		head = getDots().get(0);
		eat();
	}

	public void changeDirection(Direction d) {
		// doesn't allow move opposite
		if (!opposite.get(d).equals(this.d))
			this.d = d;
	}

	public boolean isHead(int x, int y) {
		return head.equals(new Dot(x, y)) ? true : false;
	}

	public boolean isBody(int x, int y) {
		Dot temp = getDots().get(0);
		getDots().remove(0);
		boolean result = getDots().contains(new Dot(x, y));
		getDots().add(0, temp);
		return result;
	}

	public boolean crash() {
		return isBody(head.x, head.y) ? true : false;
	}

	public void add() {
		getDots().add(new Dot(getLastDot().x, getLastDot().y));
		length++;
		lastDot = null;
	}

	public Dot getLastDot() {
		return lastDot;
	}

	public ArrayList<Dot> getDots() {
		return dots;
	}
}
