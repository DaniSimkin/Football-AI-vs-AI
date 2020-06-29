import java.awt.Color;

public class GoalKeeper extends Player {
	
	public GoalKeeper(int x, int y, double a, Color tc, int s){
		super(x,y,a,tc,s);
	}
	
	public GoalKeeper(Color tc, int s){
		super(tc,s);
		playerSpeed = GOALKEEPER_SPEED;
	}

	@Override
	public void goToInitialPosition(int width, int height) {
		setCenter(20 + RADIUS + side*((width - 40) - RADIUS * 2), height/2);
	}

	@Override
	public void move() {
		double dirX = Math.cos(alpha);
		double dirY = Math.sin(alpha);
		if(cy + RADIUS > FootBallField.getHeight() / 2 + 100){
			cy = FootBallField.getHeight()/2 + 100 - RADIUS;
		}
		else if(cy - RADIUS < FootBallField.getHeight() / 2 - 100){
			cy = FootBallField.getHeight()/2 - 100 + RADIUS;
		}
		
		cx = cx + playerSpeed * dirX;
		cy = cy + playerSpeed * dirY;
		
	}

	@Override
	public void decideWhatToDo(Ball b, boolean inControl) {
		if(!inControl){
			if(b.getBallY() > cy){
				alpha = Math.PI / 2;
			}
			else{
				alpha = -Math.PI/2;
			}
		}
		
	}

	

}
