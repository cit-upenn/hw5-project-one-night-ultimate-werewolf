package werewolf;

import javax.swing.*;
import javax.swing.Timer;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class Board extends JPanel {
	
	JPanel topPanel = new JPanel();
	JPanel centerPanel = new JPanel();
	JPanel bottomPanel = new JPanel();
	JPanel leftPanel = new JPanel();
	JTextArea instruction = new JTextArea(5,12);
//	JLabel screenCard = new JLabel();
//	JLabel screenCard2 = new JLabel();
	RoleCard rolecard = new RoleCard();
	JButton startButton = new JButton();
	JButton player1, player2, player3, player4, player5;
	JButton center1, center2, center3;
	JButton seerChoice1 = new JButton();
	JButton seerChoice2 = new JButton();
	RoleCountdown rc;
	private boolean flip = false;
	private int player, numPlayers;
	
	private ArrayList<Role> roles = new ArrayList<Role>();
	private ArrayList<Role> center = new ArrayList<Role>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private int turnDuringGame = 1;
	private ArrayList<Integer> choice = new ArrayList<Integer>();
	private int c, c2;
	private Role temp, temp2;
	private String name;
	
	
	public Board () {
		topPanel.setLayout(new FlowLayout());
		centerPanel.setLayout(new FlowLayout());
		bottomPanel.setLayout(new FlowLayout());
		leftPanel.setLayout(new FlowLayout());
		
		player1 = new JButton();
		player1.setIcon(new ImageIcon("werewolfcard.jpg"));
		player1.setText("Player 1");
		player1.addActionListener(new playerAL());
//		player1.setDisabledIcon(new ImageIcon("werewolfcard.jpg"));
		player1.setEnabled(false);
		topPanel.add(player1);
		
		player2 = new JButton();
		player2.setText("Player 2");
		player2.setIcon(new ImageIcon("werewolfcard.jpg"));
		player2.addActionListener(new playerAL());
		player2.setEnabled(false);
		topPanel.add(player2);
		
		center1 = new JButton();
		center1.setIcon(new ImageIcon("werewolfcard.jpg"));
		center1.addActionListener(new playerAL());
		centerPanel.add(center1);
		center2 = new JButton();
		center2.setIcon(new ImageIcon("werewolfcard.jpg"));
		center2.addActionListener(new playerAL());
		centerPanel.add(center2);
		center3 = new JButton();
		center3.setIcon(new ImageIcon("werewolfcard.jpg"));
		center3.addActionListener(new playerAL());
		centerPanel.add(center3);
		
		player3 = new JButton();
		player3.setText("Player 3");
		player3.setIcon(new ImageIcon("werewolfcard.jpg"));
		player3.addActionListener(new playerAL());
		player3.setEnabled(false);
		bottomPanel.add(player3);

		setLayout(new BorderLayout());

		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		
		startButton.setText("Start Game");
		startButton.addActionListener(new startButtonAL());
		add(startButton, BorderLayout.EAST);
		
		leftPanel.setPreferredSize(new Dimension(150, 480));
		instruction.setText("Would you like to look at another player's card, or two cards in the center?");
		instruction.setLineWrap(true);
		leftPanel.add(instruction);
		seerChoice1.setText("A Player's card");
//		seerChoice1.addActionListener(new );
		leftPanel.add(seerChoice1);
		seerChoice2.setText("Two in the center");
//		seerChoice2.addActionListener(new );
		leftPanel.add(seerChoice2);
		add(leftPanel, BorderLayout.WEST);
//		instruction.setVisible(false);
		
	}

	private class startButtonAL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			roles.add(new Werewolf());
			roles.add(new Werewolf());
			roles.add(new Seer());
			roles.add(new Robber());
			roles.add(new Troublemaker());
			roles.add(new Villager());
			if(numPlayers == 4) {
				roles.add(new Villager());
			} else if(numPlayers == 5) {
				roles.add(new Villager());
				roles.add(new Villager());
			}
			
			//generate players & assign cards
			Collections.shuffle(roles);
			for(int i = 0; i < numPlayers; i++) {
				players.add(new Player());
				players.get(i).assignRole(roles.get(i));
			}
			System.out.println(players.size());
				
			//put 3 remaining cards at center of table
			for(int i = 0; i < 3; i++) {
				center.add(roles.get(numPlayers));
				roles.remove(numPlayers);
			}
			
			startButton.setVisible(false);
			rc = new RoleCountdown();
			add(rc, BorderLayout.EAST);
		}
	}
	
	private class playerAL implements ActionListener {
		public void actionPerformed(ActionEvent f) {
			if (f.getSource().equals(player1)) {
				player = 1;
			} else if (f.getSource().equals(player2)) {
				player = 2;
			} else if (f.getSource().equals(player3)) {
				player = 3;
			} else if (f.getSource().equals(player4)) {
				player = 4;
			} else if (f.getSource().equals(player5)) {
				player = 5;
			} else if (f.getSource().equals(center1)) {
				player = 6;
			} else if (f.getSource().equals(center2)) {
				player = 7;
			} else if (f.getSource().equals(center3)) {
				player = 8;
			}
			flip(player);
		}
	}
	
