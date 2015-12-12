package werewolf;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;



public class WerewolfCountdown extends JFrame {
	
	long fiveMinutes = 5000;
	final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("mm :ss");
	final JLabel clock = new JLabel(sdf.format(new Date(fiveMinutes)));
	int x = 0;
	int times = 2; //one less than what we want
	Timer countdown;
	
	public WerewolfCountdown() {
		setLayout(new GridLayout(2,2, 5, 5)) ;
		
		add(clock);
		
		countdown = new Timer(1000, new timer());
		countdown.start();
		
	}
	
	public class timer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			clock.setText(sdf.format(new Date(fiveMinutes)));
			fiveMinutes -= 1000;
			
				
				if (fiveMinutes >= 1000) {
					clock.setText(sdf.format(new Date(fiveMinutes)));
				} else {
					clock.setText(sdf.format(new Date(fiveMinutes)));
					countdown.stop();
				
					fiveMinutes = 5000;
					if(times>0) {
					countdown.restart();
					times--;
					}
//				fiveMinutes = 5000;
//				clock.setText(sdf.format(new Date(fiveMinutes)));
//				countdown.start();
//				Toolkit.getDefaultToolkit().beep();
				}
			

			
		}
		
	}
	
	public static void main(String args[]) {
		WerewolfCountdown gui = new WerewolfCountdown();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(250, 100);
		gui.setTitle("Timer Program");
		gui.setVisible(true);
		
	}

	
}
