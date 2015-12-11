package werewolf;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class StartBoardGUI extends JPanel {
	
	JFrame frame;
	JLabel topLabel;
	JPanel topPanel;
	JPanel centerPanel;
	JButton threeButton;
	JButton fourButton;
	JButton fiveButton;
	
	public StartBoardGUI () {
		frame = new JFrame("One Night Ultimate Werewolf");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		topLabel = new JLabel("Click the number of players.");
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.add(topLabel, BorderLayout.CENTER);
		centerPanel = new JPanel();
		centerPanel.setLayout(new FlowLayout());
		
		threeButton = new JButton("3 players");
		fourButton = new JButton("4 players");
		fiveButton = new JButton("5 players");
		
		centerPanel.add(threeButton);
		centerPanel.add(fourButton);
		centerPanel.add(fiveButton);
		
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(centerPanel, BorderLayout.CENTER);
		
		frame.setSize(800, 650);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		StartBoardGUI startgui = new StartBoardGUI();
		
	}
	

}