//	public void addPlayerCardTop() {
//		screenCard = new JLabel(new ImageIcon("werewolfcard.jpg"));
//		topPanel.add(screenCard);
//	}
	
	public void setNumPlayers(int n) {
		numPlayers = n;
	}
	
	public void flip(int player) {
		flip = !flip;
		
		switch(player) {
			case 1:
				if (flip) player1.setIcon(new ImageIcon(players.get(0).getImage()));
				else player1.setIcon(new ImageIcon("werewolfcard.jpg"));
				break;
			case 2:
				if (flip) player2.setIcon(new ImageIcon(players.get(1).getImage()));
				else player2.setIcon(new ImageIcon("werewolfcard.jpg"));
				break;
			case 3:
				if (flip) player3.setIcon(new ImageIcon(players.get(2).getImage()));
				else player3.setIcon(new ImageIcon("werewolfcard.jpg"));
				break;
			case 4:
				if (flip) player4.setIcon(new ImageIcon(players.get(3).getImage()));
				else player4.setIcon(new ImageIcon("werewolfcard.jpg"));
				break;
			case 5:
				if (flip) player5.setIcon(new ImageIcon(players.get(4).getImage()));
				else player5.setIcon(new ImageIcon("werewolfcard.jpg"));
				break;
			case 6:
				if (flip) center1.setIcon(new ImageIcon(center.get(0).imageFile()));
				else center1.setIcon(new ImageIcon("werewolfcard.jpg"));
				break;
			case 7:
				if (flip) center2.setIcon(new ImageIcon(center.get(1).imageFile()));
				else center2.setIcon(new ImageIcon("werewolfcard.jpg"));
				break;
			case 8:
				if (flip) center3.setIcon(new ImageIcon(center.get(2).imageFile()));
				else center3.setIcon(new ImageIcon("werewolfcard.jpg"));
				break;
		}

	}
	
	
	public void addPlayerCardBottom() {
		if (numPlayers >= 4) {
			player4 = new JButton();
			player4.setText("Player 4");
			player4.setIcon(new ImageIcon("werewolfcard.jpg"));
			player4.addActionListener(new playerAL());
			player4.setEnabled(false);
			bottomPanel.add(player4);
		} 
		if (numPlayers == 5) {
			player5 = new JButton();
			player5.setText("Player 5");
			player5.setIcon(new ImageIcon("werewolfcard.jpg"));
			player5.addActionListener(new playerAL());
			player5.setEnabled(false);
			bottomPanel.add(player5);
		}
	
	}
	
	
	public class RoleCountdown extends JPanel {
		private long begin = 4000;
		private long showCard = 9000;
		private long seconds = begin;
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("mm:ss");
		JLabel clock = new JLabel(sdf.format(new Date(seconds)));
		Timer rcountdown;
		int phase = 1;
		int turnPreGame = 0;
		int players = numPlayers;
		String sound = "everyone.wav";
		InputStream in;
		AudioStream audioStream;
		
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
				
				if(phase == 1) {
					if(turnPreGame == 0) {
						if(seconds == begin - 1000) play(sound);
					} else {
						if(seconds == showCard - 1000) {
							play(sound);
							flip(turnPreGame);
						} else if(seconds == 2000) {
							flip(turnPreGame);
							sound = "close.wav";
							play(sound);
						}
					}
				}
//				} else if(phase == 2) {
//					if(seconds == ) {
//						
//					}
//				} else if(phase == 3) {
//					
//				}
				
				if (seconds >= 1000) {
					clock.setText(sdf.format(new Date(seconds)));
				} else {
					clock.setText(sdf.format(new Date(seconds)));
					rcountdown.stop();
					
					seconds = showCard;
					if(turnPreGame <= players - 1) {
						rcountdown.restart();
						switch(turnPreGame) {
							case 0:
								sound = "player1.wav";
								break;
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
						
						turnPreGame++;
					}
					
				}
			}
		}

	}
	
	

}
