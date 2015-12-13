/**
 * This class represents a Role of a Villager in One Night Ultimate Werewolf.
 * It implements the Role Interface.
 */
package werewolf;
import java.util.ArrayList;
import java.util.Scanner;

public class Villager implements Role {
//	private ArrayList<Integer> choice = new ArrayList<Integer>();
	
	@Override
	public String getRoleStr() {
		return "Villager";
	}
	
	@Override
	public String imageFile() {
		return "Villager-Werewolves.jpg";
	}

//	@Override
//	public ArrayList<Integer> wakeUp(int numOfPlayers, int ID, Scanner in) {
//		// TODO Auto-generated method stub
//		return choice;
//	}

}
