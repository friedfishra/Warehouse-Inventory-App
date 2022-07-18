package models;

public class Item {
	
	private int ItemId;
	private String ItemName;
	private int WeightTypeId;
	private int WeightId;
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
	public int getWeightTypeId() {
		return WeightTypeId;
	}
	public void setWeightTypeId(int weightTypeId) {
		WeightTypeId = weightTypeId;
	}
	public int getWeightId() {
		return WeightId;
	}
	public void setWeightId(int weightId) {
		WeightId = weightId;
	}
	public Item() {
		super();
	}
	public Item(int itemId, String itemName, int weightTypeId, int weightId) {
		super();
		ItemId = itemId;
		ItemName = itemName;
		WeightTypeId = weightTypeId;
		WeightId = weightId;
	}
	@Override
	public String toString() {
		return "Item [ItemId=" + ItemId + ", ItemName=" + ItemName + ", WeightTypeId=" + WeightTypeId + ", WeightId="
				+ WeightId + "]";
	}
	
	
}
