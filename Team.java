import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Team {
	private final int NUM_PLAYERS = 5;
	private Color teamColor;
	private int side;
	protected Player[] players;

	public Team() {
		this(Color.WHITE, 0);
	}

	public Team(Color c, int s) {
		teamColor = c;
		side = s;
		players = new Player[5];
		players[0] = new GoalKeeper(teamColor, side);
		players[1] = new TopDefender(teamColor, side);
		players[2] = new BottomDefender(teamColor, side);
		players[3] = new BottomForward(teamColor, side);
		players[4] = new TopForward(teamColor, side);

	}

	public void reArrangeTeam(int w, int h) {
		for (int i = 0; i < players.length; i++) {
			players[i].goToInitialPosition(w, h);
		}
	}

	public void checkBall(Ball b) {
		for (int i = 0; i < players.length; i++) {
			double distance = Math.sqrt(
					Math.pow(b.getBallX() - players[i].getX(), 2) + Math.pow(b.getBallY() - players[i].getY(), 2));
			if (distance < b.RADIUS * 2) {
				players[i].decideWhatToDo(b, true);
			}
		}
	}

	public void moveTeam(Ball b) {
		for (int i = 0; i < players.length; i++) {
			players[i].decideWhatToDo(b, false);
			players[i].move();
		}
	}

	public void getKeyOrder(Ball b, KeyEvent e) {
		for (int i = 0; i < players.length; i++) {
			if (e == null) {
				moveTeam(b);
			}

			else {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					players[i].setCenter(
							(int) (players[i].getX() + players[i].getPlayerSpeed() * Math.cos(players[i].getAlpha())),
							(int) (players[i].getY() + players[i].getPlayerSpeed() * Math.sin(players[i].getAlpha())));

					break;

				case KeyEvent.VK_DOWN:
					players[i].setCenter(
							(int) (players[i].getX() - players[i].getPlayerSpeed() * Math.cos(players[i].getAlpha())),
							(int) (players[i].getY() - players[i].getPlayerSpeed() * Math.sin(players[i].getAlpha())));

					break;

				case KeyEvent.VK_LEFT:
					players[i].setAlpha(players[i].getAlpha() - Math.PI / 10);
					break;

				case KeyEvent.VK_RIGHT:
					players[i].setAlpha(players[i].getAlpha() + Math.PI / 10);
					break;
				}
			}
		}
	}

	public void setPlayerZeroCord(int x, int y) {
		players[0].setCenter(x, y);
	}

	public int getCenterOfPlayer() {
		return (int) players[0].getX();
	}

	public void drawMe(Graphics g) {
		for (int i = 0; i < players.length; i++) {
			players[i].drawMe(g);
		}
	}
}
