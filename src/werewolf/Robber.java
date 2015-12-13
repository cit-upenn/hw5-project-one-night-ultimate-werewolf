/**
 * This class represents the Role of a Robber in One Night Ultimate Werewolf.
 * It implements the Role Interface.
 * 
 */
package werewolf;

public class Robber implements Role {
	
	@Override
	public String getRoleStr() {
		return "Robber";
	}
	
	
	@Override
	public String imageFile() {
		return "Robber.jpg";
	}
	

}
