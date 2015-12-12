package werewolf;
import java.util.ArrayList;
import java.util.Scanner;

public class Villager implements Role {
	private ArrayList<Integer> choice = new ArrayList<Integer>();
	
	@Override
	public String getRoleStr() {
		return "Villager";
	}

	@Override
	public ArrayList<Integer> wakeUp(int numOfPlayers, int ID, Scanner in) {
		// TODO Auto-generated method stub
		return choice;
	}

}
