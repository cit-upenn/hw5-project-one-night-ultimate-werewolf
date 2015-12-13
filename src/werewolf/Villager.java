/**
 * This class represents a Role of a Villager in One Night Ultimate Werewolf.
 * It implements the Role Interface.
 */
package werewolf;

public class Villager implements Role {
	
	@Override
	public String getRoleStr() {
		return "Villager";
	}
	
	@Override
	public String imageFile() {
		return "Villager-Werewolves.jpg";
	}

}
