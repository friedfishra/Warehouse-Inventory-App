package com.skillstorm.daos;

import java.util.List;

import models.Item;

public interface ItemDAO {
	
	public List<Item> findAll();
	public Item findById();
	public Item findByName();
	public Item save(Item item);
	public void updateName(Item item);
	public void delete(Item item);
	public void deleteMany(int[] ids);
}
