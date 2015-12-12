package werewolf;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	private int ID;
	private static int lastAssignedID = 0;
	private Role role, origRole;
	private String origRoleStr;
	
	public Player() {
		lastAssignedID++;
		ID = lastAssignedID;
		origRoleStr = "";
	}
	
	public void assignRole(Role r) {
		if(origRoleStr.equals("")) { //set originalRole only the first time of getting assigned a role (at the beginning of game)
			origRoleStr = r.getRoleStr();
			origRole = r;
		}
		role = r;
	}
	
	public ArrayList<Integer> wakeUp(int numOfPlayers, Scanner in) {
		
		return origRole.wakeUp(numOfPlayers, ID, in);
	}
	
	public int getID() {
		return ID;
	}
	
	public Role getRole() {
		return role;
	}
	
	public String getRoleStr() {
		return role.getRoleStr();
	}
	
	public String getOrigRoleStr() {
		return origRoleStr;
	}
	
	public String getImage() {
		return role.imageFile();
	}
}
