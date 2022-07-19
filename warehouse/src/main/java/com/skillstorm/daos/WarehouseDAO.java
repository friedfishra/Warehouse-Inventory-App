package com.skillstorm.daos;

import java.util.LinkedList;

import com.skillstorm.models.Warehouse;

public interface WarehouseDAO {
	
	public LinkedList<Warehouse> findAll();
	public Warehouse findById(int id);
	public Warehouse findByName(String name);
	public Warehouse save(Warehouse warehouse);
	public void update(Warehouse warehouse);
	public void delete(Warehouse warehouse);
	public void deleteById(int id);
	public void deleteMany(int[] ids);

}
