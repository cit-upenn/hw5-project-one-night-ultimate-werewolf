package werewolf;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Game {
	// instance variables
	private int numOfPlayers;
	private ArrayList<Role> roles = new ArrayList<Role>();
	private ArrayList<Role> center = new ArrayList<Role>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private int turn = 1;
	private ArrayList<Integer> choice = new ArrayList<Integer>();
	private int c, c2;
	private Role temp, temp2;
	private String name;
	
	// methods
	public void play() {
		//title screen
//		timer(5, 0);
		
		/**
		 * SETUP
		 */
		//select number of players
		System.out.println("Enter number of players from 3 to 5");
		System.out.println("and decide what number you are");
		Scanner in = new Scanner(System.in);
		numOfPlayers = Integer.parseInt(in.nextLine());
		
		
		//generate roles
		roles.add(new Werewolf());
		roles.add(new Werewolf());
		roles.add(new Seer());
		roles.add(new Robber());
		roles.add(new Troublemaker());
		roles.add(new Villager());
		if(numOfPlayers == 4) {
			roles.add(new Villager());
		} else if(numOfPlayers == 5) {
			roles.add(new Villager());
			roles.add(new Villager());
		}
		
		//generate players & assign cards
		Collections.shuffle(roles);
		for(int i = 0; i < numOfPlayers; i++) {
			players.add(new Player());
			players.get(i).assignRole(roles.get(i));
		}

		
		//put 3 remaining cards at center of table
		for(int i = 0; i < 3; i++) {
			center.add(roles.get(numOfPlayers));
			roles.remove(numOfPlayers);
		}
		
		System.out.println("Everyone close your eyes");
//		timer(3,0);
		
		for(int i = 0; i < players.size(); i++) {
			System.out.println("Player " + (i+1) + ", open your eyes. this is your card.");
			System.out.println(players.get(i).getRoleStr());
		}
		
		
		
		while(turn != 0) {
			switch(turn) {
				case 1:
					System.out.println("Werewolves, wake up and look for other werewolves.");
//					timer(10,0);
					turn = 2;
					break;
				case 2:
					System.out.println("Werewolves, close your eyes. Seer, wake up. You may look at another player’s card or two of the center cards");
//					timer(10,0);
					for(int i = 0; i < players.size(); i++) {
						name = players.get(i).getOrigRoleStr();
						if(name.equals("Seer")) {
							//get the player's input
							choice = players.get(i).wakeUp(numOfPlayers, in);
							
							if(choice.get(0) == 1) { //if looking at one player's card
								c = choice.get(1);
								name = players.get(c - 1).getRoleStr();
								System.out.println("Player " + c + "is " + name);
							} else { //if looking at two cards in the center
								c = choice.get(1);
								name = center.get(c - 1).getRoleStr();
								System.out.println(name);
								c2 = choice.get(2);
								name = center.get(c2 - 1).getRoleStr();
								System.out.println(name);
							}
						}
					}
					turn = 3;
					break;
				case 3:
					System.out.println("Seer, close your eyes. \n Robber, wake up. You may exchange your card with another player’s card, and then view your new card.");
//					timer(10,0);
					for(int i = 0; i < players.size(); i++) {
						name = players.get(i).getOrigRoleStr();
						if(name.equals("Robber")) {
							//get the player's input
							choice = players.get(i).wakeUp(numOfPlayers, in);
							
							//switch roles
							temp = players.get(i).getRole();
							temp2 = players.get(choice.get(0) - 1).getRole();
							players.get(i).assignRole(temp2);
							players.get(choice.get(0) - 1).assignRole(temp);
							name = players.get(i).getRoleStr();
							System.out.println("Your new card is " + name);
							break;
						}
					}
					turn = 4;
					break;
				case 4:
					System.out.println("Robber, close your eyes. \n Troublemaker, wake up. You may exchange cards between two other players");
//					timer(10,0);
					for(int i = 0; i < players.size(); i++) {
						name = players.get(i).getOrigRoleStr();
						if(name.equals("Troublemaker")) {
							//get the player's input
							choice = players.get(i).wakeUp(numOfPlayers, in);
							c = choice.get(0);
							c2 = choice.get(1);
							
							//switch roles
							temp = players.get(choice.get(0) - 1).getRole();
							temp2 = players.get(choice.get(1) - 1).getRole();
							players.get(choice.get(0) - 1).assignRole(temp2);
							players.get(choice.get(1) - 1).assignRole(temp);
						}
					}
					turn = 5;
					break;
				case 5:
					System.out.println("Troublemaker, close your eyes");
					System.out.println("Everyone, Wake up!");
					turn = 0;
					break;
			}
		}
		
		//reveal
		for(int i = 0; i < players.size(); i++) {
			name = players.get(i).getRoleStr();
			switch(name) {
				case "Werewolf":
					System.out.println("Player " + (i+1) + " was a Werewolf");
					break;
				case "Seer":
					System.out.println("Player " + (i+1) + " was the Seer");
					break;
				case "Robber":
					System.out.println("Player " + (i+1) + " was the Robber");
					break;
				case "Troublemaker":
					System.out.println("Player " + (i+1) + " was the Troublemaker");
					break;
				case "Villager":
					System.out.println("Player " + (i+1) + " was a Villager");
					break;
			}
		}
		
		in.close();
	}

}
