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
			

	public GameGUI () {
		gameFrame = new JFrame();
		gamePanel = new JPanel();
		
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
			
			if(e.getSource().equals(setup.threeButton)) {
				board.setNumPlayers(3);
			} else if(e.getSource().equals(setup.fourButton)) {
				board.setNumPlayers(4);
				board.addPlayerCardBottom();
			} else if(e.getSource().equals(setup.fiveButton)) {
				board.setNumPlayers(5);
				board.addPlayerCardBottom();
			}	
				
			cardLayout.show(gamePanel, "Game Board");
		}
	}	

		
		
		public static void main(String[] args) {
			GameGUI game = new GameGUI();
			
		}
		
		
}
