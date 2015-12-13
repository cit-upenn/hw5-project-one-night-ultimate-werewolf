/*
 * This creates the frame which game is played.
 * It will display the Setup Board, then the Game Board after number of players are selected.
 * This class includes Button ActionListeners.
 */
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
			

	/*
	 * The constructor of this class
	 */
	public GameGUI () {
		gameFrame = new JFrame("One Night Ultimate Werewolf");
		gamePanel = new JPanel();
		
		board = new Board();
		setup = new Setup();
		
		gameFrame.setContentPane(gamePanel);
		gameFrame.setPreferredSize(new Dimension(800, 650));
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setLayout(new CardLayout());
		gamePanel.setLayout(new CardLayout());
		
		gamePanel.add(setup, "Setup Board");
		gamePanel.add(board, "Game Board");
		
		cardLayout = (CardLayout) gamePanel.getLayout();
		cardLayout.show(gamePanel, "Setup Board");
		
		setup.threeButton.addActionListener(new generateAL());
		setup.fourButton.addActionListener(new generateAL());
		setup.fiveButton.addActionListener(new generateAL());
		
		gameFrame.pack();
		gameFrame.setVisible(true);
		
	}
	
	/*
	 * ActionListener for Setup Board
	 * Adds player cards to Game Board according to number of players
	 */
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
		
}
