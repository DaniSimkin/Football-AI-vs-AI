
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ball implements MoveAble {

	public final int RADIUS = 10;
	private int ballX, ballY;
	double ballSpeed, ballAngle;

	public Ball() {
		ballSpeed = 10;
		ballAngle = 2 * Math.PI * Math.random();
	}

	public void goToInitialPosition(int width, int height) {
		ballX = width / 2;
		ballY = height / 2;
	}

	public void move() {
		double directionX = Math.cos(ballAngle);
		double directionY = Math.sin(ballAngle);
		ballX = (int) (ballX + ballSpeed * directionX);
		ballY = (int) (ballY + ballSpeed * directionY);

	}

	public void drawMe(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		BufferedImage bI = null;

		try {
			bI = ImageIO.read(new File("ball.jpg"));
		} catch (IOException ex) {
			System.out.println("No Image Found");
		}

		TexturePaint tp = new TexturePaint(bI, new Rectangle(ballX + 10, ballY, 50, 50));// check
																							// this
																							// out
		g2d.setPaint(tp);
		g2d.fillOval(ballX - RADIUS, ballY - RADIUS, RADIUS * 2, RADIUS * 2);
		g2d.setColor(Color.BLACK);
		g2d.drawOval(ballX - RADIUS, ballY - RADIUS, RADIUS * 2, RADIUS * 2);
		g2d.dispose();

	}

	public int getBallX() {
		return ballX;
	}

	public int getBallY() {
		return ballY;
	}

	public void setLocation(int x, int y) {
		ballX = x;
		ballY = y;
	}

	public void setBallAngle(double alpha) {
		ballAngle = alpha;
	}

	public double getDirX() {
		return Math.cos(ballAngle);
	}

	public double getDirY() {
		return Math.sin(ballAngle);
	}

	public void checkAngle(FootBallField fb) {
		double dirX = getDirX();
		double dirY = getDirY();

		Rectangle r = fb.getBorders();

		if (dirX > 0) {
			if (ballX + RADIUS > r.getMaxX()) {
				ballAngle = Math.PI - ballAngle;
			}
		} else {
			if (ballX - RADIUS < r.getMinX()) {
				ballAngle = Math.PI - ballAngle;
			}
		}

		if (dirY > 0) {
			if (ballY + RADIUS > r.getMaxY()) {
				ballAngle = -ballAngle;
			}
		} else {
			if (ballY - RADIUS < r.getMinY()) {
				ballAngle = -ballAngle;
			}
		}
	}

	public void checkPlayers(Player[] p, int side) {
		Rectangle br = new Rectangle(ballX, ballY, RADIUS * 2, RADIUS * 2);
		for (int i = 0; i < p.length; i++) {
			Rectangle pr = new Rectangle((int) (p[i].getX()), (int) (p[i].getY()), RADIUS * 2, RADIUS * 2);
			if(pr.intersects(br)){
				if(side == 0){
					if(ballAngle <= 3 * Math.PI / 2 && ballAngle >=  Math.PI/2){
						ballAngle = Math.PI - ballAngle;
					}
					else{
						ballAngle = -Math.PI/2 + Math.random() * Math.PI;
					}
				}
				else if(side == 1){
					if(ballAngle >= - Math.PI/2 && ballAngle < Math.PI){
						ballAngle = Math.PI - ballAngle;
					}
					else{
						ballAngle = -Math.PI/2 + Math.random() * Math.PI;
					}
				}
			}
		}
	}
	


}
