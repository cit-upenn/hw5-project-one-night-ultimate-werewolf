/*
 * This is an interface for roles in One Night Ultimate Werewolf.
 * 
 */
package werewolf;

import java.util.ArrayList;
import java.util.Scanner;

public interface Role {
	
	/*
	 * Get string of name of Role
	 */
	public String getRoleStr();
	
	/*
	 * Get imageFile of Role
	 */
	public String imageFile();
	
	
//	public ArrayList<Integer> wakeUp(int numOfPlayers, int ID, Scanner in);
}
