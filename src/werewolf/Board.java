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
	
	/*
	 * Instance Variables 
	 */
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
	ArrayList<JButton> playerButtons = new ArrayList<JButton>();
	ArrayList<JButton> centerButtons = new ArrayList<JButton>();
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
	private int switchClicker = 0;
	private boolean status = true;
	Timer robberTimer;
	private boolean gameInProgress = true;
	
	seerAL sl = new seerAL();
//	SeerInitChoice sic = new SeerInitChoice();
	robberSwitch rs = new robberSwitch();
	troublemakerSwitch ts = new troublemakerSwitch();
//	String type = "";
	int flipback = 0;
	int flipback2 = 0;
	int flipback3 = 0;
	int sChoice = 0;
	
	ImageIcon back = new ImageIcon("werewolfcard.jpg");
	ImageIcon scard = new ImageIcon("Seer.jpg");
	ImageIcon wcard = new ImageIcon("WerewolfRole.jpg");
	ImageIcon rcard = new ImageIcon("Robber.jpg");
	ImageIcon tcard = new ImageIcon("Troublemaker.jpg");
	ImageIcon vcard = new ImageIcon("Villager-Werewolves.jpg");
	
	/**
	 * The constructor of this class 
	 */
	public Board () {
		
		//Set layouts of each panel
		topPanel.setLayout(new FlowLayout());
		centerPanel.setLayout(new FlowLayout());
		bottomPanel.setLayout(new FlowLayout());
		leftPanel.setLayout(new FlowLayout());
		
		//Add two cards to the top
		player1 = new JButton();
		player1.setIcon(back);
		player1.setText("Player 1");
		player1.setEnabled(false);
		topPanel.add(player1);
		playerButtons.add(player1);
		
		player2 = new JButton();
		player2.setText("Player 2");
		player2.setIcon(back);
		player2.setEnabled(false);
		topPanel.add(player2);
		playerButtons.add(player2);
		
		//Add three cards to the center 
		center1 = new JButton();
		center1.setIcon(back);
		center1.setEnabled(false);
		centerPanel.add(center1);
		centerButtons.add(center1);
		center2 = new JButton();
		center2.setIcon(back);
		center2.setEnabled(false);
		centerPanel.add(center2);
		centerButtons.add(center2);
		center3 = new JButton();
		center3.setIcon(back);
		center3.setEnabled(false);
		centerPanel.add(center3);
		centerButtons.add(center3);
		
		//Add card to the bottom
		player3 = new JButton();
		player3.setText("Player 3");
		player3.setIcon(back);
		player3.setEnabled(false);
		bottomPanel.add(player3);
		playerButtons.add(player3);

		//Set layout of and add components to main content panel
		setLayout(new BorderLayout());

		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		
		//Add start button 
		startButton.setText("Start Game");
		startButton.addActionListener(new startButtonAL());
		add(startButton, BorderLayout.EAST);
		
		//Add Seer's choice buttons
		leftPanel.setPreferredSize(new Dimension(150, 480));
		instruction.setText("Would you like to look at another player's card, or two cards in the center?");
		instruction.setLineWrap(true);
		leftPanel.add(instruction);
		seerChoice1.setText("A Player's card");
		seerChoice1.addActionListener(sl);
		leftPanel.add(seerChoice1);
		seerChoice2.setText("Two in the center");
		seerChoice2.addActionListener(sl);
		leftPanel.add(seerChoice2);
		add(leftPanel, BorderLayout.WEST);
		leftPanel.setVisible(false);
			
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
	
	private class SeerInitChoice implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	private class seerAL implements ActionListener {
		String type = "";
		int centerCount = 0;
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource().equals(seerChoice1)) {
				type = "players";
				enableButtons(type, "Seer");
				leftPanel.setVisible(false);
			} else if (e.getSource().equals(seerChoice2)) {
				type = "center";
				enableButtons(type, "Seer");
				leftPanel.setVisible(false);
			} else {
				if(type.equals("players")) {
					for(int i = 0; i < playerButtons.size(); i++) {
						if (e.getSource().equals(playerButtons.get(i))) {
							flip(i + 1);
							flipback = i + 1;
							sChoice = 1;
							enableButtons("disable", "");
							break;
						}
					}
				} else if(type.equals("center")) {
					for(int i = 0; i < centerButtons.size(); i++) {
						if (e.getSource().equals(centerButtons.get(i))) {
							if(centerCount < 2) {
								flip(i + 6);
								if(sChoice == 0) flipback = i + 6;
								else if(sChoice == 1) flipback2 = i + 6;
								sChoice++;
								centerCount++;
								if(centerCount == 2) enableButtons("disable", "");
							}
							System.out.println("sChoice " + sChoice);
							break;
						}
					}
				}
			}
			System.out.println(type);
			System.out.println("flipback " + flipback);
		}
	}
	
	private class troublemakerSwitch implements ActionListener {
		int one = 0;
		int two = 0;
		public void actionPerformed (ActionEvent e) {
			switchClicker++;
			for (int i = 0; i < playerButtons.size(); i++ ) {
				if (e.getSource().equals(playerButtons.get(i))) {
					playerButtons.get(i).setEnabled(false);
					
					if (switchClicker == 1) one = i;
					else if(switchClicker == 2) two = i;
				}
			}
			if (switchClicker == 2) {
				temp = players.get(one).getRole();
				temp2 = players.get(two).getRole();
				players.get(one).assignRole(temp2);
				players.get(two).assignRole(temp);
				enableButtons("disable", "");
			}
		}
	}
	
	public class robberSwitch implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			int one = 0;
			int two = 0;
			int j;
			for (int i = 0; i < playerButtons.size(); i++) {
				if (e.getSource().equals(playerButtons.get(i))) {
					enableButtons("disable", "");
					one = i;
					for (j = 0; j < players.size(); j++) {
						if (players.get(j).getOrigRoleStr() == "Robber") {
							two = j;
						}
					}
					temp = players.get(one).getRole();
					temp2 = players.get(two).getRole();
					players.get(one).assignRole(temp2);
					players.get(two).assignRole(temp);
					System.out.println("flip player " + (two+1));
					flip(two+1);
					flipback = two+1;					
					
				}
			}
		}
	}

	
	public void setNumPlayers(int n) {
		numPlayers = n;
	}
	
	public void flip(int player) {
		
		switch(player) {
			case 1:
				if (player1.getIcon() == back) {
					player1.setIcon(new ImageIcon(players.get(0).getImage()));
				} else {
					player1.setIcon(back);
				}
				break;
			case 2:
				if (player2.getIcon() == back) {
					player2.setIcon(new ImageIcon(players.get(1).getImage()));
				} else {
					player2.setIcon(back);
				}
				break;
			case 3:
				if (player3.getIcon() == back) {
					player3.setIcon(new ImageIcon(players.get(2).getImage()));
				} else {
					player3.setIcon(back);
				}
				break;
			case 4:
				if (player4.getIcon() == back) {
					player4.setIcon(new ImageIcon(players.get(3).getImage()));
				} else {
					player4.setIcon(back);
				}
				break;
			case 5:
				if (player5.getIcon() == back) {
					player5.setIcon(new ImageIcon(players.get(4).getImage()));
				} else {
					player5.setIcon(back);
				}
				break;
			case 6:
				if (center1.getIcon() == back) {
					center1.setIcon(new ImageIcon(center.get(0).imageFile()));
				} else {
					center1.setIcon(back);
				}
				break;
			case 7:
				if (center2.getIcon() == back) {
					center2.setIcon(new ImageIcon(center.get(1).imageFile()));
				} else {
					center2.setIcon(back);
				}
				break;
			case 8:
				if (center3.getIcon() == back) {
					center3.setIcon(new ImageIcon(center.get(2).imageFile()));
				} else {
					center3.setIcon(back);
				}
				break;
		}

	}
	
	
	public void addPlayerCardBottom() {
		if (numPlayers >= 4) {
			player4 = new JButton();
			player4.setText("Player 4");
			player4.setIcon(back);
			player4.setEnabled(false);
			bottomPanel.add(player4);
			playerButtons.add(player4);
		} 
		if (numPlayers == 5) {
			player5 = new JButton();
			player5.setText("Player 5");
			player5.setIcon(back);
			player5.setEnabled(false);
			bottomPanel.add(player5);
			playerButtons.add(player5);
		}
	}
	
	public void enableButtons(String type, String role) {
		if(type.equals("disable")) {
			for(int j = 0; j < playerButtons.size(); j++) {
				playerButtons.get(j).setEnabled(false);
			}
			for(int j = 0; j < centerButtons.size(); j++) {
				centerButtons.get(j).setEnabled(false);
			}
		} else if (type.equals("center")) {
			for(int j = 0; j < centerButtons.size(); j++) {
				centerButtons.get(j).setEnabled(true);
			}
		} else if (type.equals("players")) {
			int player = 100;
			for(int j = 0; j < players.size(); j++) {
				if(players.get(j).getOrigRoleStr().equals(role)) {
					player = j;
					System.out.println("player " + player);
				}
			}
			for(int i = 0; i < playerButtons.size(); i++) {
				if(i == player) playerButtons.get(i).getText();
				if(i != player) playerButtons.get(i).setEnabled(true);
			}
		}
	}
	
	
	public class RoleCountdown extends JPanel {
		private long beginningDur = 4000;
		private long showCardDur = 9000;
		private long roleActionDur = 20000;
		private long debateDur = 10000;
		private long seconds = beginningDur;
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("mm:ss");
		JLabel clock = new JLabel(sdf.format(new Date(seconds)));
		Timer rcountdown;
		int phase = 1;
		int timerTurn = 1;
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
					if(timerTurn == 1) {
						if(seconds == beginningDur - 1000) play(sound);
					} else {
						if(seconds == showCardDur - 1000) {
							play(sound);
							flip(timerTurn - 1);
						} else if(seconds == 2000) {
							flip(timerTurn - 1);
							sound = "close.wav";
							play(sound);
						}
					}
				} else if(phase == 2) {
					if(seconds == roleActionDur - 1000) {
						switch(timerTurn) {
							case 1: //Werewolves' turn
								play(sound);
								sound = "w_close.wav";
								break;
							case 2: //Seer's turn
								play(sound);
								leftPanel.setVisible(true);
								for(JButton b : playerButtons) {
									b.addActionListener(sl);
								}
								for(JButton b : centerButtons) {
									b.addActionListener(sl);
								}
								sound = "s_close.wav";
								break;
							case 3: //Robber's turn
								play(sound);
								enableButtons("players", "Robber");
								for(JButton b : playerButtons) {
									b.addActionListener(rs);
								}
								sound = "r_close.wav";
								break;
							case 4: //Troublemaker's turn
								play(sound);
								enableButtons("players", "Troublemaker");
								for(JButton b : playerButtons) {
									b.addActionListener(ts);
								}
								sound = "t_close.wav";
								break;
						}
					
					} else if(seconds == 2000) {
						switch(timerTurn) {
							case 1: //Werewolves' turn
								play(sound);
								break;
							case 2: //Seer's turn
								play(sound);
								leftPanel.setVisible(false);
								flip(flipback);
								if(sChoice == 2) flip(flipback2);
								enableButtons("disable", "");
								for(JButton b : playerButtons) {
									b.removeActionListener(sl);
								}
								for(JButton b : centerButtons) {
									b.removeActionListener(sl);
								}
								break;
							case 3: //Robber's turn
								play(sound);
								flip(flipback3);
								enableButtons("disable", "");
								for(JButton b : playerButtons) {
									b.removeActionListener(rs);
								}
								break;
							case 4: //Troublemaker's turn
								play(sound);
								enableButtons("disable", "");
								for(JButton b : playerButtons) {
									b.removeActionListener(ts);
								}
								break;
						}
					}
				} else if(phase == 3) {
					
					if(seconds == debateDur - 1000) {
						sound = "e_wakeup.wav";
						play(sound);
					} else if(seconds == 10000) {
						sound = "readytovote.wav";
						play(sound);
					} else if(seconds == 3000) {
						sound = "321vote.wav";
						play(sound);
					} else if(seconds == 0) {
						for(int i = 0; i < players.size(); i++) {
							flip(i + 1);
						}
						for(int i = 6; i < center.size() + 6; i++) {
							flip(i);
						}
					}
				}
				
				if (seconds >= 1000) {
					clock.setText(sdf.format(new Date(seconds)));
				} else {
					clock.setText(sdf.format(new Date(seconds)));
					rcountdown.stop();
					
					if(phase == 1) {
						seconds = showCardDur;
						if(timerTurn - 1 <= numPlayers ) {
							rcountdown.restart();
							switch(timerTurn) {
								case 1:
									sound = "player1.wav";
									break;
								case 2:
									sound = "player2.wav";
									break;
								case 3:
									sound = "player3.wav";
									break;
								case 4:
									sound = "player4.wav";
									break;
								case 5:
									sound = "player5.wav";
									break;
							}
						
							timerTurn++;
							if(timerTurn - 1 > numPlayers) {
								timerTurn = 1;
								seconds = roleActionDur;
								phase++;
								sound = "w_wakeup.wav";
							}
						}
					} else if(phase == 2) {
						seconds = roleActionDur;
						rcountdown.restart();
						if(timerTurn - 1 <= numPlayers ) {
							switch(timerTurn) {
								case 1:
									sound = "s_wakeup.wav";
									break;
								case 2:
									sound = "r_wakeup.wav";
									break;
								case 3:
									sound = "t_wakeup.wav";
									break;
							}
							timerTurn++;
							if(timerTurn - 1 > 3) {
								timerTurn = 1;
								seconds = debateDur;
								phase++;
								
							}
						}
					}
					
					
				}
			}
		}

	}

}
