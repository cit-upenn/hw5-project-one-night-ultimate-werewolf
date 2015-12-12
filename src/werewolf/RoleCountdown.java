package werewolf;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class RoleCountdown extends JPanel {
	
	private long fiveSeconds = 5000;
	final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("mm:ss");
	final JLabel clock = new JLabel(sdf.format(new Date(fiveSeconds)));
	Timer countdown;
	int times = 2;
	
	public RoleCountdown() {
		setLayout(new BorderLayout());
		
		
		add(clock, BorderLayout.CENTER);
		
		countdown = new Timer(1000, new RoleTimer());
		countdown.start();
		
		setVisible(true);
		
	}
	
	public class RoleTimer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			clock.setText(sdf.format(new Date(fiveSeconds)));
			fiveSeconds -= 1000;
			if (fiveSeconds >= 1000) {
				clock.setText(sdf.format(new Date(fiveSeconds)));
			} else {
				clock.setText(sdf.format(new Date(fiveSeconds)));
				countdown.stop();
				
				fiveSeconds = 5000;
				if(times>0) {
					countdown.restart();
					times--;
				}
//				fiveSeconds = 5000;
//				clock.setText(sdf.format(new Date(fiveSeconds)));
//				
			}
		}
	}
	
	public static void main(String[] args) {
		JFrame myFrame = new JFrame();
		myFrame.setLayout(new BorderLayout());
		myFrame.add(new RoleCountdown(), BorderLayout.EAST);
		myFrame.setVisible(true);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

}
