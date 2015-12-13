/**
 * This class represents the Role of a Seer in One Night Ultimate Werewolf.
 * It implements the Role Interface.
 */
package werewolf;

public class Seer implements Role {
	
	@Override
	public String getRoleStr() {
		return "Seer";
	}
	
	@Override
	public String imageFile() {
		return "Seer.jpg";
	}
	
}
