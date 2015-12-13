/**
 * This class displays the interactive Game Board of One Night Ultimate Werewolf.
 * Users carry out Night and Day Phases on this board.
 */
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

	private JPanel topPanel;
	private JPanel centerPanel;
	private JPanel bottomPanel;
	private JPanel leftPanel;

	JTextArea instruction;
	JButton startButton;
	JButton player1, player2, player3, player4, player5;
	JButton center1, center2, center3;
	JButton seerChoice1;
	JButton seerChoice2;
	ArrayList<JButton> playerButtons;
	ArrayList<JButton> centerButtons;

	private ArrayList<Role> roles;
	private ArrayList<Role> center;
	private ArrayList<Player> players;
	private Role temp, temp2;

	RoleCountdown rc;
	private int numPlayers;
	private int switchClicker = 0;
	int flipback = 0;
	int flipback2 = 0;
	int flipback3 = 0;
	int sChoice = 0;

	ImageIcon back;

	SeerAL sl = new SeerAL();
	RobberAL rl = new RobberAL();
	TroublemakerAL tl = new TroublemakerAL();
	
	
	/**
	 * Gets players ArrayList
	 * @return players ArrayList
	 * Method was created for Unit Testing 
	 */
	public ArrayList<Player> getPlayersList () {
		return players;
	}

	/**
	 * The constructor of this class 
	 */
	public Board () {

		//Initialize Instance Variables
		topPanel = new JPanel();
		centerPanel = new JPanel();
		bottomPanel = new JPanel();
		leftPanel = new JPanel();

		instruction = new JTextArea(5,12);
		startButton = new JButton();
		playerButtons = new ArrayList<JButton>();
		centerButtons = new ArrayList<JButton>();
		seerChoice1 = new JButton();
		seerChoice2 = new JButton();

		roles = new ArrayList<Role>();
		center = new ArrayList<Role>();
		players = new ArrayList<Player>();

		back = new ImageIcon("werewolfcard.jpg");

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

		//Add Seer's initial choice buttons to left of frame
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

	/**
	 * An ActionListener that generates roles and players in the game
	 * Event occurs when Start Button is clicked
	 */
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

			//generate players & assign roles randomly
			Collections.shuffle(roles);
			for(int i = 0; i < numPlayers; i++) {
				players.add(new Player());
				players.get(i).assignRole(roles.get(i));
			}

			//put 3 remaining roles at center 
			for(int i = 0; i < 3; i++) {
				center.add(roles.get(numPlayers));
				roles.remove(numPlayers);
			}

			//call RoleCountdown to start game play
			rc = new RoleCountdown();

			//add Start Button to frame's content panel
			startButton.setVisible(false);
			add(rc, BorderLayout.EAST);
		}
	}

	/**
	 * ActionListener that carries out Seer's actions during game play
	 * Actions include choosing one of the two buttons in instructions,
	 * and then choosing which cards to flip
	 */
	private class SeerAL implements ActionListener {
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
							break;
						}
					}
				}
			}
		}
	}

	/**
	 * An ActionListener that switches two cards that TroubleMaker choose
	 *
	 */
	private class TroublemakerAL implements ActionListener {
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

	/**
	 * An ActionListener that switches Robber's Card with card of their choice, 
	 * then flips Robber's new card.
	 *
	 */

	public class RobberAL implements ActionListener {
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
					flip(two+1);
					flipback3 = two+1;					

				}
			}
		}
	}

	/**
	 * Sets the number of players playing in current game
	 * @param n number of current players
	 */
	public void setNumPlayers(int n) {
		numPlayers = n;
	}
	
	/**
	 * Gets the number of players playing in current game
	 * @return number of current players
	 * Method created for Unit Testing
	 */
	public int getNumPlayers() {
		return numPlayers;
	}

	/**
	 * Flips the card to show the other side
	 * @param player the "index" of the player whose card to flip
	 */
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


	/**
	 * Adds new player card to the bottom of the Game Board Panel
	 * Also adds player button to ArrayList of player buttons
	 */
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

	/**
	 * Enables or disables certain buttons depending on parameters
	 * @param type name of group of buttons to disenable/enable
	 * @param role role that specifies each role in game whose buttons to disenable/enable
	 */
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
				}
			}
			for(int i = 0; i < playerButtons.size(); i++) {
				if(i == player) playerButtons.get(i).getText();
				if(i != player) playerButtons.get(i).setEnabled(true);
			}
		}
	}
	

	/**
	 * This class is a panel that holds the timer for the entire game.
	 * Sequences of events of Night and Day Phases are regulated by this timer.
	 *
	 */
	public class RoleCountdown extends JPanel {
		private long beginningDur = 4000;
		private long showCardDur = 9000;
		private long roleActionDur = 20000;
		private long debateDur = 210000;
		private long seconds = beginningDur;
		java.text.SimpleDateFormat sdf;
		private JLabel clock;
		private Timer rcountdown;
		private int phase = 1;
		private int timerTurn = 1;
		private String sound = "everyone.wav";
		private InputStream in;
		private AudioStream audioStream;

		/*
		 * The constructor for this class
		 */
		public RoleCountdown() {
			//Initialize Instance Variables
			sdf = new java.text.SimpleDateFormat("mm:ss");
			clock = new JLabel(sdf.format(new Date(seconds)));

			// Set layout of panel and add clock to display time
			setLayout(new BorderLayout());
			add(clock, BorderLayout.CENTER);

			// Create new timer with RoleTimer() as ActionEvent
			// Start timer
			rcountdown = new Timer(1000, new RoleTimer());
			rcountdown.start();
			setVisible(true);
		}

		/**
		 * Plays sound clip
		 * @param fileName name of sound clip file to play
		 */
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
		

		/**
		 * 
		 * ActionListener that carries out sequence of events of Night and Day Phases
		 *
		 */
		public class RoleTimer implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				clock.setText(sdf.format(new Date(seconds)));
				seconds -= 1000;

				//Carries out sequence of revealing every player's card to themselves
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

					// Carries out Night Phase 
					// Plays sound clips of instructions for each role's turn
					// Adds Seer, Robber, and Troublemaker ActionListeners accordingly
					// Enables certain cards to be clicked according to role
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
								b.addActionListener(rl);
							}
							sound = "r_close.wav";
							break;
						case 4: //Troublemaker's turn
							play(sound);
							enableButtons("players", "Troublemaker");
							for(JButton b : playerButtons) {
								b.addActionListener(tl);
							}
							sound = "t_close.wav";
							break;
						}
						// Plays sound clips of instructions for current player
						// Removes Seer, Robber, and Troublemaker ActionListeners accordingly
						// Disables buttons towards end of current player's turn
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
								b.removeActionListener(rl);
							}
							break;
						case 4: //Troublemaker's turn
							play(sound);
							enableButtons("disable", "");
							for(JButton b : playerButtons) {
								b.removeActionListener(tl);
							}
							break;
						}
					}
					// Carries out Day Phase 
					// Plays sound clips for start and end of Day Phase 
					// Reveal all cards at end of Day Phase 
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

					// Assigns which sound clips to play depending on whose turn it is
					// These sound clips instruct current player to open their eyes
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
						//Assigns which sound clips to play depending on current role
						// These sound clips instruct role to carry out their Night Phase action
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
