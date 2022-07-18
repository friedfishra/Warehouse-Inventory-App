package models;

public class Zone {
	private int ZoneId;
	private String ZoneName;
	private int WarehouseId;
	public int getZoneId() {
		return ZoneId;
	}
	public void setZoneId(int zoneId) {
		ZoneId = zoneId;
	}
	public String getZoneName() {
		return ZoneName;
	}
	public void setZoneName(String zoneName) {
		ZoneName = zoneName;
	}
	public int getWarehouseId() {
		return WarehouseId;
	}
	public void setWarehouseId(int warehouseId) {
		WarehouseId = warehouseId;
	}
	public Zone() {
		super();
	}
	public Zone(int zoneId, String zoneName, int warehouseId) {
		super();
		ZoneId = zoneId;
		ZoneName = zoneName;
		WarehouseId = warehouseId;
	}
	@Override
	public String toString() {
		return "Zone [ZoneId=" + ZoneId + ", ZoneName=" + ZoneName + ", WarehouseId=" + WarehouseId + "]";
	}
	
	
}
