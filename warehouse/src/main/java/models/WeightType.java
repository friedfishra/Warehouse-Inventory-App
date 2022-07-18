package models;

public class WeightType {
	private int WeightTypeId;
	private String WeightTypeName;
	public int getWeightTypeId() {
		return WeightTypeId;
	}
	public void setWeightTypeId(int weightTypeId) {
		WeightTypeId = weightTypeId;
	}
	public String getWeightTypeName() {
		return WeightTypeName;
	}
	public void setWeightTypeName(String weightTypeName) {
		WeightTypeName = weightTypeName;
	}
	public WeightType() {
		super();
	}
	public WeightType(int weightTypeId, String weightTypeName) {
		super();
		WeightTypeId = weightTypeId;
		WeightTypeName = weightTypeName;
	}
	@Override
	public String toString() {
		return "WeightType [WeightTypeId=" + WeightTypeId + ", WeightTypeName=" + WeightTypeName + "]";
	}
	
	
}
