package com.skillstorm.daos;

import java.util.LinkedList;

import com.skillstorm.models.Item;

public interface ItemDAO {
	
	public LinkedList<Item> findAll();
	public Item findById(int id);
	public Item findByName(String name);
	public Item save(Item item);
	public void updateItem(Item item);
	public void delete(Item item);
	public void deleteById(int id);
	public void deleteMany(int[] ids);
}
