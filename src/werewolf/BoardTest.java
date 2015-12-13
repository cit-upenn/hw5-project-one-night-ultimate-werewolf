package werewolf;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.junit.Test;

public class BoardTest {

	@Test
	public void rolesNotNull () {
		ArrayList<Role> roles = new ArrayList<Role>();
		roles.add(new Werewolf());
		assertNotNull("ArrayList can't be null after adding", roles);
	}
	
	@Test
	public void testPlayerButtonText () {
		JButton player1 = new JButton();
		player1.setText("Player 1");
		assertEquals("player1 JButton will return player1 text", "Player 1", player1.getText());
	}
	
	@Test
	public void leftPanelNotVisible () {
		JPanel leftPanel = new JPanel();
		JTextArea instruction = new JTextArea();
		leftPanel.add(instruction);
		leftPanel.setVisible(false);
		assertEquals("Instruction isVisible should be false", false, leftPanel.isVisible());
	}
	

}
