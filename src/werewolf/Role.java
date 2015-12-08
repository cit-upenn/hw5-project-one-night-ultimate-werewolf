package werewolf;

import java.util.ArrayList;

public interface Role {
	public int getID();
	
	public ArrayList<Integer> wakeUp(int numOfPlayers, int ID);
}
