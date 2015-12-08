package werewolf;

import java.util.ArrayList;

public class Player {
	private int ID;
	private static int lastAssignedID = 0;
	private Role role;
	
	public Player() {
		lastAssignedID++;
		ID = lastAssignedID;
	}
	
	public void assignRole(Role r) {
		role = r;
	}
	
	public ArrayList<Integer> wakeUp(int numOfPlayers) {
		
		return role.wakeUp(numOfPlayers, ID);
	}
	
	public int getID() {
		return ID;
	}
	
	public Role getRole() {
		return role;
	}
}
