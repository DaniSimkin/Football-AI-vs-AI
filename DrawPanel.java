import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class DrawPanel extends JPanel implements KeyListener {
	private FootBallField sf;
	private Ball b;
	private boolean gameHasStarted;
	private Timer t;
	private Team t1;
	private Team t2;
	private int score1;
	private int score2;

	public DrawPanel() {
		score1 = 0;
		score2 = 0;
		sf = new FootBallField();
		b = new Ball();
		t = new Timer();
		t1 = new Team(Color.RED, 0);
		t2 = new Team(Color.BLUE, 1);
		myTimer task = new myTimer();
		myTimer timer = new myTimer();
		t.schedule(task, 1000, 30);
		gameHasStarted = false;

	}

	public void checkIfGoal() {
		Rectangle ball = new Rectangle(b.getBallX(), b.getBallY(), b.RADIUS * 2, b.RADIUS * 2);
		if (ball.intersects(sf.getLeftGate())) {
			gameHasStarted = false;
			score2 += 1;

		} else if (ball.intersects(sf.getRightGate())) {
			gameHasStarted = false;
			score1 += 1;
		}
	}

	private class myTimer extends TimerTask {
		public void run() {
			gameHasStarted = true;
			b.checkAngle(sf);
			t1.moveTeam(b);
			t2.moveTeam(b);
			b.move();
			b.checkPlayers(t1.players, 0);
			b.checkPlayers(t2.players, 1);
			checkIfGoal();

			repaint();
		}

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int w = getWidth();
		int h = getHeight();

		sf.setFieldSize(w, h);
		sf.drawMe(g, score1, score2);
		if (!gameHasStarted) {
			b.goToInitialPosition(w, h);
			t1.reArrangeTeam(w, h);
			t2.reArrangeTeam(w, h);

		}

		t1.drawMe(g);
		t2.drawMe(g);
		b.drawMe(g);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		t1.getKeyOrder(b, e);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
