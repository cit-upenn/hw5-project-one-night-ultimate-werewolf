package werewolf;

import java.util.Scanner;
import java.util.ArrayList;

public class Robber implements Role {
	private ArrayList<Integer> choice;
	private int c;
	
	@Override
	public int getID() {
		return 3;
	}
	
	@Override
	public ArrayList<Integer> wakeUp(int numOfPlayers, int ID) {
		System.out.println("Whose card would you like to switch with?");
		for(int i = 0; i < numOfPlayers; i++) {
			if(i != ID) { //print Player # options except for the robber himself
				System.out.println(i + " ");
			}
		}
		Scanner in = new Scanner(System.in);
		c = Integer.parseInt(in.nextLine());
		choice.add(c);
		in.close();
		
		return choice;
	}

}
