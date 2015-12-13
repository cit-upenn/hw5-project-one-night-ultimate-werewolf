/*
 * This class creates JPanel with Setup Screen. 
 * The screen asks user to click how many players to include for this game.
 * User can choose from 3-5 players.
 */
package werewolf;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Setup extends JPanel {
	
	JLabel topLabel;
	JLabel topLabelPanel;
	JPanel topPanel;
	JPanel centerPanel;
	JButton threeButton;
	JButton fourButton;
	JButton fiveButton;
	
	/*
	 * The constructor for this class
	 */
	public Setup () {
		
		setLayout(new BorderLayout());
		
		topLabel = new JLabel("Click the number of players.");
		topLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 35));
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.add(topLabel, BorderLayout.NORTH);
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new FlowLayout());
		
		threeButton = new JButton("3 players");
		threeButton.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		fourButton = new JButton("4 players");
		fourButton.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		fiveButton = new JButton("5 players");
		fiveButton.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		
		centerPanel.add(threeButton);
		centerPanel.add(fourButton);
		centerPanel.add(fiveButton);
		
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		
		setVisible(true);
		
	}

}
