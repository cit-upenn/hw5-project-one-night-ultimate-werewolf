package werewolf;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Game {
	// instance variables
	private int numOfPlayers;
	private ArrayList<Role> roles;
	private ArrayList<Role> center;
	private ArrayList<Player> players;
	private int turn = 1;
	private ArrayList<Integer> choice;
	private int c, c2;
	private Role temp, temp2;
	
	// methods
	public void play() {
		//title screen
		timer(5, 0);
		
		/**
		 * SETUP
		 */
		//select number of players
		System.out.println("Enter number of players from 3 to 5");
		System.out.println("and decide what number you are");
		Scanner in = new Scanner(System.in);
		numOfPlayers = Integer.parseInt(in.nextLine());
		in.close();
		
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
		/*p1 = roles.get(0);
		p2 = roles.get(1);
		p3 = roles.get(2);
		if(num == 4) {
			p4 = roles.get(3);
		} else if(num == 5) {
			p5 = roles.get(4);
		}*/
		
		//put 3 remaining cards at center of table
		for(int i = 0; i < 3; i++) {
			center.add(roles.get(numOfPlayers));
			roles.remove(numOfPlayers);
		}
		
		System.out.println("Everyone close your eyes");
		timer(3,0);
		
		System.out.println("Player 1, open your eyes. this is your card.");
		timer(5,0);
		
		System.out.println("Player 2, open your eyes. this is your card.");
		timer(5,0);
		
		System.out.println("Player 3, open your eyes. this is your card.");
		timer(5,0);
		
		System.out.println("Player 4, open your eyes. this is your card.");
		timer(5,0);
		
		System.out.println("Player 5, open your eyes. this is your card.");
		timer(5,0);
		
		
		
		
		while(turn != 0) {
			switch(turn) {
				case 1:
					System.out.println("Werewolves, wake up and look for other werewolves.");
					timer(10,0);
					turn = 2;
					break;
				case 2:
					System.out.println("Werewolves, close your eyes. \n Seer, wake up. You may look at another player’s card or two of the center cards");
					timer(10,0);
					for(int i = 0; i < roles.size(); i++) {
						if(roles.get(i).getID() == 2) {
							choice = players.get(i).wakeUp(numOfPlayers);
						}
					}
					turn = 3;
					break;
				case 3:
					System.out.println("Seer, close your eyes. \n Robber, wake up. You may exchange your card with another player’s card, and then view your new card.");
					timer(10,0);
					for(int i = 0; i < roles.size(); i++) {
						if(roles.get(i).getID() == 3) {
							choice = players.get(i).wakeUp(numOfPlayers);
							//switch roles
							temp = players.get(i).getRole();
							temp2 = players.get(choice.get(0) - 1).getRole();
							players.get(i).assignRole(temp2);
							players.get(choice.get(0) - 1).assignRole(temp);
							System.out.println("Your new card is ");
							break;
						}
					}
					break;
				case 4:
					System.out.println("Robber, close your eyes. \n Troublemaker, wake up. You may exchange cards between two other players");
					timer(10,0);
					for(int i = 0; i < roles.size(); i++) {
						if(roles.get(i).getID() == 4) {
							choice = players.get(i).wakeUp(numOfPlayers);
							c = choice.get(0);
							c2 = choice.get(1);
							//switch roles
							temp = players.get(choice.get(0) - 1).getRole();
							temp2 = players.get(choice.get(1) - 1).getRole();
							players.get(choice.get(0) - 1).assignRole(temp2);
							players.get(choice.get(1) - 1).assignRole(temp);
						}
					}
					break;
				case 5:
					System.out.println("Troublemaker, close your eyes");
					System.out.println("Everyone, Wake up!");
					break;
			}
		}
		
		//reveal
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getRole() == Werewolf()) {
				System.out.println("Player " + i + " was a werewolf");
			}
		}
	}

	
	public void timer(int sec, int who) {
		switch(who) {
			case 0:
				break;
			case 1:
				System.out.println("Werewolf open your eyes");
				break;
			case 2:
				System.out.println("Seer open your eyes");
				break;
		}

		new Countdown(sec);
	}

}
