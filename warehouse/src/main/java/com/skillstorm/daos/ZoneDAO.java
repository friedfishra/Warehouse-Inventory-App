package com.skillstorm.daos;

import java.util.LinkedList;

import com.skillstorm.models.Zone;

public interface ZoneDAO {
	
	public LinkedList<Zone> findAll();
	public Zone findById(int id);
	public Zone findByName(String name);
	public Zone save(Zone zone);
	public void update(Zone zone);
	public void delete(Zone zone);
	public void deleteById(int id);
	public void deleteMany(int[] ids);

}
