/**
 * This class represents the Role of a Troublemaker in One Night Ultimate Werewolf.
 * It implements the Role Interface.
 */
package werewolf;

public class Troublemaker implements Role {

	@Override
	public String getRoleStr() {
		return "Troublemaker";
	}
	
	@Override
	public String imageFile() {
		return "Troublemaker.jpg";
	}

}
