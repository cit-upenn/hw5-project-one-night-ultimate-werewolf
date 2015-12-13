package werewolf;

import static org.junit.Assert.*;

import javax.swing.JButton;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void assignRoleTest() {
		Player p1 = new Player();
		Role werewolf = new Werewolf();
		p1.assignRole(werewolf);
		assertEquals("Player should be a Werewolf", p1.getImage(), "WerewolfRole.jpg");
	}

}
