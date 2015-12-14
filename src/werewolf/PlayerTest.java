package werewolf;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

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
	
	@Test
	public void newRoleTest() {
		Player p1 = new Player();
		Role werewolf = new Werewolf();
		p1.assignRole(werewolf);
		p1.assignRole(new Seer());
		assertThat("Original role should not be updated after getting asigned a new role", p1.getRoleStr(), not(p1.getOrigRoleStr()));
	}

}
