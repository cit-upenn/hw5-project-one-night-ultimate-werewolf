package werewolf;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Setup extends JPanel {
	
//	JFrame frame;
	JLabel topLabel;
	JLabel topLabelPanel;
	JPanel topPanel;
	JPanel centerPanel;
	JButton threeButton;
	JButton fourButton;
	JButton fiveButton;
	
	public Setup () {
//		frame = new JFrame("One Night Ultimate Werewolf");
//		frame.setLayout(new BorderLayout());
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		topLabel = new JLabel("Click the number of players.");
		topLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 35));
//		topLabelPanel.setLayout(new BorderLayout());
//		topLabelPanel.add(topLabel, BorderLayout.CENTER);
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
		
//		frame.add(topPanel, BorderLayout.NORTH);
//		frame.add(centerPanel, BorderLayout.CENTER);
		
//		frame.setSize(800, 650);
//		frame.setVisible(true);
	}
	
//	public static void main(String[] args) {
//		StartBoardGUI startgui = new StartBoardGUI();
//		
//	}
	

}
