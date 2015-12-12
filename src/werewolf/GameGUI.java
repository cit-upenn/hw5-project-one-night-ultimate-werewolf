package werewolf;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class GameGUI extends JFrame  {
	
		JFrame gameFrame;
		JPanel gamePanel;
		Board board;
		Setup setup;
		CardLayout cardLayout;
		JButton button;
		
		private int numOfPlayers;
		private ArrayList<Role> roles = new ArrayList<Role>();
		private ArrayList<Role> center = new ArrayList<Role>();
		public static ArrayList<Player> players = new ArrayList<Player>();
		private int turn = 1;
		private ArrayList<Integer> choice = new ArrayList<Integer>();
		private int c, c2;
		private Role temp, temp2;
		private String name;
		

	public GameGUI () {
		gameFrame = new JFrame();
		gamePanel = new JPanel();
//		CardLayout c = new CardLayout();
		
		board = new Board();
		setup = new Setup();
		
		gameFrame.setContentPane(gamePanel);
		gameFrame.setPreferredSize(new Dimension(800, 650));
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setLayout(new CardLayout());
		gamePanel.setLayout(new CardLayout());
		
		gamePanel.add(setup, "Start Board");
		gamePanel.add(board, "Game Board");
		
		
		cardLayout = (CardLayout) gamePanel.getLayout();
		cardLayout.show(gamePanel, "Start Board");
//		cardLayout.first(gamePanel);
		
		setup.threeButton.addActionListener(new generateAL());
		setup.fourButton.addActionListener(new generateAL());
		setup.fiveButton.addActionListener(new generateAL());
		
//		board.player1.addActionListener(new revealAL());
		
		gameFrame.pack();
		gameFrame.setVisible(true);
		
	}
	
	public class generateAL implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			roles.add(new Werewolf());
			roles.add(new Werewolf());
			roles.add(new Seer());
			roles.add(new Robber());
			roles.add(new Troublemaker());
			roles.add(new Villager());
			
			if(e.getSource().equals(setup.threeButton)) {
				board.setNumPlayers(3);
			} else if(e.getSource().equals(setup.fourButton)) {
				roles.add(new Villager());
				board.setNumPlayers(4);
				board.addPlayerCardBottom();
			} else if(e.getSource().equals(setup.fiveButton)) {
				roles.add(new Villager());
				roles.add(new Villager());
				board.setNumPlayers(5);
				board.addPlayerCardBottom();
			}	
				
			//generate players & assign cards
			Collections.shuffle(roles);
			for(int i = 0; i < numOfPlayers; i++) {
				players.add(new Player());
				players.get(i).assignRole(roles.get(i));
			}
				
			//put 3 remaining cards at center of table
			for(int i = 0; i < 3; i++) {
				center.add(roles.get(numOfPlayers));
				roles.remove(numOfPlayers);
			}
				
			cardLayout.show(gamePanel, "Game Board");
		}
	}
	
//	public class revealAL implements ActionListener {
//		public void actionPerformed(ActionEvent f) {
//			
//		}
//	}
	

		
		
		public static void main(String[] args) {
			GameGUI game = new GameGUI();
			
		}
		
		
}
