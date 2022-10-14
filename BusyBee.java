
// Bees that collect pollen

public class BusyBee extends HoneyBee {
	
	// ------------ CONSTRUCTOR ------------
	public BusyBee(Tile pos) {
		// Create pollen-collecting bee with the given position, health = 5, foodCost = 2
		super(pos, 5, 2);		
	}
	
	public boolean takeAction() {
		// BusyBee collects pollen, their action results into 2 food added to their tile
		this.getPosition().storeFood(2);
		return true;
	}
}
