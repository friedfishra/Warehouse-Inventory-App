package models;

public class Weight {
	private int weightId;
	private int weightName;
	public int getWeightId() {
		return weightId;
	}
	public void setWeightId(int weightId) {
		this.weightId = weightId;
	}
	public int getWeightName() {
		return weightName;
	}
	public void setWeightName(int weightName) {
		this.weightName = weightName;
	}
	public Weight() {
		super();
	}
	public Weight(int weightId, int weightName) {
		super();
		this.weightId = weightId;
		this.weightName = weightName;
	}
	@Override
	public String toString() {
		return "Weight [weightId=" + weightId + ", weightName=" + weightName + "]";
	}
	
	
}
