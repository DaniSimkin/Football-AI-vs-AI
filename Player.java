import java.awt.Color;
import java.awt.Graphics;

public abstract class Player implements MoveAble{
	private final int LEFT_SIDE = 0;
	private final int RIGHT_SIDE = 1;
	public final int RADIUS = 10;
	public final double DEFENDER_SPEED = 2.1;
	public final double FORWARD_SPEED = 2.3;
	public final double GOALKEEPER_SPEED = 2.2;

	protected double cx, cy;
	protected double alpha;
	protected double playerSpeed;
	protected Color teamColor;
	protected int side;

	public Player() {
		this(0, 0, 0, Color.WHITE, 0);
	}

	public Player(int x, int y, double angle, Color c, int side) {
		setCenter(x, y);
		setAlpha(angle);
		teamColor = c;
		this.side = side;

	}

	public Player(Color c, int s) {
		this(0, 0, 0, c, s);
		if (s == 1) {
			setAlpha(Math.PI);
		}
	}

	public void setCenter(int x, int y) {
		cx = x;
		cy = y;
	}

	public double getX() {
		return cx;
	}

	public double getY() {
		return cy;
	}

	public double getPlayerSpeed() {
		return playerSpeed;
	}

	public void setAlpha(double angle) {
		alpha = angle;
	}

	public double getAlpha() {
		return alpha;
	}

	public void drawMe(Graphics g) {
		g.setColor(teamColor);
		g.fillOval((int) cx - RADIUS, (int) cy - RADIUS, RADIUS * 2, RADIUS * 2);
		g.setColor(Color.BLACK);
		g.drawOval((int) cx - RADIUS, (int) cy - RADIUS, RADIUS * 2, RADIUS * 2);
		g.drawLine((int) cx, (int) cy, (int) (cx + RADIUS * Math.cos(alpha)), (int) (cy + RADIUS * Math.sin(alpha)));
	}
	
	public abstract void decideWhatToDo(Ball b, boolean inControl);

}
