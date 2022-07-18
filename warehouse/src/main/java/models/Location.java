package models;

public class Location {
	private int ZoneId;
	private int Aisle;
	private int ItemId;
	public int getZoneId() {
		return ZoneId;
	}
	public void setZoneId(int zoneId) {
		ZoneId = zoneId;
	}
	public int getAisle() {
		return Aisle;
	}
	public void setAisle(int aisle) {
		Aisle = aisle;
	}
	public int getItemId() {
		return ItemId;
	}
	public void setItemId(int itemId) {
		ItemId = itemId;
	}
	public Location() {
		super();
	}
	public Location(int zoneId, int aisle, int itemId) {
		super();
		ZoneId = zoneId;
		Aisle = aisle;
		ItemId = itemId;
	}
	@Override
	public String toString() {
		return "Location [ZoneId=" + ZoneId + ", Aisle=" + Aisle + ", ItemId=" + ItemId + "]";
	}
	
	
}
