package models;

public class Item {
	
	private int ItemId;
	private String ItemName;
	private String WeightType;
	private int Weight;
	private int zoneId;
	private int aisle;
	public int getItemId() {
		return ItemId;
	}
	public void setItemId(int itemId) {
		ItemId = itemId;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	
	public String getWeightType() {
		return WeightType;
	}
	public void setWeightType(String weightType) {
		WeightType = weightType;
	}
	public int getWeight() {
		return Weight;
	}
	public void setWeight(int weight) {
		Weight = weight;
	}
	public int getZoneId() {
		return zoneId;
	}
	public void setZone(int zone) {
		this.zoneId = zone;
	}
	public int getAisle() {
		return aisle;
	}
	public void setAisle(int aisle) {
		this.aisle = aisle;
	}
	public Item() {
		super();
	}
	public Item(int itemId, String itemName, String weightType, int weight, int zoneId, int aisle) {
		super();
		ItemId = itemId;
		ItemName = itemName;
		WeightType = weightType;
		Weight = weight;
		this.zoneId = zoneId;
		this.aisle = aisle;
	}
	@Override
	public String toString() {
		return "Item [ItemId=" + ItemId + ", ItemName=" + ItemName + ", WeightType=" + WeightType + ", Weight=" + Weight
				+ ", zone=" + zoneId + ", aisle=" + aisle + "]";
	}
	
	
	
}
