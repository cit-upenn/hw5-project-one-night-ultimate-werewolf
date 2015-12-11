package werewolf;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class WerewolfTryGUI {

////	StartBoardGUI sb = new StartBoardGUI();
//	WerewolfGUI w = new WerewolfGUI();
//	
//	public WerewolfTryGUI () {
//		setPreferredSize(new Dimension(800,650));
//		setVisible(true);
//		
//	}
//	
//	public void displayGameBoard() {
//		w.setVisible(true);
//		add(w);
//		setContentPane(w);
//		
//	}
//	
//	public static void main(String[] args) {
//		WerewolfTryGUI wgui = new WerewolfTryGUI();
//		wgui.displayGameBoard();
//	}
	
	public static void main(String[] args) {
		JFrame gameFrame = new JFrame();
		WerewolfGUI w = new WerewolfGUI();
		StartBoardGUI sb = new StartBoardGUI();
		
		gameFrame.setPreferredSize(new Dimension(800, 650));
		gameFrame.setLayout(new CardLayout());
		
		gameFrame.add(sb);
		gameFrame.setContentPane(sb);
		
//		gameFrame.add(w);
//		gameFrame.setContentPane(w);
		
		gameFrame.pack();
		gameFrame.setVisible(true);
		
		
		
	}
	
	
}
