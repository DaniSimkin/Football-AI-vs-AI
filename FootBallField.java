import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FootBallField {
	final int DELTA = 15;
	private static int width;
	private static int height;
	Gate leftGate;
	Gate rightGate;

	public FootBallField() {
		leftGate = new Gate();
		rightGate = new Gate();
	}

	public void setFieldSize(int w, int h) {
		width = w;
		height = h;
	}
	
	public static int getWidth(){
		return width;
	}
	
	public static int getHeight(){
		return height;
	}

	public void drawMe(Graphics g, int s1, int s2) {
		for (int i = 0; i < DELTA; i++) {
			if (i % 2 == 0) {
				g.setColor(new Color(100, 200, 100));
			} else {
				g.setColor(new Color(120, 230, 120));
			}
			g.fillRect(i * (width / DELTA), 0, width / DELTA, height);
		}
		g.setColor(Color.WHITE);
		for (int i = 0; i < 3; i++) {
			g.drawRect(20 + i, 20 + i, width - (40 + i * 2), height - (40 + i * 2));
		}
		g.drawLine(width / 2, 20, width / 2, height - 20);
		g.drawOval(width / 2 - 100, height / 2 - 100, 200, 200);
		g.fillOval(width / 2 - 10, height / 2 - 10, 20, 20);

		 g.drawRect(20, height / 2 - 100, 100, 200);
		 g.drawRect(width - 120, height / 2 - 100, 100, 200);
		 leftGate.setGates(20, height/2-50, 20, 100);
		 leftGate.drawMe(g);
		 rightGate.setGates(width - 40, height/2-50, 20, 100);
		 rightGate.drawMe(g);
		 
		 g.setColor(Color.RED);
		 g.setFont(new Font("serif", Font.BOLD, 25));
		 g.drawString("Team Red: " + s1, 280, 20);
		 
		 g.setColor(Color.BLUE);
		 g.setFont(new Font("serif", Font.BOLD, 25));
		 g.drawString("Team Blue: " + s2, 600, 20);
	}
	
	public Rectangle getBorders(){
		return new Rectangle(20,20,width-40,height-40);
	}
	
	public Rectangle getLeftGate(){
		return new Rectangle(leftGate.left,leftGate.top,leftGate.width,leftGate.height);
	}
	
	public Rectangle getRightGate(){
		return new Rectangle(rightGate.left + 10,rightGate.top,rightGate.width,rightGate.height);
	}
}
