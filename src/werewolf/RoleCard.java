package werewolf;

import javax.swing.*;
import java.awt.*;

public class RoleCard extends JLabel{
	
	JLabel gameCard = new JLabel();
	
	public RoleCard() {
		
//		JLabel gameCard1 = new JLabel();
		gameCard.setIcon(new ImageIcon("/Users/gracelee/Desktop/werewolfcard.jpg"));
	}
	
	public void switchRoleCard() {
		gameCard.setIcon(new ImageIcon("/Users/gracelee/Desktop/Villager-Werewolves.jpg"));
	}
	
}
