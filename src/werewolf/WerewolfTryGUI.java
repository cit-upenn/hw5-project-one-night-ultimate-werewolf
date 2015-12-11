package werewolf;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class WerewolfTryGUI extends JFrame {

//	StartBoardGUI sb = new StartBoardGUI();
	WerewolfGUI w = new WerewolfGUI();
	
	public WerewolfTryGUI () {
		setPreferredSize(new Dimension(800,650));
		setVisible(true);
		
	}
	
	public void displayGameBoard() {
		w.setVisible(true);
		add(w);
		setContentPane(w);
		
	}
	
	public static void main(String[] args) {
		WerewolfTryGUI wgui = new WerewolfTryGUI();
		wgui.displayGameBoard();
	}
	
	
}
