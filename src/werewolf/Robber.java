/**
 * This class represents the Role of a Robber in One Night Ultimate Werewolf.
 * It implements the Role Interface.
 * 
 */
package werewolf;

import java.util.Scanner;
import java.util.ArrayList;

public class Robber implements Role {
	
//	private ArrayList<Integer> choice = new ArrayList<Integer>();
//	private int c;
	
	@Override
	public String getRoleStr() {
		return "Robber";
	}
	
	
	@Override
	public String imageFile() {
		return "Robber.jpg";
	}
	
//	@Override
//	public ArrayList<Integer> wakeUp(int numOfPlayers, int ID, Scanner in) {
//		System.out.println("Whose card would you like to switch with?");
//		for(int i = 0; i < numOfPlayers; i++) {
//			if(i+1 != ID) { //print Player # options except for the robber himself
//				System.out.println((i+1));
//			}
//		}
////		Scanner in = new Scanner(System.in);
//		c = Integer.parseInt(in.nextLine());
//		choice.add(c);
////		in.close();
//		
//		return choice;
//	}

}
