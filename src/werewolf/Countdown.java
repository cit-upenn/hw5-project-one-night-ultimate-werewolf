package werewolf;

import java.util.Timer;
import java.util.TimerTask;


public class Countdown {
	//instance variables
	private Timer timer;
	public boolean on;
	
	//constructor
	public Countdown(int sec) {
		timer = new Timer();
		timer.schedule(new RemindTask(), sec * 1000);
		on = false;
	}

	class RemindTask extends TimerTask {
		public void run() {
			System.out.println("Time's up!");
			timer.cancel();
		}
	}

}
