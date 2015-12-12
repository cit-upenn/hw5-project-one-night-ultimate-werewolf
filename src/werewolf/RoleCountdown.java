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
	public long seconds = 10000;
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("mm:ss");
	JLabel clock = new JLabel(sdf.format(new Date(seconds)));
	Timer rcountdown;
	int turn = 1;
	int players = 2;
	String sound = "player1.wav";
	InputStream in;
	AudioStream audioStream;
//	Board b = new Board();
	
	public boolean reveal = false;
	public boolean running = true;
	
	
	public RoleCountdown() {
		setLayout(new BorderLayout());
		
		
		add(clock, BorderLayout.CENTER);
		
		rcountdown = new Timer(1000, new RoleTimer());
		rcountdown.start();
		
		setVisible(true);
	}
	
	
	public void play(String fileName) {
		try {
			in = new FileInputStream(fileName);
			audioStream = new AudioStream(in);
			AudioPlayer.player.start(audioStream);
		} catch (FileNotFoundException k) {
		    k.printStackTrace(); 
		} catch (IOException k) {
			k.printStackTrace(); 
		}
	}
	
	public class RoleTimer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			clock.setText(sdf.format(new Date(seconds)));
			seconds -= 1000;
			
			
			if(seconds == 9000) {
				play(sound);
//				b.flip(turn);
//				flip(turn);
//				reveal = true;
			} else if(seconds == 2000) {
//				b.flip(turn);
				sound = "close.wav";
				play(sound);
//				reveal = false;
			}
			
			if (seconds >= 1000) {
				clock.setText(sdf.format(new Date(seconds)));
			} else {
				clock.setText(sdf.format(new Date(seconds)));
				rcountdown.stop();
				
				seconds = 10000;
				if(turn <= players) {
					rcountdown.restart();
					switch(turn) {
						case 1:
							sound = "player2.wav";
							break;
						case 2:
							sound = "player3.wav";
							break;
						case 3:
							sound = "player4.wav";
							break;
						case 4:
							sound = "player5.wav";
							break;
					}
					
					turn++;
				} else {
					running = false;
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
