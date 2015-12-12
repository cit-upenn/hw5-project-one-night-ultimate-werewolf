package werewolf;

import javax.swing.*;
import java.awt.*;
import java.util.*;


public class WerewolfGUI extends JPanel {
	JPanel topPanel = new JPanel();
	JPanel centerPanel = new JPanel();
	JPanel bottomPanel = new JPanel();
	JLabel screenCard = new JLabel();
	JLabel screenCard2 = new JLabel();
	RoleCard rolecard = new RoleCard();
//	RoleCountdown rc;

	public WerewolfGUI () {
		topPanel.setLayout(new FlowLayout());
		centerPanel.setLayout(new FlowLayout());
		bottomPanel.setLayout(new FlowLayout());

		screenCard.setIcon(new ImageIcon("werewolfcard.jpg"));
		topPanel.add(screenCard);
		screenCard = new JLabel(new ImageIcon("werewolfcard.jpg"));
		topPanel.add(screenCard);
		
		screenCard = new JLabel(new ImageIcon("werewolfcard.jpg"));
		centerPanel.add(screenCard); //figure out how to center these cards
		screenCard = new JLabel(new ImageIcon("werewolfcard.jpg"));
		centerPanel.add(screenCard);
		screenCard = new JLabel(new ImageIcon("werewolfcard.jpg"));
		centerPanel.add(screenCard);
		
//		centerPanel.add(screenCard, BorderLayout.WEST);
		screenCard = new JLabel(new ImageIcon("werewolfcard.jpg"));
//		centerPanel.add(screenCard, BorderLayout.CENTER);
//		screenCard = new JLabel(new ImageIcon("/Users/gracelee/Desktop/werewolfcard.jpg"));
//		centerPanel.add(screenCard, BorderLayout.EAST);
		bottomPanel.add(screenCard);
//		centerPanel.add(screenCard2);
//		bottomPanel.add(screenCard);
		
//		topPanel.add(rolecard);
//		centerPanel.add(rolecard);
//		bottomPanel.add(rolecard);

		setLayout(new BorderLayout());

		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		
		revealRoles();
		
		setVisible(true);
		
		
	}
	
	public void addPlayerCardTop() {
		screenCard = new JLabel(new ImageIcon("werewolfcard.jpg"));
		topPanel.add(screenCard);
	}
	
	public void addPlayerCardBottom(int numPlayers) {
		for(int i = 0; i < numPlayers - 3; i++) {
			screenCard = new JLabel(new ImageIcon("werewolfcard.jpg"));
			bottomPanel.add(screenCard);
		}
	
	}
	
	public void revealRoles() {
		add(new RoleCountdown(), BorderLayout.EAST);
	}
	
	
//	public void display () {
//		JFrame myFrame = new JFrame("One Night Ultimate Werewolf");
//		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//   		myFrame.setContentPane(this);
//    	myFrame.setPreferredSize(new Dimension(800,650));
//
//    	myFrame.pack();
//    	myFrame.setVisible(true);
//	}
//
//	public static void main(String[] args) {
//		WerewolfGUI gui = new WerewolfGUI();
//		gui.display();
//	}
}
