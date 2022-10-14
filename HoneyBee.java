
public abstract class HoneyBee extends Insect {
	// ------------ FIELDS ------------
	private int foodCost;
	
	// ------------ CONSTRUCTOR ------------
	public HoneyBee(Tile position, int hp, int cost) {
		// Create a HoneyBee with these characteristics
		super(position, hp);
		this.foodCost = cost;
	}
	
	// ------------ METHODS ------------
	
	// Return how much (this) bee costs in food
	public int getCost() {
		return this.foodCost;
	}
	
	public boolean equals(Object anObject) {
		// Returns true if it matches this in type, position, and food
		if (anObject instanceof HoneyBee) {
			HoneyBee obj = (HoneyBee) anObject;
			return (super.equals(obj) && this.foodCost == obj.foodCost);
		}
		return false;
	}
		
}
