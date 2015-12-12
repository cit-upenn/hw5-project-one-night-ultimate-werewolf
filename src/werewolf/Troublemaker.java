package werewolf;

import java.util.ArrayList;
import java.util.Scanner;

public class Troublemaker implements Role {
	private ArrayList<Integer> choice = new ArrayList<Integer>();
	private int c;

	@Override
	public String getRoleStr() {
		return "Troublemaker";
	}
	
	@Override
	public String imageFile() {
		return "Troublemaker.jpg";
	}
	
	@Override
	public ArrayList<Integer> wakeUp(int numOfPlayers, int ID, Scanner in) {
		System.out.println("Whose cards would you like to swap?");
		for(int i = 0; i < numOfPlayers; i++) {
			if(i+1 != ID) { //print Player # options except for the robber himself
				System.out.println((i+1));
			}
		}
//		Scanner in = new Scanner(System.in);
		System.out.println("Enter first card");
		c = Integer.parseInt(in.nextLine());
		choice.add(c);
		System.out.println("Enter second card");
		c = Integer.parseInt(in.nextLine());
		choice.add(c);
//		in.close();
		
		return choice;
		
	}

}
