package com.skillstorm.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import com.skillstorm.conf.WarehouseDBCreds;

import models.Item;

public class ItemDAOImpl implements ItemDAO{

	@Override
	public LinkedList<Item> findAll() {
		String sql = "SELECT * FROM item";
		
		try (Connection conn = WarehouseDBCreds.getInstance().getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			LinkedList<Item> items = new LinkedList<>();
			while(rs.next()) {
				Item item = mapRowToItem(rs);
				items.add(item);
			}
			return items;
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return null;
	}

	@Override
	public Item findById(int id) {
		String sql = "SELECT * FROM item WHERE ItemId =  + ?";
		try (Connection conn = WarehouseDBCreds.getInstance().getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Item item = mapRowToItem(rs);
				return item;
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Item findByName(String name) {
		String sql = "SELECT * FROM item WHERE ItemName = ?";
		try (Connection conn = WarehouseDBCreds.getInstance().getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Item item = mapRowToItem(rs);
				return item;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Item save(Item item) {
		String sql = "INSERT INTO item (ItemName, WeightType, Weight, ZoneId, Aisle) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = WarehouseDBCreds.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, item.getItemName());
			ps.setString(2,  item.getWeightType());
			ps.setInt(3,  item.getWeight());
			ps.setInt(4,  item.getZoneId());
			ps.setInt(5,  item.getAisle());
			
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected != 0) {
				ResultSet keys = ps.getGeneratedKeys();
				if (keys.next()) {
					int key = keys.getInt(1);
					item.setItemId(key);
					conn.commit();
					return item;
				} else {
					conn.rollback();
				}
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void updateItem(Item item) {
		String sql = "UPDATE item SET ItemId = ?, ItemName = ?, WeightType = ?, Weight = ?, ZoneId = ?, Aisle = ?"
				+ "WHERE ItemId = ?";
		try(Connection conn = WarehouseDBCreds.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, item.getItemId());
			ps.setString(2, item.getItemName());
			ps.setString(3,  item.getWeightType());
			ps.setInt(4,  item.getWeight());
			ps.setInt(5,  item.getZoneId());
			ps.setInt(6,  item.getAisle());
			
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected != 0) {
				conn.commit();
				
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Item item) {
		String sql = "DELETE FROM item WHERE ItemId = ?";
		try(Connection conn = WarehouseDBCreds.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  item.getItemId());
			
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected != 0) {
				conn.commit();
				
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void deleteById(int id) {
		String sql = "DELETE FROM item WHERE ItemId = ?";
		try(Connection conn = WarehouseDBCreds.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  id);
			
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected != 0) {
				conn.commit();
				
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void deleteMany(int[] ids) {
		String sql = "DELETE FROM item WHERE ItemId = ?";
		try(Connection conn = WarehouseDBCreds.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			for(int id : ids) {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1,  id);
				
				int rowsAffected = ps.executeUpdate();
				if(rowsAffected != 0) {
					conn.commit();
					
				} else {
					conn.rollback();
				}
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	private static Item mapRowToItem(ResultSet rs) {
		Item item = new Item();
		
		try {
			item.setItemId(rs.getInt("ItemId"));
			item.setItemName(rs.getString("ItemName"));
			item.setWeightType(rs.getString("WeightType"));
			item.setWeight(rs.getInt("Weight"));
			item.setZone(rs.getInt("ZoneId"));
			item.setAisle(rs.getInt("Aisle"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return item;
	}



}
