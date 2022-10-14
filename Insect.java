public abstract class Insect {
	// ------------ FIELDS ------------
	private Tile position;
	private int healthPoint;


	// ------------ CONSTRUCTOR ------------
	public Insect(Tile pos, int hp) {
		// when an insect with a specified position is created, it's also added to the corresponding tile
		if (this instanceof HoneyBee) {
			if (pos.addInsect(this) == false)	{
				throw new IllegalArgumentException("Cannot add this bee to this tile");
			} else {
				this.position = pos;
				this.healthPoint = hp;
				this.position.addInsect(this);
			}
		} else if (this instanceof Hornet) {
			this.position = pos;
			this.healthPoint = hp;
			this.position.addInsect(this);
		}
			
	}

	// ------------ METHODS ------------
	
	// Retrieve the position of (this) insect
	public final Tile getPosition() {
		return this.position;
	}
	
	// Retrieve the health points of (this) insect
	public final int getHealth() {
		return this.healthPoint;
	}
	
	// Update the value stored in the position of the insect
	public void setPosition(Tile pos) {
		this.position = pos;
	}
	
	public void takeDamage(int damageReceived) {
		// if the insect is a bee positioned on the bee hive, the damage is reduced by 10% before being applied
		if (this instanceof HoneyBee) {
			if (this.position.isHive() == true) {
				damageReceived = (int) (damageReceived * 0.9);
				this.healthPoint -= damageReceived;
			} else {
				this.healthPoint -= damageReceived;
			}
		}
		else {
			this.healthPoint -= damageReceived;
		} 
		
		// If the insect has been killed, remove it from the tile
		if (this.healthPoint <= 0) {
			this.position.removeInsect(this); // Remove the insect from the tile
		}
	}
	
	public abstract boolean takeAction();
	
	public boolean equals(Object anObject) {
		// Returns true if it matches this in type, position and health.
		if (anObject instanceof Insect){
			Insect obj = (Insect) anObject;
			if (this.getPosition() == obj.getPosition() && this.getHealth() == obj.getHealth()) {
				return true;
			}
		}
		return false;
	}
}
