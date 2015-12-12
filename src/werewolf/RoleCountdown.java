package werewolf;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.io.*;
import sun.audio.*;

public class RoleCountdown extends JPanel {
	private long fiveSeconds = 10000;
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("mm:ss");
	JLabel clock = new JLabel(sdf.format(new Date(fiveSeconds)));
	Timer rcountdown;
	int times = 2;
//	AudioStream audioStream;
	
	
	
	public RoleCountdown() {
		setLayout(new BorderLayout());
		
		
		add(clock, BorderLayout.CENTER);
		
		rcountdown = new Timer(1000, new RoleTimer());
		rcountdown.start();
		
		setVisible(true);
		
		
		 
	}
	
	public class RoleTimer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			clock.setText(sdf.format(new Date(fiveSeconds)));
			fiveSeconds -= 1000;
			
			
			if(fiveSeconds == 9000) {
				try {
					String f1 = "player1.wav";
					InputStream in = new FileInputStream(f1);
					AudioStream audioStream = new AudioStream(in);
					AudioPlayer.player.start(audioStream);
				} catch (FileNotFoundException k) {
				    k.printStackTrace(); 
				} catch (IOException k) {
					k.printStackTrace(); 
				}
			}
			
			if (fiveSeconds >= 1000) {
				clock.setText(sdf.format(new Date(fiveSeconds)));
			} else {
				clock.setText(sdf.format(new Date(fiveSeconds)));
				rcountdown.stop();
				
				fiveSeconds = 10000;
				if(times>0) {
					rcountdown.restart();
					times--;
				}
//				fiveSeconds = 5000;
//				clock.setText(sdf.format(new Date(fiveSeconds)));
				
			}
		}
	}
	
//	public static void main(String[] args) {
//		JFrame myFrame = new JFrame();
//		myFrame.setSize(new Dimension(800, 650));
//		myFrame.setLayout(new BorderLayout());
//		myFrame.add(new RoleCountdown(), BorderLayout.EAST);
//		myFrame.setVisible(true);
//		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}
	

}
