package werewolf;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.JButton;

import org.junit.Test;

public class GameGUITest {

	@Test
	public void threeButtonTest() {
		GameGUI gui = new GameGUI();
		gui.setup.threeButton.doClick();
		gui.board.startButton.doClick();
		int size = gui.board.getPlayersList().size();
		assertEquals("Size should return 3", 3, size);
	}
	
	@Test
	public void fourButtonTest() {
		GameGUI gui = new GameGUI();
		gui.setup.fourButton.doClick();
		gui.board.startButton.doClick();
		int size = gui.board.getPlayersList().size();
		assertEquals("Size should return 4", 4, size);
	}
	
	@Test
	public void fiveButtonTest() {
		GameGUI gui = new GameGUI();
		gui.setup.fiveButton.doClick();
		gui.board.startButton.doClick();
		int size = gui.board.getPlayersList().size();
		assertEquals("Size should return 5", 5, size);
	}
	
	
	@Test
	public void robberSwitchTest() {
		/**
		 * DOES NOT WORK WHEN NONE OF THE PLAYERS ARE ASSIGNED THE ROBBER.
		 * WHEN RUNNING THE TEST, IF YOU SEE ALL PLAYER BUTTONS ARE ENABLED (IN COLOR),
		 * NO ONE WAS ASSIGNED THE ROBBER DUE TO RANDOM SHUFFLING
		 * THIS IS FINE SINCE IF NO ONE IS THE ROBBER, NO ACTIONS SHOULD BE TAKEN ANYWAY
		 */
		String role = "";
		Player playerChosen;
		ArrayList<Player> pList;
		GameGUI gui = new GameGUI();	
		gui.setup.threeButton.doClick();
		gui.board.startButton.doClick();
		
		
		for (JButton p : gui.board.playerButtons) {
			p.addActionListener(gui.board.rl);
		}
		
		gui.board.enableButtons("players", "Robber");
		
		
		for (int i = 0; i < gui.board.playerButtons.size(); i++) {
			if (gui.board.playerButtons.get(i).isEnabled()) {
				gui.board.playerButtons.get(i).doClick();
				pList = gui.board.getPlayersList();
				playerChosen = pList.get(i);
				role = playerChosen.getRoleStr();
				break;
			}
		}
		assertEquals("Chosen player's new role should be robber", "Robber", role);
		
	}
	
	@Test
	public void switchClickerTest() {
		GameGUI gui = new GameGUI();
		gui.setup.threeButton.doClick();
		gui.board.startButton.doClick();

		for (JButton p: gui.board.playerButtons) {
			p.addActionListener(gui.board.tl);
		}
		
		gui.board.enableButtons("players", "Troublemaker");
		
		for (int i = 0; i < gui.board.playerButtons.size(); i++) {
			if (gui.board.playerButtons.get(i).isEnabled()) {
				gui.board.playerButtons.get(i).doClick();
			}
		}
		assertEquals("SwitchClicker should be 2", 2, gui.board.getSwitchClicker());
	}
	
	
	@Test
	public void numBoardsTest() {
		GameGUI gui = new GameGUI();
		int p = gui.gamePanel.getComponentCount();
		assertEquals("There should only be 2 boards - setup and game boards", 2, p);
	}
	
	
	
	

}
