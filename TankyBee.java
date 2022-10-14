// Slow-to-sting but sturdy bees

public class TankyBee extends HoneyBee {
	// ------------ FIELDS ------------
	private int attackDamage;
	private int armor;
	
	// ------------ CONSTRUCTOR ------------
	public TankyBee(Tile pos, int damage, int armor) {
		// Creating slow-to-sting-but-sturdy bee with given position, aD, armor, health = 30, foodCost = 3
		super(pos, 30, 3);
		this.attackDamage = damage;
		this.armor = armor;
	}
	
	public boolean takeAction() {
		// TankyBee can only sting hornets that are positioned on the same tile as them
		if (this.getPosition().getNumOfHornets() != 0) {
			this.getPosition().getHornet().takeDamage(this.attackDamage);
			return true;
		} 
		return false;
	}
	
	

	public void takeDamage(int damageReceived) {
		double multiplier = 100.0 / (100.0 + this.armor);
		double realDamage = damageReceived * multiplier;
		int realDamage_int = (int) realDamage;				// TYPECASTING
		super.takeDamage(realDamage_int);
	}
	
	public boolean equals(Object anObject) {
		// Returns true if the Object received as input matches this in type, position, health, attack damage and armor
		if (anObject instanceof TankyBee) {
			TankyBee obj = (TankyBee) anObject;
			return (super.equals(obj) && (this.attackDamage == obj.attackDamage) && (this.armor == obj.armor));
		}
		return false;
	}
	
}
