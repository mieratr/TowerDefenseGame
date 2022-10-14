
public class Tile {
	
	// FIELDS
	private int foodAvailable;					// Food present on the tile
	private boolean isBeehive;					// Whether a bee hive is present on this tile
	private boolean isHornetNest;				// Whether a a hornet nest is present on this tile
	private boolean isTileFromNestToHive;		// Whether this tile is part of (nest -> hive) path
	private Tile nextTileTowardHive;			// Reference to the next tile on the path from (nest -> hive)
	private Tile nextTileTowardNest;			// Reference to the next tile on the path from (hive -> nest)
	private HoneyBee bee;						// Indicate the bee that is on this tile
	private SwarmOfHornets hornetSwarm;		    // Indicate all the hornets that are on this tile
	

	public Tile() {
		this.foodAvailable = 0;
		this.isBeehive = false;
		this.isHornetNest = false;
		this.isTileFromNestToHive = false;
		this.nextTileTowardHive = null;
		this.nextTileTowardHive = null;
		this.bee = null;
		this.hornetSwarm = null;	
	}
	
	// SECOND CONSTRUCTOR
	public Tile(int foodAvailable, boolean isBeehive, boolean isHornetNest, boolean isTileFromNestToHive, Tile nextTileTowardHive, Tile nextTileTowardNest, HoneyBee bee, SwarmOfHornets hornetSwarm) {
		this.foodAvailable = foodAvailable;
		this.isBeehive = isBeehive;
		this.isHornetNest = isHornetNest;
		this.isTileFromNestToHive = isTileFromNestToHive;
		this.nextTileTowardHive = nextTileTowardHive;
		this.nextTileTowardNest = nextTileTowardNest;
		this.bee = bee;
		this.hornetSwarm = hornetSwarm;
	}
	
	// -------------- METHODS --------------
	
	// Whether a bee hive has been built on this tile
	public boolean isHive() {
		if (this.isBeehive == true) {
			return true;
		}
		return false;
	}
	
	// Whether a hornet nest has been built on this tile 
	public boolean isNest() {
		if (this.isHornetNest == true) {
			return true;
		}
		return false;
	}
	
	// Update field indicating whether there's a bee hive on the tile
	public void buildHive() {
		this.isBeehive = true;
	}
	
	// Update field indicating whether there's a hornet nest on the tile
	public void buildNest() {
		this.isHornetNest = true;
	}
	
	// Check whether this tile is part of the [nest -> hive] path
	public boolean isOnThePath() {
		if (this.isTileFromNestToHive == true) {
			return true;
		}
		return false;
	}
	
	// Return the next tile that is on the path [nest -> hive]
	public Tile towardTheHive() {
		if (this.isOnThePath() && this.nextTileTowardHive != null) {
			return this.nextTileTowardHive;
		}
		return null;
	}
	
	// Return the next tile that is on the path [hive -> nest]
	public Tile towardTheNest() {
		if (this.isOnThePath() && this.nextTileTowardNest != null) {
			return this.nextTileTowardNest;
		}
		return null;
	}

	// Building path, putting one tile toward the hive and another one toward the nest
	public void createPath(Tile newTileToHive, Tile newTileToNest) {
		this.nextTileTowardHive = newTileToHive;
		this.nextTileTowardNest = newTileToNest;
		this.isTileFromNestToHive= true;
		
	}
	
	// Collect the food on the tile and leaves the tile with no food
	public int collectFood() {
		int foodOnTile = this.foodAvailable;
		this.foodAvailable = 0;
		return foodOnTile; 	
	}
	
	// Add to the food stored on this tile
	public void storeFood(int foodReceived) {
		this.foodAvailable += foodReceived;
	}
	
	// Return the bee position on this tile
	public HoneyBee getBee() {
		if (this.bee != null) {
			return this.bee;
		}
		return null;
	}
	
	
	// Get the first joined (oldest) hornet of the swarm on this tile
	public Hornet getHornet() {				
		if (this.hornetSwarm != null && this.hornetSwarm.sizeOfSwarm() != 0 ) {
			return this.hornetSwarm.getHornets()[0];
		}
		return null;
	}
	
	public int getNumOfHornets() {
		if (this.hornetSwarm != null) {
			return this.hornetSwarm.getHornets().length;
		}
		return 0;
	}
	
	
	
	public boolean addInsect(Insect theInsect) {
		if (theInsect instanceof HoneyBee) {
			if (this.bee == null && this.isHornetNest == false) {
				this.bee = (HoneyBee) theInsect;			// Change the property of the tile
				theInsect.setPosition(this);				// Change the property of the insect
				return true;
			} 
		} else if (theInsect instanceof Hornet) {
			if (this.isHornetNest == true || this.isBeehive == true || this.isTileFromNestToHive == true ) { 
				if (this.hornetSwarm == null) {
					SwarmOfHornets newSwarm = new SwarmOfHornets();
					newSwarm.addHornet((Hornet) theInsect);
					this.hornetSwarm = newSwarm;
				} else {
					this.hornetSwarm.addHornet((Hornet) theInsect);	// Add the insect to the first spot of the swarm 
				}
				theInsect.setPosition(this);	 // Change the property of the insect
				return true;
			}
		}
		return false;
	}
	
	
	public boolean removeInsect(Insect theInsect) {
		if (theInsect instanceof HoneyBee) {
			if (this.getBee() != null) {
				this.bee = null;		// Change the property of the tile
				//theInsect.setPosition(null);		// Change the property of the insect
				return true;
			}
		} else if (theInsect instanceof Hornet) {
			if (this.hornetSwarm != null) {
				this.hornetSwarm.removeHornet((Hornet) theInsect);		// Change the property of the tile					
				return true;
			}
		}
		return false;
	}
}
