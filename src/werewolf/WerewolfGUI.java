package werewolf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


public class WerewolfGUI extends JPanel {
	
	JPanel topPanel = new JPanel();
	JPanel centerPanel = new JPanel();
	JPanel bottomPanel = new JPanel();
	JLabel screenCard = new JLabel();
	JLabel screenCard2 = new JLabel();
	RoleCard rolecard = new RoleCard();
	JButton startButton = new JButton();
	JButton player1 = new JButton();
	JButton player2;
	JButton player3;
	JButton center1;
	JButton center2;
	JButton center3;
	JButton player4;
	JButton player5;
//	RoleCountdown rc;

	public WerewolfGUI () {
		topPanel.setLayout(new FlowLayout());
		centerPanel.setLayout(new FlowLayout());
		bottomPanel.setLayout(new FlowLayout());

		player1.setIcon(new ImageIcon("werewolfcard.jpg"));
		player1.setText("Player 1");
		player1.addActionListener(new playerAL());
		topPanel.add(player1);
		
		player2 = new JButton();
		player2.setText("Player 2");
		player2.setIcon(new ImageIcon("werewolfcard.jpg"));
		player2.addActionListener(new playerAL());
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
		player3.setIcon(new ImageIcon("werewolfcard.jpg"));
		player3.addActionListener(new playerAL());
		bottomPanel.add(player3);

		setLayout(new BorderLayout());

		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		
		startButton.setText("Start Game");
		startButton.addActionListener(new startButtonAL());
		add(startButton, BorderLayout.EAST);
		
		setVisible(true);
		
	}

	private class startButtonAL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			startButton.setVisible(false);
			revealRoles();
		}
	}
	
	private class playerAL implements ActionListener {
		public void actionPerformed(ActionEvent f) {
			if (f.getSource().equals(player1)) {
				player1.setIcon(new ImageIcon("Villager-Werewolves.jpg"));
			}
		}
	}
	
//	public void addPlayerCardTop() {
//		screenCard = new JLabel(new ImageIcon("werewolfcard.jpg"));
//		topPanel.add(screenCard);
//	}
	
	public void addPlayerCardBottom(int numPlayers) {
		if (numPlayers == 4) {
			
		}
	
	}
	
	public void revealRoles() {
		add(new RoleCountdown(), BorderLayout.EAST);
	}
	

}
