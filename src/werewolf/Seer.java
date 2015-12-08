package werewolf;

import java.util.ArrayList;
import java.util.Scanner;

public class Seer implements Role {
	private ArrayList<Integer> choice;
	private int c;
	
	@Override
	public int getID() {
		return 2;
	}
	
	@Override
	public ArrayList<Integer> wakeUp(int numOfPlayers, int ID) {
		System.out.println("Enter 1 to look at another player's card; 2 to look at any two cards in the center");
		Scanner in = new Scanner(System.in);
		c = Integer.parseInt(in.nextLine());
		choice.add(c);
		
		if(c == 1) {
			System.out.println("Which player's?");
			for(int i = 0; i < numOfPlayers; i++) {
				if(i != ID) { //print Player # options except for the robber himself
					System.out.println(i + " ");
				}
			}
			c = Integer.parseInt(in.nextLine());
			choice.add(c);
			
		} else {
			System.out.println("Which two cards?");
			System.out.println("Enter first card");
			c = Integer.parseInt(in.nextLine());
			choice.add(c);
			System.out.println("Enter second card");
			c = Integer.parseInt(in.nextLine());
			choice.add(c);
		}
		in.close();

		return choice;
	}
	
}
