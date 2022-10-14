

// Represents a group of hornets

public class SwarmOfHornets {
	// FIELDS
	private Hornet[] hornetSwarm;
	private int swarmSize;
	
	
	// CONSTRUCTOR
	public SwarmOfHornets() {
		this.swarmSize = 0;
		this.hornetSwarm = new Hornet[this.swarmSize];
	}

	public String toString() {
		return " " + hornetSwarm;
	}
	
	// METHODS
	public int sizeOfSwarm() {
		return this.hornetSwarm.length;
	}
	
	public Hornet[] getHornets() {		// The array must contain no null elements, and in order
		return this.hornetSwarm;
	}
	
	public Hornet getFirstHornet() {
		if (this.hornetSwarm.length == 0) {
			return null;
		} else {
			return this.hornetSwarm[0];
		}
	}

	public void addHornet(Hornet h) {
		// when the swarm array is empty
		if (swarmSize == 0) {
			hornetSwarm = new Hornet[1];
			hornetSwarm[0] = h;
			swarmSize++;
		// when the swarm array is not full
		} else if (hornetSwarm[swarmSize-1] == null){
			int i = 0;
			while (i < swarmSize) {
				if (hornetSwarm[i] == null) {
					hornetSwarm[i] = h;
					break;
				} else {i++;}
			}
		// when the swarm array is full
		} else {
			Hornet[] intermediateArray = new Hornet[swarmSize+1];
			for (int i = 0; i < swarmSize; i++) {
				intermediateArray[i] = hornetSwarm[i];
			}
			swarmSize++;
			hornetSwarm = new Hornet[swarmSize];
			for (int i = 0; i < swarmSize; i++) {
				hornetSwarm[i] = intermediateArray[i] ;
				hornetSwarm[swarmSize-1] = h;
			}
		}
	}
	

	
	// Delete space in swarm when a Hornet is removed
	public boolean removeHornet(Hornet h) {
		// Search for the first occurrence of the Hornet h
		for (int i = 0; i < swarmSize; i++) {
			if (hornetSwarm[i].equals(h)) {
				// Delete Hornet h
				for (int j = i; j < hornetSwarm.length-1; j++) {
					hornetSwarm[j] = hornetSwarm[j+1];
				}
				swarmSize--;
				Hornet[] intermediateArray = new Hornet[swarmSize];
				for (int j = 0; j < swarmSize; j++) {
					intermediateArray[j] = hornetSwarm[j];
				}
				hornetSwarm = new Hornet[swarmSize];
				for (int j = 0; j < swarmSize; j++) {
					hornetSwarm[j] = intermediateArray[j];
				}
				return true;
			} 
		}
		return false;
		
	}
	

}
