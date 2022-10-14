
// Bees that sting their enemies

public class StingyBee extends HoneyBee {
	// ------------ FIELDS ------------
	private int attackDamage;
	
	// ------------ CONSTRUCTOR ------------
	public StingyBee(Tile position, int damage) {
		// Create enemies-stinging bee with given position, attack damage, health = 10, foodCost = 1
		super(position, 10, 1);
		this.attackDamage = damage;
	}

	public boolean takeAction() {		
		// Stingy bee can only take action if:
		// a) it's positioned on the path leading from the nest to the hive
		// b) on the bee hive itself
		// Stingy bee canNOT take action if the hornet is on its nest
		if (this.getPosition().isHive()|| this.getPosition().isOnThePath()) {
			if (!this.getPosition().isNest() && this.getPosition().getNumOfHornets() != 0) {
					this.getPosition().getHornet().takeDamage(this.attackDamage);	
					return true;
			} else if (this.getPosition().towardTheNest() != null){
				// Looking for the first non-empty swarm on the path that leads from the bee’s tile to the hornet nest
				while (this.getPosition().towardTheNest().getNumOfHornets() == 0 || this.getPosition().towardTheNest().isNest() == false) {
					if (this.getPosition().towardTheNest().getNumOfHornets() != 0) {
						this.getPosition().towardTheNest().getHornet().takeDamage(this.attackDamage);
						return true;
						}	
				}
			
			}
		}
		return false;
	}

	public boolean equals(Object anObject) {
		// Returns true if the Object received as input matches this in type, position, food cost, and attack damage.
		if (anObject instanceof StingyBee) {
			StingyBee obj = (StingyBee) anObject;
			return (super.equals(obj) && this.attackDamage == obj.attackDamage);
		}
		return false;
	}
}
