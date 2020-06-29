import java.awt.Color;

public class BottomForward extends Player {
	
	public BottomForward(int x, int y, double a, Color tc, int s){
		super(x,y,a,tc,s);
	}
	
	public BottomForward(Color tc, int s){
		super(tc,s);
		playerSpeed = FORWARD_SPEED;
	}


	@Override
	public void goToInitialPosition(int width, int height) {
		setCenter((int)(20 + (width - 20 * 2) *0.4 + side*(width - 20 *2)/5), 20 + (3*(height - 40)/4));
	}

	@Override
	public void move() {
		
		double dirX = Math.cos(alpha);
		double dirY = Math.sin(alpha);
		if(cy - RADIUS < FootBallField.getHeight() / 2){
			cy = FootBallField.getHeight() / 2 + RADIUS;
		}
		cx = cx + dirX * playerSpeed;
		cy = cy + dirY * playerSpeed;
		

	}

	@Override
	public void decideWhatToDo(Ball b, boolean inControl) {
		if (!inControl) {
			if (side == 0 && b.getBallX() < FootBallField.getWidth() / 2) {
				if (b.getBallY() > cy) {
					alpha = Math.PI / 2;
				} else {
					alpha = -Math.PI / 2;
				}
			} else if (side == 1 && b.getBallX() >FootBallField.getWidth() / 2) {
				if (b.getBallY() > cy) {
					alpha = Math.PI / 2;
				} else {
					alpha = -Math.PI / 2;
				}
			}

			else {
				double distanceX = b.getBallX() - cx;
				double distanceY = b.getBallY() - cy;
				alpha = Math.atan(distanceY / distanceX);

				if (distanceX < 0) {
					alpha = alpha + Math.PI;
				}
			}
		}

	}

}
