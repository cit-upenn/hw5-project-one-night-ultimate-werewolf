/**
 * This class represents the Role of the Werewolf in One Night Ultimate Werewolf.
 * It implements the Role Interface.
 */
package werewolf;

public class Werewolf implements Role {
	
	@Override
	public String getRoleStr() {
		return "Werewolf";
	}
	
	@Override
	public String imageFile() {
		return "WerewolfRole.jpg";
	}

}
