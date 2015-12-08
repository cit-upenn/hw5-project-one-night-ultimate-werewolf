package werewolf;

import java.util.ArrayList;

public class Werewolf implements Role {
	private ArrayList<Integer> choice;
	
	@Override
	public int getID() {
		return 1;
	}

	@Override
	public ArrayList<Integer> wakeUp(int numOfPlayers, int ID) {
		// TODO Auto-generated method stub
		return choice;
	}

}
