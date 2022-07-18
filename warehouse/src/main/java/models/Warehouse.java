package models;

public class Warehouse {
	private int WarehouseId;
	private String WarehouseName;
	public int getWarehouseId() {
		return WarehouseId;
	}
	public void setWarehouseId(int warehouseId) {
		WarehouseId = warehouseId;
	}
	public String getWarehouseName() {
		return WarehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		WarehouseName = warehouseName;
	}
	public Warehouse() {
		super();
	}
	public Warehouse(int warehouseId, String warehouseName) {
		super();
		WarehouseId = warehouseId;
		WarehouseName = warehouseName;
	}
	@Override
	public String toString() {
		return "Warehouse [WarehouseId=" + WarehouseId + ", WarehouseName=" + WarehouseName + "]";
	}
	
	
}
