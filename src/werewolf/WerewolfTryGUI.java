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

public class WerewolfTryGUI extends JFrame implements ActionListener {
	
		JFrame gameFrame;
		JPanel gamePanel;
		WerewolfGUI w;
		StartBoardGUI sb;
		CardLayout cardLayout;
		JButton button;
		
		private int numOfPlayers;
		private ArrayList<Role> roles = new ArrayList<Role>();
		private ArrayList<Role> center = new ArrayList<Role>();
		private ArrayList<Player> players = new ArrayList<Player>();
		private int turn = 1;
		private ArrayList<Integer> choice = new ArrayList<Integer>();
		private int c, c2;
		private Role temp, temp2;
		private String name;
		

	public WerewolfTryGUI () {
		gameFrame = new JFrame();
		gamePanel = new JPanel();
//		CardLayout c = new CardLayout();
		
		w = new WerewolfGUI();
		sb = new StartBoardGUI();
		
		gameFrame.setContentPane(gamePanel);
		gameFrame.setPreferredSize(new Dimension(800, 650));
		gameFrame.setLayout(new CardLayout());
		gamePanel.setLayout(new CardLayout());
		
		gamePanel.add(sb, "Start Board");
		gamePanel.add(w, "Game Board");
		
		
		cardLayout = (CardLayout) gamePanel.getLayout();
		cardLayout.show(gamePanel, "Start Board");
//		cardLayout.first(gamePanel);
		
		sb.threeButton.addActionListener(this);
		sb.fourButton.addActionListener(this);
		sb.fiveButton.addActionListener(this);
		
		gameFrame.pack();
		gameFrame.setVisible(true);
		
	}
		@Override
		public void actionPerformed(ActionEvent e) {
			roles.add(new Werewolf());
			roles.add(new Werewolf());
			roles.add(new Seer());
			roles.add(new Robber());
			roles.add(new Troublemaker());
			roles.add(new Villager());
			
			if(e.getSource().equals(sb.fourButton)) {
				roles.add(new Villager());
				
			} else if(e.getSource().equals(sb.fiveButton)) {
				roles.add(new Villager());
				roles.add(new Villager());
			}
			cardLayout.show(gamePanel, "Game Board");
		}
		
		public static void main(String[] args) {
			WerewolfTryGUI grace = new WerewolfTryGUI();
		}
		
		

	

	
	
}
