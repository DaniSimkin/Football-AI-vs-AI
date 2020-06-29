import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		JFrame f = new JFrame();
		DrawPanel dp = new DrawPanel();
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1040, 740);
		f.add(dp);
		f.setLocation(400,100);
		f.setVisible(true);

	}

}
