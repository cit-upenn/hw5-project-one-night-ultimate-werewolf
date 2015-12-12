package werewolf;

import java.util.ArrayList;
import java.util.Scanner;

public interface Role {
	public String getRoleStr();
	
	public String imageFile();
	
	public ArrayList<Integer> wakeUp(int numOfPlayers, int ID, Scanner in);
}