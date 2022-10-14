
public class Hornet extends Insect {
	// ------------ FIELDS ------------
	private int attackDamage;

	// ------------ CONSTRUCTOR ------------
	public Hornet(Tile pos, int hp, int damage) { 
		// Create a Hornet with these characteristics
		super(pos, hp);
		this.attackDamage = damage;
	}
	
	

	public boolean takeAction() {	
		// CASE 1: There is a bee on the same tile -> inflict damage
		if (this.getPosition().getBee() != null) {		
			this.getPosition().getBee().takeDamage(this.attackDamage);
			return true;
		} 
		// CASE 2: There is no bee on the same tile -> move to the next tile
		if (this.getPosition().getBee() == null && !this.getPosition().isHive()) {
			this.setPosition(this.getPosition().towardTheHive());
			this.getPosition().addInsect(this); // This hornet moves to the next tile toward the bee hive
			this.getPosition().towardTheNest().removeInsect(this); // Remove this hornet from the current tile
			return true;
		}
		return false;
	}
	
	public boolean equals(Object anObject) {
		// Returns true if it matches this in type, position, health and attackDamage
		if (anObject instanceof Hornet) {
			Hornet obj = (Hornet) anObject;
			return (super.equals(obj) && this.attackDamage == obj.attackDamage);
		}
		return false;
	}
		
}
