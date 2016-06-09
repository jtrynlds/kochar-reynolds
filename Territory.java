import java.util.Arrays;
import java.util.List;


/**
 * This class represents a territory in a game of Uncertainty.
 * It is held by a player and has a number of armies. It gives armies that allows the player to be deployed.
 * 
 * @author Sahil Kochar
 *
 */
public class Territory {
	
	private List<Integer> neighbors;
	private Player owner;
	private String name;
	private Army troops;
	
	//Whether the terrain is land, water, or desert
	//0 is land, 1 is water, and 2 is desert
	private int terrainType;
	
	public Territory(String territoryName, int startingTroops, int terrain, Integer[] adjacent){
		name = territoryName;
		terrainType = terrain;
		troops = new Army(startingTroops, findDefense(terrainType));
		neighbors.addAll(Arrays.asList(adjacent));
	}
	
	/**
	 * @return The player that owns this territory.
	 */
	public String getPlayerID(){
		return owner.getId();
	}
	
	/*
	public int getIncome(){
		int output = 1;
		for(Territory t : neighbors){
			if(t.getPlayerID().equals(this.getPlayerID()));
		}
		
		return output;
	}
	*/
	
	/**
	 * Allows the player to deploy part of their income into this territory
	 * @param numArmies
	 */
	public void deploy(int numArmies){
		troops.add(numArmies);
	}
	
	/**
	 * Moves armies from this territory to an adjacent territory.
	 * It attacks if the adjacent territory is held by an enemy.
	 * It transfers armies there if the adjacent territory is held by the same player.	 
	 * @param target The territory to send armies into.
	 * @param numToMove The number of armies to send into the target.
	 * @return Whether the attack succeeded
	 */
	public boolean moveInto(Territory target, int numToMove){
		if(target.getPlayerID().equals(this.getPlayerID())){
			this.troops.transfer(target.getArmy(), numToMove);
			return true;
		}
		return this.troops.attack(target.getArmy());		
	}
	
	/**
	 * Attacks from this territory to another territory held by a different player.
	 * @param target The territory to be attacked into.
	 * @return Whether the attack was successful
	 */
	public boolean attack(Territory target){
		return troops.attack(target.getArmy());
	}
	
	/**
	 * @return The Army that is held by this territory.
	 */
	public Army getArmy(){
		return troops;
	}
	
	/**
	 * Sets the owner of the territory.
	 * @param newOwner The Player that the owner should be set to.
	 */
	public void setOwner(Player newOwner){
		owner = newOwner;
	}
	
	/**
	 * Changes the owner of this territory.
	 * @param newOwner The new owner.
	 * @param numOccupying The number of armies that the new owner owns in this territory.
	 */
	public void changeOwner(Player newOwner, int numOccupying){
		this.owner = newOwner;
		troops = new Army(numOccupying, findDefense(terrainType));
	}
	
	/**
	 * Pretty much a mapping from terrain type to the defensiveness of the territory
	 * @param terrain The terrain type to find the defensiveness of.
	 * @return The defense that corresponds to the terrain type.
	 */
	private static double findDefense(int terrain){
		
		//Normal territory is normal
		if(terrain == 0){
			return 1.0;
		}
		
		//Water territory is harder to attack into
		if(terrain == 1){
			return 1.5;
		}
		
		//Desert territory is easier to attack into
		if(terrain == 2){
			return 0.75;
		}
		
		return 0;
	}
}
